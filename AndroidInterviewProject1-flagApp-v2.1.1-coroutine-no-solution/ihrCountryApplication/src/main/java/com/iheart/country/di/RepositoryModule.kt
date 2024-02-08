package com.iheart.country.di;

import com.iheart.country.api.CountriesApi
import com.iheart.country.repository.CountryDataRepository
import com.iheart.country.repository.CountryDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCountryDataRepository(countryApi:CountriesApi): CountryDataRepository {
        return CountryDataRepositoryImpl(countryApi)
    }
}
