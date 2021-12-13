package de.jet.javacord.extension

import de.jet.javacord.interchange.JavaCordInterchange
import de.jet.javacord.interchange.JavaCordInterchangeBranch
import org.javacord.api.interaction.SlashCommandOptionType

fun buildJavaCordInterchange(name: String, process: JavaCordInterchange.() -> Unit) {
    JavaCordInterchange(name).apply(process)
}

fun JavaCordInterchangeBranch.branch(name: String, process: JavaCordInterchangeBranch.() -> Unit) {
    // TODO: 13.12.21 branch
}

fun JavaCordInterchangeBranch.type(type: SlashCommandOptionType) {
    // TODO: 13.12.21 branchType
}

fun JavaCordInterchangeBranch.content(process: JavaCordInterchangeBranch.() -> Unit) {
    // TODO: 13.12.21 content on execution
}