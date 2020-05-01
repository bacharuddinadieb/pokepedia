package org.d3if0113.pokepedia.property

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.d3if0113.pokepedia.network.BASE_URL_IMAGE

@Parcelize
data class PokemonPokedexProperty(
    val deretan: Int,
    val nama: String,
    val deskripsi: String,
    val kemampuan: String,
    val tipe: List<Int>,
    val kelemahan: List<Int>,
    val imgURL: String = "${BASE_URL_IMAGE}sugimori/${deretan}.png"
) : Parcelable