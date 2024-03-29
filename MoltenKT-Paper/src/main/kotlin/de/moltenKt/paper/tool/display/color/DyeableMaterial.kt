package de.moltenKt.paper.tool.display.color

import de.moltenKt.core.extension.container.replace
import de.moltenKt.paper.extension.paper.createKey
import de.moltenKt.paper.extension.paper.createNamespacedKey
import de.moltenKt.paper.extension.system
import de.moltenKt.paper.tool.display.color.ColorType.WHITE
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import org.bukkit.Material

enum class DyeableMaterial(
	val produceMaterialProcess: ColorType.() -> Material,
) : Keyed {

	WOOL(ColorType::wool),
	TERRACOTTA(ColorType::terracotta),
	CONCRETE(ColorType::concrete),
	CONCRETE_POWDER(ColorType::concretePowder),
	CARPET(ColorType::carpet),
	STAINED_GLASS(ColorType::stainedGlass),
	STAINED_GLASS_PANE(ColorType::stainedGlassPane),
	SHULKER_BOX(ColorType::shulker),
	GLAZED_TERRACOTTA(ColorType::glazedTerracotta),
	BED(ColorType::bed),
	BANNER(ColorType::banner),
	BANNER_WALL(ColorType::bannerWall),
	DYE(ColorType::dyeMaterial),
	CANDLE(ColorType::candle),
	CANDLE_CAKE(ColorType::candleCake);

	fun withColor(color: ColorType) = produceMaterialProcess(color)

	override fun key(): Key = system.createKey(name)

	companion object {

		@JvmStatic
		fun fromMaterial(material: Material) = try {
			valueOf(material.name.replace(
				"LIGHT_BLUE" to "LIGHTBLUE",
				"LIGHT_GRAY" to "LIGHTGRAY",
			).substringAfter('_'))
		} catch (exception: IllegalArgumentException) {
			null
		}

		@JvmStatic
		fun materialFromMaterialCode(materialCode: String): Material? {
			val code = materialCode.lowercase().removePrefix("minecraft:").removePrefix("moltenkt:").uppercase()
			val output = if (code.contains('#')) {
				val split = code.split('#')

				if (split.size == 2) {

					try {
						val material = valueOf(split.first())
						val colorType = ColorType.valueOf(split.last())

						material.withColor(colorType)

					} catch (exception: NoSuchElementException) {
						null
					}

				} else
					null

			} else
				try {
					valueOf(code).withColor(WHITE)
				} catch (exception: NoSuchElementException) {
					null
				}

			return output ?: Material.matchMaterial(code)
		}

	}

}
