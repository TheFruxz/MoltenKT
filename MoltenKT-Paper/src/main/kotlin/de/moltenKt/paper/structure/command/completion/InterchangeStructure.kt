package de.moltenKt.paper.structure.command.completion

import de.moltenKt.core.tool.smart.identification.UUID
import de.moltenKt.core.tool.smart.positioning.Address
import de.moltenKt.core.tree.TreeBranch
import de.moltenKt.core.tree.TreeBranchType
import de.moltenKt.paper.extension.debugLog
import de.moltenKt.paper.extension.interchange.InterchangeExecutor
import de.moltenKt.paper.structure.command.InterchangeResult
import de.moltenKt.paper.structure.command.InterchangeResult.SUCCESS
import de.moltenKt.paper.structure.command.completion.InterchangeStructure.BranchStatus.*
import de.moltenKt.paper.structure.command.completion.component.CompletionAsset
import de.moltenKt.paper.structure.command.completion.component.CompletionComponent
import de.moltenKt.paper.structure.command.completion.tracing.CompletionTraceResult
import de.moltenKt.paper.structure.command.completion.tracing.CompletionTraceResult.Conclusion.EMPTY
import de.moltenKt.paper.structure.command.completion.tracing.PossibleTraceWay
import de.moltenKt.paper.structure.command.live.InterchangeAccess
import de.moltenKt.paper.tool.permission.Approval
import de.moltenKt.paper.tool.permission.hasApproval

class InterchangeStructure(
	override var identity: String = UUID.randomString(),
	override var address: Address<InterchangeStructure> = Address.address("/"),
	override var subBranches: List<InterchangeStructure> = emptyList(),
	configuration: CompletionBranchConfiguration = CompletionBranchConfiguration(),
	override var content: List<CompletionComponent> = emptyList(),
	val parent: InterchangeStructure? = null,
	private var isBranched: Boolean = false,
	var requiredApprovals: List<Approval> = emptyList(),
	var onExecution: (suspend InterchangeAccess.() -> InterchangeResult)? = null,
) : TreeBranch<InterchangeStructure, List<CompletionComponent>, TreeBranchType>(
	identity,
	address,
	TreeBranchType.OBJECT,
	subBranches,
	content
) {

	var configuration = configuration
		private set

	fun buildSyntax() = buildString {
		fun construct(level: Int = 0, subBranches: List<InterchangeStructure>) {
			subBranches.forEach { subBranch ->
				val branchConfig = subBranch.configuration
				appendLine(buildString {
					repeat(level) { append("  ") }
					if (level > 0) append("|- ") else append("/")
					append(buildString {
						if (level > 0) append("(")

						append(subBranch.content.joinToString("|") { it.label }.let { display ->
							display.ifBlank { subBranch.identity }
						})

						if (level > 0) {
							append(")")
							if (subBranch.isRoot && subBranch.onExecution != null) append("<")
							if (!branchConfig.isRequired) append("?")
							if (!branchConfig.ignoreCase) append("^")
							if (branchConfig.mustMatchOutput) append("=")
							if (branchConfig.infiniteSubParameters) append("*")

						}
					})
				})
				if (subBranch.subBranches.isNotEmpty())
					construct(level + 1, subBranch.subBranches)
			}
		}

		construct(subBranches = listOf(this@InterchangeStructure))

	}

	fun content(vararg completionComponents: CompletionComponent) =
		content(content = completionComponents.toList())

	fun execution(process: (suspend InterchangeAccess.() -> InterchangeResult)?) {
		onExecution = process
	}

	fun requiredApproval(vararg approvals: Approval) {
		requiredApprovals = approvals.toList()
	}

	/**
	 * This function represents the [execution] function, but returns
	 * [result] internally as the [InterchangeResult] instead of the
	 * returned [InterchangeResult] from the [process].
	 * @param process the code, that the interchange executor triggers
	 * @author Fruxz
	 * @since 1.0
	 */
	@JvmName("executionWithoutReturn")
	fun concludedExecution(result: InterchangeResult = SUCCESS, process: InterchangeAccess.() -> Unit) {
		onExecution = {
			process()
			result
		}
	}

	private fun isInputAllowedByTypes(input: String) =
		content.flatMap { if (it is CompletionComponent.Asset) it.asset.supportedInputType else emptyList() }
			.let { internal ->
				if (content.filterIsInstance<CompletionComponent.Static>().isNotEmpty()) {
					true
				} else if (internal.isNotEmpty()) {
					return@let internal.any { it.isValid(input) }
				} else
					true
			}

	private fun computeLocalCompletion() = content.flatMap { it.completion() }

	private fun validInput(input: String) =
		(!configuration.mustMatchOutput || this.computeLocalCompletion()
			.any { it.equals(input, configuration.ignoreCase) })
				&& configuration.supportedInputTypes.any { it.isValid(input) }
				&& isInputAllowedByTypes(input)
				&& (!configuration.isRequired || input.isNotBlank())


	enum class BranchStatus {
		MATCHING,
		OVERFLOW,
		INCOMPLETE,
		NO_DESTINATION,
		FAILED;
	}

	fun trace(inputQuery: List<String>, executor: InterchangeExecutor?, availableExecutionRepresentsSolution: Boolean = true): CompletionTraceResult<InterchangeStructure> {
		val waysMatching = mutableListOf<PossibleTraceWay<InterchangeStructure>>()
		val waysOverflow = mutableListOf<PossibleTraceWay<InterchangeStructure>>()
		val waysIncomplete = mutableListOf<PossibleTraceWay<InterchangeStructure>>()
		val waysFailed = mutableListOf<PossibleTraceWay<InterchangeStructure>>()
		val waysNoDestination = mutableListOf<PossibleTraceWay<InterchangeStructure>>()

		fun innerTrace(currentBranch: InterchangeStructure, currentDepth: Int, parentBranchStatus: BranchStatus) {
			debugLog("tracing branch ${currentBranch.identity}[${currentBranch.address}] with depth '$currentDepth' from parentStatus $parentBranchStatus")
			val currentLevelInput = inputQuery.getOrNull(currentDepth) ?: ""
			val currentSubBranches = currentBranch.subBranches
			val currentInputValid = currentBranch.validInput(currentLevelInput)
			val currentResult: BranchStatus
			val isAccessTargetValidRoot = currentBranch.isRoot && inputQuery.isEmpty() && availableExecutionRepresentsSolution && currentBranch.onExecution != null

			// CONTENT START

			when (parentBranchStatus) {
				FAILED -> currentResult = FAILED
				INCOMPLETE -> currentResult = INCOMPLETE
				NO_DESTINATION, OVERFLOW, MATCHING -> {

					if (currentInputValid) {
						if (currentBranch.parent?.isRoot == true || (waysMatching + waysOverflow + waysNoDestination).any { t -> t.address == currentBranch.parent?.address }) {
							currentResult = if (currentBranch.subBranches.any { it.configuration.isRequired } && inputQuery.lastIndex < currentDepth) {
								INCOMPLETE
							} else if (currentDepth >= inputQuery.lastIndex || currentBranch.configuration.infiniteSubParameters) {
								if ((currentSubBranches.isNotEmpty() && currentSubBranches.all { it.configuration.isRequired }) && !(availableExecutionRepresentsSolution && currentBranch.onExecution != null)) {
									NO_DESTINATION // This branch has to be completed with its sub-branches
								} else {
									if (currentBranch.requiredApprovals.all { executor?.hasApproval(it) != false }) { // check if executor has all required approvals
										MATCHING
									} else
										FAILED // not enough approvals!
								}
							} else {
								OVERFLOW
							}

						} else {
							currentResult = if (waysIncomplete.any { t -> t.address == currentBranch.parent?.address }) {
								INCOMPLETE
							} else {
								FAILED
							}
						}

					} else if (isAccessTargetValidRoot) {
						currentResult = MATCHING
					} else {
						currentResult = if (((waysMatching + waysOverflow + waysNoDestination).any { it.address == currentBranch.parent?.address } && currentLevelInput.isBlank()) || (currentBranch.parent?.isRoot == true && inputQuery.isEmpty())) {
							INCOMPLETE
						} else {
							FAILED
						}
					}
				}
			}

			// CONTENT END

			debugLog("branch ${currentBranch.identity}[${currentBranch.address}] with depth '$currentDepth' from parentStatus $parentBranchStatus is $currentResult")

			val outputBuild = PossibleTraceWay(
				address = currentBranch.address,
				branch = currentBranch,
				cachedCompletion = currentBranch.computeLocalCompletion(),
				tracingDepth = currentDepth,
				usedQueryState = inputQuery,
			)

			when (currentResult) {
				FAILED -> waysFailed.add(outputBuild)
				OVERFLOW -> waysOverflow.add(outputBuild)
				INCOMPLETE -> waysIncomplete.add(outputBuild)
				MATCHING -> waysMatching.add(outputBuild)
				NO_DESTINATION -> waysNoDestination.add(outputBuild)
			}

			if (!currentBranch.isRoot) {
				currentSubBranches.forEach {
					innerTrace(it, currentDepth + 1, currentResult)
				}
			}

		}

		(subBranches + this).forEach {
			innerTrace(it, 0, MATCHING)
		}

		return CompletionTraceResult(
			waysMatching = waysMatching,
			waysOverflow = waysOverflow,
			waysIncomplete = waysIncomplete,
			waysFailed = waysFailed,
			waysNoDestination = waysNoDestination,
			traceBase = this,
			executedQuery = inputQuery
		)
	}

	fun validateInput(parameters: List<String>, executor: InterchangeExecutor?): Boolean {
		val trace = trace(parameters, executor)

		return if (trace.conclusion == EMPTY && parameters.isEmpty()) {
			true
		} else
			trace.waysMatching.isNotEmpty()
	}

	fun computeCompletion(parameters: List<String>, executor: InterchangeExecutor?): List<String> {

		val query = (parameters.lastOrNull() ?: "")
		val traceBase = parameters.dropLast(1)
		val tracing = trace(traceBase, executor)
		val tracingContent = tracing.let { return@let (it.waysIncomplete + it.waysMatching + it.waysNoDestination) }
		val filteredContent = tracingContent.filter { it.tracingDepth == parameters.lastIndex }
		val flattenedContentCompletion = filteredContent.flatMap { it.cachedCompletion }
		val distinctCompletion = flattenedContentCompletion.distinct()
		val partitionedCompletion = distinctCompletion.partition { it.startsWith(query, true) }
		val mergedCompletion = partitionedCompletion.let { partitioned ->
			return@let partitioned.first + partitioned.second.filter { it.contains(query, true) }
		}

		return mergedCompletion
	}

	/**
	 * @throws IllegalStateException if the branch already has sub-branches
	 * @throws IllegalStateException if the parent-branch is non-required, but this branch is configured as required
	 */
	fun configure(process: CompletionBranchConfiguration.() -> Unit) {
		if (!isBranched) {
			val newConfiguration = configuration.apply(process)

			if (parent != null && !parent.configuration.isRequired && newConfiguration.isRequired)
				throw IllegalStateException("Cannot set required on a non-required branch path (parent is non-required)")

			configuration = newConfiguration
		} else throw IllegalStateException("Cannot configure a branched, that already has sub-branches")
	}

	/**
	 * @throws IllegalStateException if the new branch follows an infinite-parameter branch
	 */
	fun branch(
		identity: String = (parent?.identity ?: "") + "/way-${parent?.subBranches ?: 0}",
		path: Address<InterchangeStructure> = this.address / identity,
		configuration: CompletionBranchConfiguration = CompletionBranchConfiguration(),
		process: InterchangeStructure.() -> Unit,
	) {

		if (parent != null && parent.configuration.infiniteSubParameters)
			throw IllegalStateException("Cannot branch a branch with infinite sub-parameters")

		isBranched = true
		subBranches += InterchangeStructure(
			identity = identity,
			address = path,
			subBranches = emptyList(),
			configuration = configuration,
			content = emptyList(),
			parent = this,
			requiredApprovals = requiredApprovals,
		).apply(process)
	}

	fun addContent(vararg components: CompletionComponent) {
		content += components
	}

	fun addContent(vararg statics: String) =
		addContent(CompletionComponent.static(statics.toSet()))

	fun addContent(vararg assets: CompletionAsset<*>) =
		addContent(components = assets.map { CompletionComponent.asset(it) }.toTypedArray())

	fun addApprovalRequirement(vararg approval: Approval) {
		requiredApprovals += approval
	}

}

fun buildInterchangeStructure(
	path: Address<InterchangeStructure> = Address.address("/"),
	subBranches: List<InterchangeStructure> = emptyList(),
	configuration: CompletionBranchConfiguration = CompletionBranchConfiguration(),
	content: List<CompletionComponent> = emptyList(),
	requiredApprovals: List<Approval> = emptyList(),
	process: InterchangeStructure.() -> Unit,
) = InterchangeStructure("root", path, subBranches, configuration, content, requiredApprovals = requiredApprovals).apply(process)

fun emptyInterchangeStructure() =
	buildInterchangeStructure(process = { })