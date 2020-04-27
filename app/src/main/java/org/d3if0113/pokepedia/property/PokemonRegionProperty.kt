package org.d3if0113.pokepedia.property

import com.squareup.moshi.Json

data class PokemonRegionProperty(
    val nama: String,
    val deskripsi: String,
    @Json(name = "kota") val listKota: List<PokemonRegionPropertyKota>
)