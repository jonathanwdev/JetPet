package hoods.com.jetpetrescue.data.repository

import hoods.com.jetpetrescue.data.network.mappers.PetApiMapper
import hoods.com.jetpetrescue.data.network.models.ApiAnimals
import hoods.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.domain.repository.PetRepository
import hoods.com.jetpetrescue.utils.ResourceHolder
import java.lang.Exception

class PetRepositoryImpl(
    private val apiService: PetFinderApiService,
    private val apiMapper: PetApiMapper<List<Pet>, ApiAnimals>
): PetRepository {
    override suspend fun getAnimal(page: Int): ResourceHolder<List<Pet>> {
        return try {
            val data = apiService.getAnimals(page)
            ResourceHolder.Success(apiMapper.mapToDomain(data))
        } catch (err: Exception) {
            err.printStackTrace()
            ResourceHolder.Error(err.cause)
        }
    }
}