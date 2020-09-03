package alfianyusufabdullah.exp.games.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Game(
    @SerialName("name")
    val name: String,
    @SerialName("rating")
    val rating: Double,
    @SerialName("background_image")
    val background: String,
    @SerialName("released")
    val released: String
)