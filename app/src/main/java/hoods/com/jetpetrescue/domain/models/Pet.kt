package hoods.com.jetpetrescue.domain.models

data class Pet(
    val id: String,
    val age: String,
    val url: String,
    val breeds: String,
    val colors: String,
    val contact: PetOwnerContact,
    val description: String,
    val gender: String,
    val name: String,
    val photos: List<PetPhoto>,
    val size: String,
    val species: String,
    val status: String,
    val tags: List<String>,
    val type: String,
    val currentPage: Int,
)