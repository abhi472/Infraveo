package com.iheart.country.ui.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iheart.country.data.Country
import com.iheart.country.data.Language
import com.iheart.country.repository.CountryDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val countryDataRepository: CountryDataRepositoryImpl) :
    ViewModel() {

    private val _uiState = MutableStateFlow(DetailViewUiState())
    val uiState: StateFlow<DetailViewUiState> = _uiState.asStateFlow()
    private val _filteredCountries: ArrayList<Country> = ArrayList()
    val filteredCountries:ArrayList<Country> = _filteredCountries
    private val _startActivityEvent: MutableLiveData<Country> = MutableLiveData()
    val startActivityEvent: LiveData<Country> = _startActivityEvent


    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    fun setBoundCountries(list: ArrayList<Country>, country: Country) {
            _filteredCountries.addAll(list)
            val filteredList: MutableList<Country> = mutableListOf()
            country.borders?.let {
                for(border in it) {
                    for (region in list) {
                        if(border == region.alpha3Code) {
                            filteredList.add(region)
                        }
                    }
                }
            }
        updateUiState(boundaryCountries = filteredList)
    }

    fun setImage(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            try {

                // Image name *countryCode".png
                // ex) https://ihrandroidtesting.s3.amazonaws.com/country/flag/png250px/af.png
                // ex) https://ihrandroidtesting.s3.amazonaws.com/country/flag/png250px/us.png
                val url = URL(countryDataRepository.getImageUrl(countryCode.lowercase()))
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                updateUiState(flagImage = image)
            } catch (e: IOException) {
            }
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateBack)
        }
    }


    // -----------------------------------------------------
    //
    //   when the user select the Border country
    //      it will open new DetailActivity / DetailComposeActivity for the selected country
    //
    //  parameter : 3 alpha code for the country
    //
    // -----------------------------------------------------
    fun handleBorderCountrySelected(alpha3Code: String) {

        val country = filteredCountries.last { it.alpha3Code == alpha3Code }
        _startActivityEvent.value = country
    }


    fun updateUiState(
        title: String = uiState.value.title,
        flagImage: Bitmap? = uiState.value.flagImage,
        nativeName: String = uiState.value.nativeName,
        boundaryCountries: List<Country> = uiState.value.boundary,
        languageList:List<Language> = uiState.value.languages
    ) {
        _uiState.value = uiState.value.copy(
            title = title,
            flagImage = flagImage,
            nativeName = nativeName,
            boundary = boundaryCountries,
            languages = languageList
        )
    }
}
