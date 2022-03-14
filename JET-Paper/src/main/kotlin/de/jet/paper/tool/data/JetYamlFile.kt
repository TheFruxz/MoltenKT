package de.jet.paper.tool.data

import de.jet.jvm.tool.smart.identification.Identifiable
import de.jet.paper.extension.paper.bukkitVersion
import de.jet.paper.structure.app.App
import de.jet.paper.structure.component.Component
import de.jet.paper.tool.smart.VendorsIdentifiable
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.div
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.reader
import kotlin.io.path.writeText

interface JetYamlFile : JetFile {

	companion object {

		private fun generateYaml(path: Path) =
			object : JetFile {

				override val file =
					path.apply {
						if (!parent.exists())
							parent.createDirectories()
						if (!exists())
							createFile()
					}

				val noPath = file
				val reader = noPath.reader()
				val yaml = YamlConfiguration.loadConfiguration(reader)

				override fun load() {
					yaml.loadFromString(noPath.readText())
				}

				override fun save() {
					noPath.writeText(yaml.saveToString())
				}

				override fun contains(path: String) =
					yaml.contains(path)

				override fun <T : Any?> set(path: String, value: T) {
					yaml.set(path, value)
				}

				@Suppress("UNCHECKED_CAST")
				override fun <T> get(path: String): T? {
					val get = yaml.get(path)

					return try {
						get as T?
					} catch (e: ClassCastException) {
						null
					}

				}

			}

		fun appFile(
			vendor: Identifiable<App>,
			fileName: String,
			extension: String = "yml"
		) =
			generateYaml(
				Path("JETData", "#${vendor.identity}", "$fileName.$extension")
			)

		fun rootFile(fileName: String, extension: String = "yml") =
			generateYaml(Path("JETData") / "ROOT" / "$fileName.$extension")

		fun componentFile(component: VendorsIdentifiable<Component>, fileName: String, extension: String = "yml"): JetFile =
			generateYaml(Path("JETData") / "#${component.identity}@${component.vendorIdentity.identity}" / "$fileName.$extension")

		internal fun dummyComponentFile(dataA: String, dataB: String, fileName: String, extension: String = "yml"): JetFile =
			generateYaml(Path("JETData") / "#$dataA@$dataB" / "$fileName.$extension")

		fun versionFile(fileName: String, extension: String = "yml") =
			generateYaml(Path("JETData") / bukkitVersion / "$fileName.$extension")

	}

}