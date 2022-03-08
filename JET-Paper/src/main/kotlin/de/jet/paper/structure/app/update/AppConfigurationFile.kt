package de.jet.paper.structure.app.update

import io.ktor.http.*

@kotlinx.serialization.Serializable
data class AppConfigurationFile(
    val github: String,
    val fileFormat: String,
) {

    val githubUrl: Url
        get() = Url(github)

    val fileFormatRegex: Regex
        get() = Regex(fileFormat)

}