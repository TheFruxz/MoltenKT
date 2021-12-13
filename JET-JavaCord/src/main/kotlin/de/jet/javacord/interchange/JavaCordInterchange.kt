package de.jet.javacord.interchange

class JavaCordInterchange(
	override val name: String,
	override val path: String = "/",
	override val branches: List<JavaCordInterchangeBranch> = emptyList()
) : JavaCordInterchangeStructure(name, path, branches)


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