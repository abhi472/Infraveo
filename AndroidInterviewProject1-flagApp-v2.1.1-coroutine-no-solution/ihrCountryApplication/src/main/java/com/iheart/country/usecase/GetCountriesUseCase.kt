package com.iheart.country.usecase

import com.iheart.country.data.Country
import com.iheart.country.repository.CountryDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryDataRepository: CountryDataRepository
) {

     suspend operator fun invoke(): Flow<List<Country>> {
        return countryDataRepository.getAllCountries()
     }
}