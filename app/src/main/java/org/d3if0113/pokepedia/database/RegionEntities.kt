package org.d3if0113.pokepedia.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.d3if0113.pokepedia.network.BASE_URL_IMAGE
import org.d3if0113.pokepedia.property.PokemonPokedexProperty
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import org.d3if0113.pokepedia.property.PokemonRegionPropertyKota

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

data class EntityJoinRegionKota constructor(
    @ColumnInfo(name = "nama_region")
    val namaRegion: String,

    @ColumnInfo(name = "deskripsi_region")
    val deskripsi: String,

    @ColumnInfo(name = "nama_kota")
    val namaKota: String,

    @ColumnInfo(name = "slogan_kota")
    val slogan: String
)

@Entity(tableName = "tb_pokedex")
data class EntityPokedex(
    @PrimaryKey()
    @ColumnInfo(name = "deretan_pokedex")
    val deretan: Int,

    @ColumnInfo(name = "nama_pokedex")
    val nama: String,

    @ColumnInfo(name = "deskirpsi_pokedex")
    val deskripsi: String,

    @ColumnInfo(name = "kemampuan_pokedex")
    val kemampuan: String,

    @ColumnInfo(name = "tipe_pokedex")
    val tipe: String,

    @ColumnInfo(name = "kelemahan_pokedex")
    val kelemahan: String
)

fun List<EntityJoinRegionKota>.asDomainModelRegionJoin(): List<PokemonRegionProperty> {
    var tampungNamaRegion = ""
    var listPokemonRegionProperty: MutableList<PokemonRegionProperty> = mutableListOf()
    var pokemonRegionPropertyKota: MutableList<PokemonRegionPropertyKota> = mutableListOf()
    var pokemonRegionPropertyKota2: MutableList<MutableList<PokemonRegionPropertyKota>> =
        mutableListOf()

    map {
        if (it.namaRegion != tampungNamaRegion) {
            var asd: MutableList<PokemonRegionPropertyKota> = mutableListOf()
            listPokemonRegionProperty.add(
                PokemonRegionProperty(
                    nama = it.namaRegion,
                    deskripsi = it.deskripsi,
                    listKota = asd,
                    imgURL = "${BASE_URL_IMAGE}region/${it.namaRegion}.png"
                )
            )
            pokemonRegionPropertyKota2.add(pokemonRegionPropertyKota)
            pokemonRegionPropertyKota = mutableListOf()
        }
        if (it.namaRegion != tampungNamaRegion) {
        }
        tampungNamaRegion = it.namaRegion
        pokemonRegionPropertyKota.add(PokemonRegionPropertyKota(it.namaKota, it.slogan))
    }
    pokemonRegionPropertyKota2.add(pokemonRegionPropertyKota)
    for ((index, item) in listPokemonRegionProperty.withIndex()) {
        item.listKota = pokemonRegionPropertyKota2[index + 1]
    }
    return listPokemonRegionProperty
}

fun List<PokemonRegionProperty>.asDatabaseModelRegion(): List<EntityRegion> {
    return map {
        EntityRegion(
            nama = it.nama,
            deskripsi = it.deskripsi
        )
    }
}

fun List<PokemonPokedexProperty>.asDatabaseModelPokedex(): List<EntityPokedex> {
    return map {
        EntityPokedex(
            deretan = it.deretan,
            nama = it.nama,
            deskripsi = it.deskripsi,
            kemampuan = it.kemampuan,
            tipe = it.tipe.joinToString(),
            kelemahan = it.kelemahan.joinToString()
        )
    }
}