package de.moltenKt.paper.runtime.event.canvas

import de.moltenKt.paper.tool.display.canvas.Canvas
import de.moltenKt.paper.tool.event.KCancellable
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.inventory.Inventory

data class CanvasRenderEvent(
	val player: Player,
	override val canvas: Canvas,
	val renderResult: Inventory,
	override var eventCancelled: Boolean = false
) : CanvasEvent(player, canvas), KCancellable {

	override fun getHandlers() = handlerList

	companion object {

		private val handlerList = HandlerList()

		@JvmStatic
		fun getHandlerList() = handlerList

	}

}
