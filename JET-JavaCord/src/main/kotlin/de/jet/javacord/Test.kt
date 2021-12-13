package de.jet.javacord

import de.jet.javacord.app.DiscordBotExtension
import de.jet.javacord.extension.buildJavaCordInterchange
import de.jet.jvm.extension.app.runApp
import de.jet.jvm.tool.smart.positioning.Address

fun main() = runApp("test") {

    attachWith(DiscordBotExtension) {

        buildJavaCordInterchange("test") {



        }

    }

}