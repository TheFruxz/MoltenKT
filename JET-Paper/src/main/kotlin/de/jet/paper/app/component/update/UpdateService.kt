package de.jet.paper.app.component.update

import de.jet.jvm.tool.smart.identification.Identifiable
import de.jet.jvm.tool.smart.identification.Identity
import de.jet.paper.app.JetCache
import de.jet.paper.app.component.update.asset.UpdateState
import de.jet.paper.extension.debugLog
import de.jet.paper.extension.mainLog
import de.jet.paper.extension.system
import de.jet.paper.structure.app.App
import de.jet.paper.structure.service.Service
import de.jet.paper.tool.timing.tasky.Tasky
import de.jet.paper.tool.timing.tasky.TemporalAdvice
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.collections.set
import kotlin.time.Duration.Companion.minutes

internal class UpdateService(override val vendor: Identifiable<App> = system) : Service {

    override val thisIdentity = "Updates"

    override val temporalAdvice = TemporalAdvice.ticking(1.minutes)

    override val process: Tasky.() -> Unit = {
        debugLog("Service '$identity' is now checking for app-updates...")
        val output = mutableMapOf<Identity<out App>, UpdateState>()

        JetCache.registeredApplications.forEach {
            debugLog("Checking ${it.identity} for app-update...")

            val configuration = it.appConfigurationFile

            if (configuration != null) {

                runBlocking {
                    val config = it.appConfigurationFile
                    val url = config?.githubUrl

                    if (config != null && url != null) {
                        val requestBody = it.httpClient.get(url).body<JsonObject>()

                        requestBody["tag_name"].let { json ->
                            val currentVersion = json?.jsonPrimitive?.toString()
                            val localVersion = it.description.version

                            if (currentVersion == localVersion) {
                                output[it.identityObject] = UpdateState(
                                    localVersion = localVersion,
                                    removeVersion = currentVersion,
                                    remoteFile = requestBody["assets"]?.jsonArray?.mapNotNull { jsElement ->
                                        jsElement.jsonObject["browser_download_url"]?.jsonPrimitive?.contentOrNull
                                    }?.first { first -> first.split("/").lastOrNull()?.matches(config.fileFormatRegex) == true }?.let { content -> Url(content) },
                                    localFile = it.pluginFile.toPath(),
                                    localConfiguration = config,
                                )
                                mainLog.warning("You're running the latest version! ($currentVersion)")
                            } else {
                                mainLog.warning("You're currently running ${it.identity} version ${localVersion}, but the current version is $currentVersion!")
                            }

                        }
                    } else
                        debugLog("No github-url found in configuration file!")
                }

            } else
                debugLog("App ${it.identity} has no configuration file! Skip update")
        }

        JetCache.updateStates.apply {
            clear()
            putAll(output)
            debugLog("Successfully swapped updateStates content!")
        }
    }

}