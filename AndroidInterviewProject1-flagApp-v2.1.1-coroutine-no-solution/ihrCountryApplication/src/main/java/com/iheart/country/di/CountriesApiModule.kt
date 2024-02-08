package com.iheart.country.di

import com.google.gson.GsonBuilder
import com.iheart.country.api.CountriesService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CountriesApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): CountriesService =
        Retrofit.Builder()
            .baseUrl(CountriesService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesService::class.java)

}
