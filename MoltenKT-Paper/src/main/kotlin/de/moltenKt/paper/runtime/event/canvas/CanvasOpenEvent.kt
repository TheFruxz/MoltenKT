package de.moltenKt.paper.runtime.event.canvas

import de.moltenKt.paper.tool.display.canvas.Canvas
import net.kyori.adventure.key.Key
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.inventory.Inventory

open class CanvasOpenEvent(
	val player: Player,
	override val canvas: Canvas,
	val inventory: Inventory,
	val data: Map<Key, Any>,
) : CanvasEvent(player, canvas) {

	override fun getHandlers() = handlerList

	companion object {

		private val handlerList = HandlerList()

		@JvmStatic
		fun getHandlerList() = handlerList

	}

}
