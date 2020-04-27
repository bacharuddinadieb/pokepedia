package org.d3if0113.pokepedia.network

import com.squareup.moshi.Moshi
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// variabel constanta URL data dan image
private const val BASE_URL_DATA =
    "https://raw.githubusercontent.com/bacharuddinadieb/pokemon-data/master/pokemon-json/"
const val BASE_URL_IMAGE =
    "https://raw.githubusercontent.com/bacharuddinadieb/pokemon-data/master/pokemon-images/"

private val moshi = Moshi.Builder()
    .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_DATA)
    .build()

interface PokemonAPIService {
    @GET("region.json")
    fun getData(): Call<List<PokemonRegionProperty>>
}

object PokemonAPI {
    val retrofitService: PokemonAPIService by lazy {
        retrofit.create(PokemonAPIService::class.java)
    }
}