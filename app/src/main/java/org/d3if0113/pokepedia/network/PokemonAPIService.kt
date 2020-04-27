package org.d3if0113.pokepedia.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// variabel constanta URL data dan image
private const val BASE_URL_DATA =
    "https://raw.githubusercontent.com/bacharuddinadieb/pokemon-data/master/pokemon-json/"
const val BASE_URL_IMAGE =
    "https://raw.githubusercontent.com/bacharuddinadieb/pokemon-data/master/pokemon-images/"

private val retrofit =
    Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(
        BASE_URL_DATA
    ).build()

interface PokemonAPIService {
    @GET("region.json")
    fun getData(): Call<String>
}

object PokemonAPI {
    val retrofitService: PokemonAPIService by lazy {
        retrofit.create(PokemonAPIService::class.java)
    }
}