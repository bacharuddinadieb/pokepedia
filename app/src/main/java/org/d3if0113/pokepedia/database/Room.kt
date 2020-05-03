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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritPokemon(favorit: EntityFavorit)

    @Query("select deretan_pokedex, nama_pokedex, deskirpsi_pokedex, kemampuan_pokedex, tipe_pokedex, kelemahan_pokedex from tb_pokedex inner join tb_favorit on deretan_pokedex = deretan_favorit")
    fun getAllFavorit(): LiveData<List<EntityPokedex>>

    @Delete
    fun deleteFavorite(vararg entityFavorit: EntityFavorit)
}

@Database(
    entities = [EntityRegion::class, EntityKota::class, EntityPokedex::class, EntityFavorit::class],
    version = 4
)
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