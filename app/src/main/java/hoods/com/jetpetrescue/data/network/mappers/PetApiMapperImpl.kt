package hoods.com.jetpetrescue.data.network.mappers

import hoods.com.jetpetrescue.data.network.models.Address
import hoods.com.jetpetrescue.data.network.models.ApiAnimals
import hoods.com.jetpetrescue.data.network.models.Contact
import hoods.com.jetpetrescue.data.network.models.Photo
import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.domain.models.PetOwnerContact
import hoods.com.jetpetrescue.domain.models.PetPhoto

class PetApiMapperImpl : PetApiMapper<List<Pet>, ApiAnimals> {
    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(apiEntity: ApiAnimals): List<Pet> {
        return apiEntity.animals.map { animal ->
            animal.run {
                Pet(
                    id = id.toString(),
                    age = formatData(age),
                    breeds = formatData(breeds.primary),
                    colors = formatData(colors.primary),
                    contact = formatContact(contact),
                    description = formatData(description),
                    gender = formatData(gender),
                    name = formatData(name),
                    photos = formatPhoto(photos),
                    size = formatData(size),
                    species = formatData(species),
                    status =  formatData(status),
                    tags = tags ?: emptyList(),
                    type = formatData(type),
                    url = formatData(url),
                    currentPage = apiEntity.pagination.currentPage ?: 0
                )
            }
        }
    }

    private fun formatContact(contact: Contact?) : PetOwnerContact {
        return PetOwnerContact(
            address = formatData(formatAddress(contact?.address)),
            email = formatData(contact?.email),
            phone = formatData(contact?.phone),
        )
    }

    private fun formatAddress(address: Address?): String {
        val dot = "u25cf"
        if(address != null) {
            return "${address.city}$dot${address.country}"
        }
        return ""
    }

    private fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    private fun formatPhoto(photoList: List<Photo>?): List<PetPhoto> {
        return photoList?.map { photo ->
            PetPhoto(
                full = formatData(photo.full),
                large = formatData(photo?.large),
                medium = formatData(photo.medium),
                small = formatData(photo.small)
            )
        } ?: listOf()
    }


}

