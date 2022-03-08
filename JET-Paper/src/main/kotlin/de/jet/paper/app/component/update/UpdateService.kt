package de.jet.paper.app.component.update

import de.jet.jvm.tool.smart.identification.Identifiable
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
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Duration.Companion.minutes

internal class UpdateService(override val vendor: Identifiable<App> = system) : Service {

    override val thisIdentity = "Updates"

    override val temporalAdvice = TemporalAdvice.ticking(1.minutes)

    override val process: Tasky.() -> Unit = {
        debugLog("Service '$identity' is now checking for app-updates...")
        val output = mutableListOf<UpdateState>()

        JetCache.registeredApplications.forEach {
            debugLog("Checking ${it.identity} for app-update...")

            val configuration = it.appConfigurationFile

            if (configuration != null) {

                runBlocking {
                    it.httpClient.get(urlString = "https://api.github.com/repos/TheFruxz/JET/releases/latest")
                        .body<JsonObject>()["tag_name"].let { json ->
                        val currentVersion = json?.jsonPrimitive?.toString()



                        if (currentVersion == it.description.version) {
                            mainLog.warning("You're running the latest version! ($currentVersion)")
                        } else {
                            mainLog.warning( "You're currently running ${it.identity} version ${it.description.version}, but the current version is $currentVersion!")
                        }
                    }
                }

            } else
                debugLog("App ${it.identity} has no configuration file! Skip update")
        }
    }

}