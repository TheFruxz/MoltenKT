package de.moltenKt.paper.tool.position.dependent

import de.moltenKt.core.annotation.NotPerfect
import de.moltenKt.core.extension.math.ceilToInt
import de.moltenKt.core.extension.math.floorToInt
import de.moltenKt.paper.tool.display.world.SimpleLocation
import de.moltenKt.paper.tool.position.relative.PyramidalShape
import kotlinx.serialization.Serializable
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector

@Serializable
@NotPerfect
data class DependentPyramidalShape(
	val peakLocation: SimpleLocation,
	override val height: Double,
	override val groundWidth: Double,
	override val groundDepth: Double,
) : DependentShape, PyramidalShape {

	override val volume: Double by lazy {
		(1/3.0) * (groundWidth * groundDepth) * height
	}

	override val center: SimpleLocation by lazy {
		peakLocation.copy(y = peakLocation.y - (height / 2))
	}

	override val fullHeight: Double = height

	override val fullWidth: Double = groundWidth

	override val fullDepth: Double = groundDepth

	override val blockLocations: List<SimpleLocation> by lazy {
		val locations = mutableListOf<SimpleLocation>()

		for (x in (peakLocation.x-(groundWidth / 2)).floorToInt()..(peakLocation.x+(groundWidth / 2)).ceilToInt()) {
			for (z in (peakLocation.z-(groundDepth / 2)).floorToInt()..(peakLocation.z+(groundDepth / 2)).ceilToInt()) {
				for (y in (peakLocation.y-height).floorToInt()..peakLocation.y.ceilToInt()) {
					if (contains(Vector(x, y, z))) {
						locations.add(SimpleLocation(peakLocation.world, x, y, z))
					}
				}
			}
		}

		return@lazy locations
	}

	override fun contains(vector: Vector): Boolean {
		val pyramidWidthAtHeightY = (1 + (groundWidth * (1 - ((vector.y - (peakLocation.y - height)) / (height + 0))))) * .5
		val pyramidDepthAtHeightY = (1 + (groundDepth * (1 - ((vector.y - (peakLocation.y - height)) / (height + 0))))) * .5

		return vector.x in (peakLocation.x-pyramidWidthAtHeightY)..(peakLocation.x+pyramidWidthAtHeightY) &&
				vector.z in (peakLocation.z-pyramidDepthAtHeightY)..(peakLocation.z+pyramidDepthAtHeightY)
	}

	override fun contains(location: Location): Boolean {
		return contains(location.toVector()) && location.world.name == peakLocation.world
	}

	override fun asShifted(toWorld: World): DependentShape = copy(
		peakLocation = peakLocation.copy(world = toWorld.name)
	)

}