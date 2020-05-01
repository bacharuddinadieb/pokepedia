package org.d3if0113.pokepedia.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if0113.pokepedia.database.EntityKota
import org.d3if0113.pokepedia.database.PokemonDatabase
import org.d3if0113.pokepedia.database.asDatabaseModelRegion
import org.d3if0113.pokepedia.database.asDomainModelRegionJoin
import org.d3if0113.pokepedia.network.PokemonAPI
import org.d3if0113.pokepedia.property.PokemonRegionProperty

class RegionRepository(private val database: PokemonDatabase) {

    val regionJoin: LiveData<List<PokemonRegionProperty>> =
        Transformations.map(database.regionDAO.getAllRegionJoinKota()) {
            it.asDomainModelRegionJoin()
        }

    suspend fun refreshRegion() {
        withContext(Dispatchers.IO) {
            val dataRegion = PokemonAPI.retrofitService.getDataRegion().await()
            val dataRegionKota: MutableList<EntityKota> = mutableListOf()
            try {
                database.regionDAO.insertAllRegion(dataRegion.asDatabaseModelRegion())
                for (item in dataRegion) {
                    for (item2 in item.listKota) {
                        val entityKota = EntityKota(item2.nama, item2.slogan, item.nama)
                        dataRegionKota.add(entityKota)
                    }
                }
                database.regionDAO.insertAllKota(dataRegionKota)
                Log.i("Berhasil Insert Uy", "${dataRegion.size} + ${dataRegionKota.size} size")
            } catch (e: Exception) {
                Log.i("Error Insert", e.message)
            }
        }
    }
}