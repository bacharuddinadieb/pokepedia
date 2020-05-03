package org.d3if0113.pokepedia.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRegion(region: List<EntityRegion>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKota(kota: List<EntityKota>)

    @Query("select nama_region, deskripsi_region, nama_kota, slogan_kota from tb_region inner join tb_kota on nama_region = id_region")
    fun getAllRegionJoinKota(): LiveData<List<EntityJoinRegionKota>>
}

@Dao
interface PokedexDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPokedex(pokedex: List<EntityPokedex>)

    @Query("select * from tb_pokedex")
    fun getAllPokedex(): LiveData<List<EntityPokedex>>
}

@Database(entities = [EntityRegion::class, EntityKota::class, EntityPokedex::class], version = 3)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val regionDAO: RegionDAO
    abstract val pokedexDAO: PokedexDAO
}

private lateinit var INSTANCE: PokemonDatabase

fun getDatabase(context: Context): PokemonDatabase {
    synchronized(PokemonDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}