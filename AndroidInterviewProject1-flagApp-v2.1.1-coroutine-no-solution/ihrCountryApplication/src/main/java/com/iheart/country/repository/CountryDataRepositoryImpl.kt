package com.iheart.country.repository

import com.iheart.country.api.CountriesApi
import com.iheart.country.api.CountriesService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryDataRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi): CountryDataRepository {

    override suspend fun getAllCountries() = flow {
        emit(countriesApi.getAllCountries())
    }

    override fun getImageUrl(code: String): String = "${CountriesService.BASE_URL}flag/png250px/${code}.png"

}
