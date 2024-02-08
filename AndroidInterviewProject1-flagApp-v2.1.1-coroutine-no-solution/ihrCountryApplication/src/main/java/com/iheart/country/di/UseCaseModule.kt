package com.iheart.country.di

import com.iheart.country.repository.CountryDataRepository
import com.iheart.country.usecase.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideCountriesUseCase(countryDataRepository: CountryDataRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countryDataRepository)
    }
}