package de.jet.javacord.interchange

import de.jet.jvm.interchange.InterchangeStructureBranch
import org.javacord.api.interaction.SlashCommandOptionType

class JavaCordInterchangeBranch(
	override val branchName: String,
	val branchType: SlashCommandOptionType = SlashCommandOptionType.STRING,
	override val path: String = "/",
	override val branches: List<InterchangeStructureBranch> = emptyList(),
) : InterchangeStructureBranch(branchName, path, branches)