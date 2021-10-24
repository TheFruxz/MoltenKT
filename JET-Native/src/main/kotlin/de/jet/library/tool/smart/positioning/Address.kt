package de.jet.library.tool.smart.positioning

import de.jet.library.tool.smart.identification.Identifiable
import kotlinx.serialization.Serializable

@Serializable
data class Address<T> internal constructor(val address: String) : Identifiable<Address<T>> {

    override val identity = address

    override fun toString() = address

    override fun hashCode(): Int {
        var result = address.hashCode()
        result = 31 * result + identity.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address<*>

        if (address != other.address) return false
        if (identity != other.identity) return false

        return true
    }

    companion object {

        fun <T> address(path: String) =
            Address<T>(path)

    }

}