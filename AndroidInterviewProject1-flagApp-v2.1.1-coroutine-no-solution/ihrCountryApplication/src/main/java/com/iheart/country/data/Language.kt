package com.iheart.country.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    val name: String,
    val nativeName: String,
    val iso639_1: String?,
    val iso639_2: String?
): Parcelable
