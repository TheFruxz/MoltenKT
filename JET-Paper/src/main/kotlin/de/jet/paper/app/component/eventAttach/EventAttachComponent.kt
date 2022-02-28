package de.jet.paper.app.component.eventAttach

import de.jet.paper.app.JetCache
import de.jet.paper.extension.debugLog
import de.jet.paper.structure.app.event.EventListener
import de.jet.paper.structure.component.Component.RunType.ENABLED
import de.jet.paper.structure.component.SmartComponent

class EventAttachComponent : SmartComponent(ENABLED, true) {

	override val thisIdentity = "EventAttaching"

	override suspend fun component() {
		instance = this

		(JetCache.entityBoundListener.values.flatten() + JetCache.playerBoundListener.values.flatten()).forEach {
			attachListener(it)
		}

	}

	fun attachListener(eventListener: EventListener) {
		listeners.add(eventListener)

		if (isRunning) {
			vendor.add(eventListener)
		}

		JetCache.registeredListeners.add(eventListener)
		debugLog("Listener '${eventListener.identity}' added through '$identity' with the attachListener function!")
	}

	companion object {
		lateinit var instance: EventAttachComponent
	}

}