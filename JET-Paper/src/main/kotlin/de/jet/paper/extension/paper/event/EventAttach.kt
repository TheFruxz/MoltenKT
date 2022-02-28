package de.jet.paper.extension.paper.event

import de.jet.paper.app.component.eventAttach.EventAttachComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityEvent

/*inline fun <reified T> OfflinePlayer.attachEvent(onlyThisPlayer: Boolean = true, crossinline process: T.() -> Unit) {
	val computedListener = object : EventListener() {

		override val listenerIdentity = "PLAYER_${name}_${buildRandomTag(hash = false)}"

		@EventHandler
		fun onEvent(event: T) {
			if (event is PlayerEvent) {
				if (onlyThisPlayer || event.player.uniqueId == uniqueId) {
					process(event)
				}
			}
		}

	}

	EventAttachComponent.instance.vendor.add(computedListener)
	EventAttachComponent.instance.attachListener(computedListener)
	JetCache.playerBoundListener[identityObject]?.add(computedListener)
}*/

inline fun <reified T : EntityEvent> Entity.attachEvent(onlyThisEntity: Boolean = true, crossinline process: T.() -> Unit) {
	Bukkit.getPluginManager().registerEvents(object : Listener {

		@EventHandler
		fun onEvent(event: T) {
			if (onlyThisEntity || event.entity.uniqueId == uniqueId) {
				process(event)
			}
		}

	}, EventAttachComponent.instance.vendor)
}