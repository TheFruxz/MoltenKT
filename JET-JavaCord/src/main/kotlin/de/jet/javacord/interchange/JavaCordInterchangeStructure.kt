package de.jet.javacord.interchange

import de.jet.jvm.interchange.InterchangeStructure
import de.jet.jvm.interchange.InterchangeStructureBranch

open class JavaCordInterchangeStructure(
    name: String,
    path: String = "/",
    branches: List<InterchangeStructureBranch> = emptyList()
) : InterchangeStructure<InterchangeStructureBranch>(name, path, branches)