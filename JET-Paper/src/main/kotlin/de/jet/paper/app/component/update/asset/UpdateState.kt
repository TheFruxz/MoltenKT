package de.jet.paper.app.component.update.asset

import de.jet.paper.structure.app.update.AppConfigurationFile
import io.ktor.http.*
import java.nio.file.Path

data class UpdateState(
    val localVersion: String,
    val removeVersion: String,
    val remoteFile: Url,
    val localFile: Path,
    val localConfiguration: AppConfigurationFile,
)
