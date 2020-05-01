package org.d3if0113.pokepedia.database

import android.content.Context
import androidx.room.*

@Dao
interface RegionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRegion(region: List<EntityRegion>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKota(kota: List<EntityKota>)
}

@Database(entities = [EntityRegion::class, EntityKota::class], version = 2)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val regionDAO: RegionDAO
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