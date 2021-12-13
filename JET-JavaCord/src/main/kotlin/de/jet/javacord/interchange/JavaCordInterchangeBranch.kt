package de.jet.javacord.interchange

import de.jet.jvm.interchange.InterchangeStructureBranch
import org.javacord.api.interaction.SlashCommandOptionType

open class JavaCordInterchangeBranch(
	override val branchName: String,
	open val branchType: SlashCommandOptionType,
	override val path: String = "/",
	override val branches: List<JavaCordInterchangeBranch> = emptyList(),
	open val content: ((parameters: List<String>) -> Unit)? = null
) : InterchangeStructureBranch(branchName, path, branches)