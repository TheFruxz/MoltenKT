package de.jet.javacord.interchange

import de.jet.jvm.tool.smart.Producible
import org.javacord.api.interaction.SlashCommand
import org.javacord.api.interaction.SlashCommandOptionBuilder
import org.javacord.api.interaction.SlashCommandOptionType
import org.javacord.api.interaction.SlashCommandOptionType.STRING

class JavaCordInterchange(
	override val name: String,
	override val description: String = "",
	override val path: String = "/",
	override val branches: List<JavaCordInterchangeBranch> = emptyList(),
	override var branchType: SlashCommandOptionType,
	override var content: ((parameters: List<String>) -> Unit)? = null,
) : JavaCordInterchangeStructure(name, description, path, branchType, branches) {

	val builder: Builder
		get() = Builder(name, path, description, branches.toMutableList(), branchType, content)

	data class Builder(
		var name: String,
		var description: String,
		var path: String,
		var branches: MutableList<JavaCordInterchangeBranch> = mutableListOf(),
		var type: SlashCommandOptionType = STRING,
		var content: ((parameters: List<String>) -> Unit)? = null
	) : Producible<JavaCordInterchange> {

		infix operator fun plus(branch: JavaCordInterchangeBranch) {
			branches.add(branch)
		}

		fun branch(name: String, content: ((parameters: List<String>) -> Unit)?) {
			branches.add(JavaCordInterchangeBranch(name, STRING, "$path/$name", emptyList(), content))
		}

		infix fun content(content: ((parameters: List<String>) -> Unit)?) = apply {
			this.content = content
		}

		infix fun inputType(type: SlashCommandOptionType) = apply {
			this.type = type
		}

		override fun produce() = JavaCordInterchange(name, description, path, branches, type, content)

	}

	fun computeSlashCommand() =
		SlashCommand.with(name, description, SlashCommandOptionBuilder().apply {

			setType(branchType)
			setName(name)
			setDescription("test")
			setOptions(emptyList())

			fun addBranches(optionBuilder: SlashCommandOptionBuilder, branches: List<JavaCordInterchangeBranch>): (SlashCommandOptionBuilder) -> Unit = {

				for (branch in branches) {
					val builder = SlashCommandOptionBuilder()

					builder.setType(branch.branchType)
					builder.setName(branch.branchName)
					builder.setDescription("test")
					builder.setOptions(emptyList())

					optionBuilder.addOption(builder.build())

					apply(addBranches(builder, branch.branches))
				}

			}

			if (branches.isNotEmpty()) {
				apply(addBranches(this, branches))
			}
		})

}

/*

How interchanges should work:

buildJavaCordInterchange(...) {
	branch("test") {
		...
	}
	branch("test2") {
		content { arguments ->
			println("Your arguments: $arguments")
		}
		inputType(InputType.USER)
	}
}
 |
 |
\ /
 v

 How to use?

 Use it like a normal slash command:
 /test2 <TheFruxz>

 The output is:
 Your arguments: TheFruxz

 */