package org.d3if0113.pokepedia.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.d3if0113.pokepedia.property.PokemonRegionProperty

@Entity(tableName = "tb_region")
data class EntityRegion(
    @PrimaryKey()
    @ColumnInfo(name = "nama_region")
    val nama: String,

    @ColumnInfo(name = "deskripsi_region")
    val deskripsi: String
)

@Entity(
    tableName = "tb_kota",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = EntityRegion::class,
            parentColumns = arrayOf("nama_region"),
            childColumns = arrayOf("id_region"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class EntityKota constructor(
    @PrimaryKey()
    @ColumnInfo(name = "nama_kota")
    val nama: String,

    @ColumnInfo(name = "slogan_kota")
    val slogan: String,

    @ColumnInfo(name = "id_region")
    val idRegion: String
)

fun List<PokemonRegionProperty>.asDatabaseModelRegion(): List<EntityRegion> {
    return map {
        EntityRegion(
            nama = it.nama,
            deskripsi = it.deskripsi
        )
    }
}