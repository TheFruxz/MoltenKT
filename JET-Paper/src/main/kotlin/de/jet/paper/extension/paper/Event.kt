package de.jet.paper.extension.paper

import de.jet.paper.runtime.event.interact.PlayerInteractAtBlockEvent
import org.bukkit.Location
import org.bukkit.event.block.Action
import org.bukkit.event.block.Action.PHYSICAL
import org.bukkit.event.player.PlayerInteractEvent

/**
 * This value represents the equal-check if the
 * action is [PHYSICAL].
 * @sample isPhysical
 */
val Action.isPhysical: Boolean
	get() = this == PHYSICAL

/**
 * If a block is clicked, the location, where a new block will be placed returns.
 * This means, if you place a block on the top of another block, the location
 * gets returned of the possible/futuristic block-location of the newly appearing/
 * placed block.
 */
val PlayerInteractAtBlockEvent.realAffectedBlock: Location
	get() = block.location.toBlockLocation().add(origin.blockFace.direction)

/**
 * If a block is clicked, the location, where a new block will be placed returns.
 * This means, if you place a block on the top of another block, the location
 * gets returned of the possible/futuristic block-location of the newly appearing/
 * placed block.
 */
val PlayerInteractEvent.realAffectedBlock: Location?
	get() = clickedBlock?.location?.toBlockLocation()?.add(blockFace.direction)
