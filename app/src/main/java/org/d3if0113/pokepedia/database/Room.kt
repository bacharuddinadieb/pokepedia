package org.d3if0113.pokepedia.database

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