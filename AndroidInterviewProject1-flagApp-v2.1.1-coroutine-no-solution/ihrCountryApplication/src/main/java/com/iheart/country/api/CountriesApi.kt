package com.iheart.country.api

import com.iheart.country.data.Country
import javax.inject.Inject

class CountriesApi @Inject constructor(private val countriesService: CountriesService) {

     suspend fun getAllCountries(): List<Country> = countriesService.getAll()
}
