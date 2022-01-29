package de.jet.paper.runtime.event.interact

import de.jet.paper.app.component.events.JetEventsComponent
import de.jet.paper.tool.annotation.RequiresComponent
import de.jet.paper.tool.display.item.Item
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event.Result.DEFAULT
import org.bukkit.event.HandlerList
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.player.PlayerInteractEvent

@RequiresComponent(JetEventsComponent::class)
data class PlayerInteractAtItemEvent(
	val whoInteract: Player,
	val item: Item,
	val material: Material,
	val action: Action,
	override val origin: PlayerInteractEvent,
	private var isCancelled: Boolean = false,
	override var interactedBlock: Result = DEFAULT,
	override var interactedItem: Result = DEFAULT,
) : JetPlayerInteractEvent, PlayerEvent(whoInteract, false), Cancellable {

	override fun isCancelled() = isCancelled

	override fun setCancelled(cancel: Boolean) {
		isCancelled = cancel
	}

	override fun getHandlers() = handlerList

	companion object {

		private val handlerList = HandlerList()

		@JvmStatic
		fun getHandlerList() = handlerList

	}

}