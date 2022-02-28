package de.jet.paper.extension.paper.event

import de.jet.jvm.extension.data.buildRandomTag
import de.jet.paper.app.JetCache
import de.jet.paper.app.component.eventAttach.EventAttachComponent
import de.jet.paper.extension.paper.identityObject
import de.jet.paper.structure.app.event.EventListener
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityEvent
import org.bukkit.event.player.PlayerEvent
import kotlin.reflect.full.functions

fun <T : PlayerEvent> OfflinePlayer.attachEvent(onlyThisPlayer: Boolean = true, process: T.() -> Unit) {
	val computedListener = object : EventListener() {

		override val listenerIdentity = "PLAYER_${name}_${buildRandomTag(hash = false)}"

		@EventHandler
		fun onEvent(event: T) {
			if (onlyThisPlayer || event.player.uniqueId == uniqueId) { process(event) }
		}

	}

	EventAttachComponent.instance.attachListener(computedListener)
	JetCache.playerBoundListener[identityObject]?.add(computedListener)
}

inline fun <reified T : EntityEvent> Entity.attachEvent(onlyThisEntity: Boolean = true, crossinline process: T.() -> Unit) {
	val computedListener = object : EventListener() {

		override val listenerIdentity = "ENTITY_${name}_${buildRandomTag(hash = false)}"

		fun test() {
			this::class.functions.first().typeParameters.first().variance
		}

		@EventHandler
		fun onEvent(event: T) {
			if (onlyThisEntity || event.entity.uniqueId == uniqueId) { process(event) }
		}

	}

	EventAttachComponent.instance.attachListener(computedListener)
	JetCache.entityBoundListener[identityObject]?.add(computedListener)
}