package de.moltenKt.paper.app.component.messaging

import de.moltenKt.paper.extension.display.notification
import de.moltenKt.paper.structure.command.InterchangeUserRestriction
import de.moltenKt.paper.structure.command.completion.buildInterchangeStructure
import de.moltenKt.paper.structure.command.completion.infiniteSubParameters
import de.moltenKt.paper.structure.command.structured.StructuredInterchange
import de.moltenKt.paper.tool.display.message.Transmission
import de.moltenKt.unfold.extension.dyeGray
import de.moltenKt.unfold.extension.dyeRed
import de.moltenKt.unfold.plus
import de.moltenKt.unfold.text
import org.bukkit.entity.Player

internal class ReplyInterchange : StructuredInterchange("reply", userRestriction = InterchangeUserRestriction.ONLY_PLAYERS, structure = buildInterchangeStructure {

    branch {

        addContent("...")
        infiniteSubParameters()

        concludedExecution {
            val player = executor as Player

            if (!Messaging.sendReply(player, parameters.joinToString(" "))) {

                text {
                    this + text("You're").dyeGray()
                    this + text(" not ").dyeRed()
                    this + text("having an active conversation with someone online!").dyeGray()
                }.notification(Transmission.Level.FAIL, executor).display()

            }

        }

    }

})