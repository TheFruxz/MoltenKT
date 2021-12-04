package de.jet.paper.tool.data

import de.jet.jvm.tool.smart.identification.Identifiable

/**
 * The path used inside yaml files
 */
data class JetPath(
	private val base: String,
): Identifiable<JetPath> {

	var fullPath: String = base

	override val identity: String
		get() = fullPath

}