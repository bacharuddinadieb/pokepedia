package org.d3if0113.pokepedia.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface RegionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRegion(region: List<EntityRegion>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKota(kota: List<EntityKota>)
}