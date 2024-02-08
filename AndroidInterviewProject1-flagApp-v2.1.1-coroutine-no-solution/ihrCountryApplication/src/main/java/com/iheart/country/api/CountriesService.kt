package com.iheart.country.api

import com.iheart.country.data.Country
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface CountriesService {

    @GET("countries.json")
    suspend fun getAll(): List<Country>

    @GET("countries.json")
    fun getAllRxSingle(): Single<Response<List<Country>>>

    companion object {

//    https://ihrandroidtesting.s3.amazonaws.com/country/countries.json
//    https://ihrandroidtesting.s3.amazonaws.com/country/flag/png250px/us.png

        const val BASE_URL = "https://ihrandroidtesting.s3.amazonaws.com/country/"

    }

}

