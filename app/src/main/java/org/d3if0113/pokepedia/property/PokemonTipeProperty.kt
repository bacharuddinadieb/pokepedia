package org.d3if0113.pokepedia.property

class PokemonTipeProperty(
    val id: Int,
    val nama: String,
    val warna: String
)

val DAFTAR_TIPE_POKEMON: List<PokemonTipeProperty> = listOf(
    PokemonTipeProperty(1, "Normal", "#A8AE98"),
    PokemonTipeProperty(2, "Fighting", "#BE4C36"),
    PokemonTipeProperty(3, "Flying", "#986EFF"),
    PokemonTipeProperty(4, "Poison", "#B22198"),
    PokemonTipeProperty(5, "Ground", "#D9C948"),
    PokemonTipeProperty(6, "Rock", "#B7B560"),
    PokemonTipeProperty(7, "Bug", "#9FCF08"),
    PokemonTipeProperty(8, "Ghost", "#733BBD"),
    PokemonTipeProperty(9, "Steel", "#ACA5BC"),
    PokemonTipeProperty(10, "Fire", "#FF1500"),
    PokemonTipeProperty(11, "Water", "#5573FF"),
    PokemonTipeProperty(12, "Grass", "#5CE155"),
    PokemonTipeProperty(13, "Electric", "#FBDC00"),
    PokemonTipeProperty(14, "Psychic", "#FF0091"),
    PokemonTipeProperty(15, "Ice", "#6CC2FF"),
    PokemonTipeProperty(16, "Dragon", "#8C00F1"),
    PokemonTipeProperty(17, "Dark", "#775641"),
    PokemonTipeProperty(18, "Fairy", "#F86DED")
)
