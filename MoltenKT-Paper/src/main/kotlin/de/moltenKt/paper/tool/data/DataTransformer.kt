package de.moltenKt.paper.tool.data

import de.moltenKt.core.extension.container.toArrayList
import de.moltenKt.core.extension.data.fromJsonString
import de.moltenKt.core.extension.data.toJsonString
import de.moltenKt.paper.tool.display.item.Item
import de.moltenKt.paper.tool.display.world.SimpleLocation
import org.bukkit.Location

data class DataTransformer<SHELL: Any, CORE: Any>(
	val toCore: SHELL.() -> CORE,
	val toShell: CORE.() -> SHELL,
) {

	companion object {

		@JvmStatic
		fun <BOTH : Any> empty() =
			DataTransformer<BOTH, BOTH>({ this }, { this })

		// JSON
		@JvmStatic
		inline fun <reified T : Any> json() =
			DataTransformer<T, String>(
				{ this.toJsonString() },
				{ this.fromJsonString() },
			)

		@JvmStatic
		fun jsonItem() =
			DataTransformer<Item, String>({ produceJson() }, { Item.produceByJson(this)!! })

		// collections

		@JvmStatic
		inline fun <reified SET> setCollection() =
			DataTransformer<Set<SET>, ArrayList<SET>>(
				{ toArrayList() },
				{ toSet() },
			)

		// simple location

		@JvmStatic
		fun simpleLocationBukkit() =
			DataTransformer<Location, SimpleLocation>({ SimpleLocation.ofBukkit(this) }, { bukkit })

		@JvmStatic
		fun simpleLocationListBukkit() =
			DataTransformer<List<Location>, List<SimpleLocation>>({ map { SimpleLocation.ofBukkit(it) }}, { map { it.bukkit } })

		@JvmStatic
		fun simpleLocationArrayBukkit() =
			DataTransformer<Array<Location>, Array<SimpleLocation>>({ map { SimpleLocation.ofBukkit(it) }.toTypedArray()}, { map { it.bukkit }.toTypedArray() })

	}

}
