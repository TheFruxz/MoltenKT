package de.jet.minecraft.structure.command.live

import de.jet.library.tool.smart.Identifiable
import de.jet.minecraft.structure.app.App
import de.jet.minecraft.structure.command.Interchange
import de.jet.minecraft.structure.command.InterchangeExecutorType
import de.jet.minecraft.tool.smart.Logging
import org.bukkit.command.CommandSender
import java.util.logging.Level

data class InterchangeAccess(
	override val vendor: Identifiable<App>,
	val executorType: InterchangeExecutorType,
	val executor: CommandSender,
	val interchange: Interchange,
	val label: String,
	val parameters: List<String>,
) : Logging {

	override val sectionLabel = "InterchangeRun/$vendor:$label"

	fun interchangeLog(level: Level = Level.INFO, message: String) = sectionLog.log(level, message)

	val checkParameter = ValidationData(this)

	data class ValidationData(
		private val access: InterchangeAccess,
	) {

		operator fun get(slot: Int) = with(access) {
			val completion = interchange.completion
			val currentSection = completion.sections.getOrNull(slot)

			return@with if (currentSection != null) {

				currentSection.inputExpressionCheck(parameters.getOrNull(slot) ?: "")

			} else
				completion.infinite && completion.sections.lastIndex < parameters.lastIndex

		}

	}

}