package de.jet.javacord.extension

import de.jet.javacord.interchange.JavaCordInterchange
import org.javacord.api.interaction.SlashCommandOptionType

fun buildJavaCordInterchange(name: String, process: JavaCordInterchange.Builder.() -> Unit) =
    JavaCordInterchange(name, branchType = SlashCommandOptionType.INTEGER).builder.apply(process).produce()
