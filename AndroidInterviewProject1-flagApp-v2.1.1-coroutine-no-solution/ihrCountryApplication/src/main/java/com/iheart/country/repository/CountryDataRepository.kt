package com.iheart.country.repository

import com.iheart.country.data.Country
import kotlinx.coroutines.flow.Flow

interface CountryDataRepository {

    suspend fun getAllCountries(): Flow<List<Country>>

    fun getImageUrl(code: String): String
}