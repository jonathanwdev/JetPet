package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    @SerialName("next")
    val next: Next = Next(),
    @SerialName("previous")
    val previous: Previous = Previous()
)