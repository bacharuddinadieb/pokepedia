package org.d3if0113.pokepedia.property

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonRegionPropertyKota(
    val nama: String,
    val slogan: String
) : Parcelable