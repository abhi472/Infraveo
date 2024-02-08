package com.iheart.country.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iheart.country.data.Country
import com.iheart.country.ui.base.BaseViewModel
import com.iheart.country.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val countriesUseCase: GetCountriesUseCase) :
    BaseViewModel() {

        init {
            loadData()
        }

    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>> = _countriesLiveData

    fun loadData() {
        viewModelScope.launch {
            countriesUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .catch {
                    _countriesLiveData.value = emptyList()

                }
                .collect {
                    _countriesLiveData.value = it

                }
        }
    }

    // -----------------------------------------------------
    // This function accepts the list of countries
    //
    // This function will find the country that has the largest number of borders
    //
    // This function returns Pair<Country.name, numberOfBorders as Int>
    // -----------------------------------------------------
    fun getTheCountryWithTheLargestNumberOfBorders(countries: List<Country>): Pair<String, Int> {
        countries.let { list ->
            val country = list.maxBy { it.borders?.size ?: 0}
            return Pair(country.name, country.borders?.size?:0)
        }
    }

    fun getFilteredCountries( country: Country): ArrayList<Country> {
        val list =  countriesLiveData.value?.filter { it.region == country.region } ?: emptyList()
        return ArrayList(list)
    }

    // -----------------------------------------------------
    // This function will find the top 5 countries that has the largest population per square mile (Density)
    //
    //  parameter : the list of countries
    //
    // Density = total population / area
    //
    //  density should be round off to nearest 1000th place
    //
    //  return List<Pair<Country.name, density as Float>
    // Adding remarks here: there is no area value in country data
    // -----------------------------------------------------
    fun getTop5CountriesWithTheLargestDensity(countries: List<Country>): List<Pair<String, Float>> {
        return countries.sortedByDescending {
            it.populations
        }.take(5)
            .map { it -> Pair<String, Float>(it.name, (it.populations/10000).toFloat()) }

    }


}
