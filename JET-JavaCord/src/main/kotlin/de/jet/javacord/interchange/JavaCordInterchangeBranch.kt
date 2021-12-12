package de.jet.javacord.interchange

import de.jet.jvm.interchange.InterchangeStructureBranch

class JavaCordInterchangeBranch(
	override val branchName: String, override val path: String,
	override val branches: List<InterchangeStructureBranch>
) : InterchangeStructureBranch(branchName, path, branches)