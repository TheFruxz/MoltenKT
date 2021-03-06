package de.moltenKt.paper.structure.command.completion

import de.moltenKt.core.extension.empty
import de.moltenKt.core.extension.math.isDouble
import de.moltenKt.core.extension.math.isLong
import de.moltenKt.core.extension.tryOrNull
import de.moltenKt.paper.extension.paper.offlinePlayer
import de.moltenKt.paper.extension.paper.playerOrNull
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*

interface InterchangeStructureInputRestriction<DATATYPE> {

	fun isValid(input: String): Boolean

	fun transformer(input: String): DATATYPE

	companion object {

		@JvmStatic
		val NONE = object : InterchangeStructureInputRestriction<Unit> {
			override fun isValid(input: String) = false
			override fun transformer(input: String) = empty()
		}

		@JvmStatic
		val ANY = object : InterchangeStructureInputRestriction<String> {
			override fun isValid(input: String) = true
			override fun transformer(input: String) = input
		}

		@JvmStatic
		val STRING = object : InterchangeStructureInputRestriction<String> {
			override fun isValid(input: String) = true
			override fun transformer(input: String) = input
		}

		@JvmStatic
		val LONG = object : InterchangeStructureInputRestriction<Long> {
			override fun isValid(input: String) = input.isLong()
			override fun transformer(input: String) = input.toLong()
		}

		@JvmStatic
		val DOUBLE = object : InterchangeStructureInputRestriction<Double> {
			override fun isValid(input: String) = input.isDouble()
			override fun transformer(input: String) = input.toDouble()
		}

		@JvmStatic
		val BOOLEAN = object : InterchangeStructureInputRestriction<Boolean> {
			override fun isValid(input: String) = input.toBooleanStrictOrNull() != null
			override fun transformer(input: String) = input.toBooleanStrict()
		}

		@JvmStatic
		val ONLINE_PLAYER = object : InterchangeStructureInputRestriction<Player> {
			override fun isValid(input: String) = (playerOrNull(input) ?: tryOrNull { playerOrNull(UUID.fromString(input)) }) != null
			override fun transformer(input: String) = playerOrNull(input) ?: playerOrNull(UUID.fromString(input))!!
		}

		@JvmStatic
		val OFFLINE_PLAYER = object : InterchangeStructureInputRestriction<OfflinePlayer> {
			override fun isValid(input: String) = (tryOrNull { offlinePlayer(UUID.fromString(input)) } ?: offlinePlayer(input)).name != null
			override fun transformer(input: String) = tryOrNull { offlinePlayer(UUID.fromString(input)) } ?: offlinePlayer(input)
		}

	}

}