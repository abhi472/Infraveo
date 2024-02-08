package com.iheart.country.ui.detail

import android.graphics.Bitmap
import com.iheart.country.data.Country
import com.iheart.country.data.Language

data class DetailViewUiState(
    val title: String = "default title",
    val flagImage: Bitmap? = null,
    val nativeName: String = "default nativeName",
    val boundary: List<Country> = emptyList(),
    val languages: List<Language> = emptyList()

)

sealed class NavigationEvent {
    object NavigateBack : NavigationEvent()
}

const val COUNTRY = "COUNTRY"
const val REGION_COUNTRIES = "REGION_COUNTRIES"
