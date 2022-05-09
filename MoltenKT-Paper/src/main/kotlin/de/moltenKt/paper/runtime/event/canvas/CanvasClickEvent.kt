package de.moltenKt.paper.runtime.event.canvas

import de.moltenKt.paper.extension.display.ui.item
import de.moltenKt.paper.tool.display.canvas.Canvas
import de.moltenKt.paper.tool.event.KCancellable
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView

data class CanvasClickEvent(
	val player: Player,
	override val canvas: Canvas,
	val slot: Int,
	val view: InventoryView,
	override var eventCancelled: Boolean = false,
	val originEvent: InventoryClickEvent,
) : CanvasEvent(player, canvas, isAsync = false), KCancellable {

	val action by lazy {
		originEvent.action
	}

	val click by lazy {
		originEvent.click
	}

	val slotType by lazy {
		originEvent.slotType
	}

	val rawSlot by lazy {
		originEvent.rawSlot
	}

	val clickedItemStack by lazy {
		originEvent.currentItem
	}

	val clickedItem by lazy {
		clickedItemStack?.takeIf { it.hasItemMeta() }?.item ?: originEvent.cursor?.takeIf { it.hasItemMeta() }?.item
	}

	val clickedInventory by lazy {
		originEvent.clickedInventory!! /* TODO trigger have to check this not to be null!!! */
	}

	override fun getHandlers() = handlerList

	companion object {

		private val handlerList = HandlerList()

		@JvmStatic
		fun getHandlerList() = handlerList

	}

}