package de.moltenKt.paper.extension.mojang

import de.moltenKt.core.extension.data.jsonBase
import de.moltenKt.paper.app.MoltenApp
import de.moltenKt.paper.mojang.MojangProfile
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.decodeFromJsonElement
import java.util.*

/**
 * throws exception if no user is found by the [profileQuery]
 */
@Throws(NullPointerException::class)
suspend fun getMojangProfile(profileQuery: String) =
	jsonBase.decodeFromJsonElement<MojangProfile>(
		MoltenApp.instance.httpClient.request("https://api.ashcon.app/mojang/v2/user/$profileQuery").body()
	)


/**
 * throws exception if no user is found by the [profileQuery]
 */
suspend fun getMojangProfile(uuid: UUID) = getMojangProfile("$uuid")