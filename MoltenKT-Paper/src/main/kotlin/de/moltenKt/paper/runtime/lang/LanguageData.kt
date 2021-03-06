package de.moltenKt.paper.runtime.lang

import de.moltenKt.core.tool.smart.positioning.Address
import java.nio.file.Path

data class LanguageData(val sourceFile: Path, val sourceAddress: Address<Path>, val indexedValue: String)
