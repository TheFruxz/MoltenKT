package de.jet.paper.app.component.update

import de.jet.paper.structure.component.SmartComponent

internal class UpdateComponent : SmartComponent(RunType.AUTOSTART_IMMUTABLE, true){

    override val thisIdentity = "Updates"

    override suspend fun component() {
        service(UpdateService())
    }

}