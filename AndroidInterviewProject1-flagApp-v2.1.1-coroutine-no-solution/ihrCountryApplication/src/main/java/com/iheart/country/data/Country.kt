package com.iheart.country.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val nativeName: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val populations: Long,
    val languages: List<Language>,
    val flag: String?,
    val borders: List<String>?,
): Parcelable

//  "area": 1580.0,