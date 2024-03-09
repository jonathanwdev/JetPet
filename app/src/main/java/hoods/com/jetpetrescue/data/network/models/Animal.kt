package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Animal(
    @SerialName("age")
    val age: String = "",
    @SerialName("attributes")
    val attributes: Attributes = Attributes(),
    @SerialName("breeds")
    val breeds: Breeds = Breeds(),
    @SerialName("coat")
    val coat: String? = null,
    @SerialName("colors")
    val colors: Colors = Colors(),
    @SerialName("contact")
    val contact: Contact = Contact(),
    @SerialName("description")
    val description: String = "",
    @SerialName("distance")
    val distance: String? = null,
    @SerialName("environment")
    val environment: Environment = Environment(),
    @SerialName("gender")
    val gender: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("_links")
    val links: Links = Links(),
    @SerialName("name")
    val name: String = "",
    @SerialName("organization_id")
    val organizationId: String = "",
    @SerialName("photos")
    val photos: List<Photo> = listOf(),
    @SerialName("published_at")
    val publishedAt: String = "",
    @SerialName("size")
    val size: String = "",
    @SerialName("species")
    val species: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("tags")
    val tags: List<String> = listOf(),
    @SerialName("type")
    val type: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("videos")
    val videos: List<Video> = listOf()
)