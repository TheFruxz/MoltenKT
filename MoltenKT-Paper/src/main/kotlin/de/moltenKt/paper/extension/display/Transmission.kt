package de.moltenKt.paper.extension.display

import de.moltenKt.paper.extension.interchange.InterchangeExecutor
import de.moltenKt.paper.tool.display.message.Transmission
import de.moltenKt.paper.tool.display.message.Transmission.Level
import de.moltenKt.unfold.extension.asStyledComponent
import de.moltenKt.unfold.extension.lines
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentIteratorType
import net.kyori.adventure.text.ComponentLike

fun Iterable<ComponentLike>.message(vararg participant: InterchangeExecutor) =
	Transmission(content = this.toList().map(ComponentLike::asComponent))
		.participants(participant.toList())

fun Iterable<ComponentLike>.notification(level: Level, vararg participant: InterchangeExecutor) =
	Transmission(content = this.toList().map(ComponentLike::asComponent), level = level)
		.promptSound(level.promptSound)
		.participants(participant.toList())

fun ComponentLike.message(vararg participant: InterchangeExecutor) = lines().message(*participant)

fun ComponentLike.notification(level: Level, vararg participant: InterchangeExecutor) = lines().notification(level, *participant)

fun String.message(vararg participants: InterchangeExecutor) = lines().map(String::asStyledComponent).message(*participants)

fun String.notification(level: Level, vararg participants: InterchangeExecutor) = lines().map(String::asStyledComponent).notification(level, *participants)
