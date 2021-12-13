package de.jet.javacord.interchange

import de.jet.jvm.interchange.InterchangeStructureBranch
import de.jet.jvm.interchange.InterchangeStructured
import de.jet.jvm.tool.smart.identification.Identifiable
import de.jet.jvm.tool.smart.positioning.Address
import org.javacord.api.interaction.SlashCommandOptionType

open class JavaCordInterchangeStructure(
	override val name: String,
	open val description: String = "",
	override val path: String = "/",
	override val branchType: SlashCommandOptionType,
	override val branches: List<JavaCordInterchangeBranch> = emptyList(),
	override var content: ((parameters: List<String>) -> Unit)? = null,
) : JavaCordInterchangeBranch(name, branchType, path, branches), Identifiable<InterchangeStructureBranch>,
	InterchangeStructured<InterchangeStructureBranch> {

	override val identity: String
		get() = name

	override fun getNearestBranchWithParameters(original: Address<InterchangeStructureBranch>): Pair<InterchangeStructureBranch, String> {

		fun getContent(address: Address<InterchangeStructureBranch>): Pair<InterchangeStructureBranch, String> {
			val currentAddressState = address.addressString.split("/")
			var output: Pair<InterchangeStructureBranch, String>?

			output = getStructureBranches<InterchangeStructureBranch>().firstOrNull { branch ->
				branch.path.removePrefix("/") == currentAddressState.joinToString("/")
			}?.let {
				return@let it to original.addressString.removePrefix(currentAddressState.joinToString("/"))
					.removePrefix("/").split("/").joinToString(" ")
			}

			if (output == null && currentAddressState.size > 1) {
				output = getContent(Address.address(currentAddressState.dropLast(1).joinToString("/")))
			} else if (address == original) {
				return (getStructureBranches<InterchangeStructureBranch>().also { branches ->
					branches.forEach {
						println(it.path)
					}
				}.first() to "")
			}

			return output
				?: (getStructureBranches<InterchangeStructureBranch>().first() to original.addressString.removePrefix("/")
					.split("/").joinToString(" "))
		}

		return getContent(original.copy())
	}

}