package de.moltenKt.paper.structure.app.interchange

import de.moltenKt.paper.extension.display.notification
import de.moltenKt.paper.structure.command.Interchange
import de.moltenKt.paper.structure.command.InterchangeResult.SUCCESS
import de.moltenKt.paper.structure.command.completion.emptyInterchangeStructure
import de.moltenKt.paper.structure.command.execution
import de.moltenKt.paper.tool.display.message.Transmission.Level.ERROR
import de.moltenKt.unfold.extension.dyeGray
import de.moltenKt.unfold.extension.dyeRed
import de.moltenKt.unfold.extension.dyeYellow
import de.moltenKt.unfold.extension.style
import de.moltenKt.unfold.plus
import de.moltenKt.unfold.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration.BOLD

class IssuedInterchange(
	label: String,
	aliases: Set<String>,
) : Interchange(
	label = label,
	aliases = aliases,
	protectedAccess = false,
	completion = emptyInterchangeStructure(),
) {

	override val execution = execution {

		text {
			this + text("Oops!").style(NamedTextColor.RED, BOLD)
			this + text(" This interchange ").dyeGray()
			this + text("crashed").dyeRed()
			this + text(" during the ").dyeGray()
			this + text("registration").dyeYellow()
			this + text(" process, please report this to a technician!").dyeGray()
		}.notification(ERROR, executor).display()

		SUCCESS
	}

}