package de.jet.javacord.extension

fun buildJavaCordInterchange(name: String, process: JavaCordInterchange.() -> Unit) {
    JavaCordInterchange(name).apply(process)
}