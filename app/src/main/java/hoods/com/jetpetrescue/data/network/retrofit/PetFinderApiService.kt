package hoods.com.jetpetrescue.data.network.retrofit

import hoods.com.jetpetrescue.data.network.K
import hoods.com.jetpetrescue.data.network.models.AccessToken
import hoods.com.jetpetrescue.data.network.models.ApiAnimals
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetFinderApiService {
    @GET(K.BASE_END_POINT)
    suspend fun getAnimals(
        @Query("page") page: Int
    ): ApiAnimals

    @POST(K.AUTH_END_POINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(K.CLIENT_ID) clientId: String = K.API_KEY,
        @Field(K.CLIENT_SECRET) clientSecret: String = K.SECRET_KEY,
        @Field("grant_type") grantType: String = "client_credentials",
    ): AccessToken
}