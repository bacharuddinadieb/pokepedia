package org.d3if0113.pokepedia.property

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import org.d3if0113.pokepedia.network.BASE_URL_IMAGE

@Parcelize
data class PokemonRegionProperty(
    val nama: String,
    val deskripsi: String,
    @Json(name = "kota") val listKota: List<PokemonRegionPropertyKota>,
    val imgURL: String = "${BASE_URL_IMAGE}region/${nama}.png"
) : Parcelable