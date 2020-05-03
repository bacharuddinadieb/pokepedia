package org.d3if0113.pokepedia

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import org.d3if0113.pokepedia.network.PokemonAPIStatus
import org.d3if0113.pokepedia.property.DAFTAR_TIPE_POKEMON
import org.d3if0113.pokepedia.property.PokemonPokedexProperty
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import org.d3if0113.pokepedia.property.PokemonRegionPropertyKota
import org.d3if0113.pokepedia.ui.favorite.FavoriteAdapter
import org.d3if0113.pokepedia.ui.pokedex.PokedexAdapter
import org.d3if0113.pokepedia.ui.region.RegionAdapter
import org.d3if0113.pokepedia.ui.region.detail.DetailRegionAdapter

@BindingAdapter("listDataRegion")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PokemonRegionProperty>?) {
    val adapter = recyclerView.adapter as RegionAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataKotaRegion")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<PokemonRegionPropertyKota>?) {
    val adapter = recyclerView.adapter as DetailRegionAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataPokedex")
fun bindRecyclerView3(recyclerView: RecyclerView, data: List<PokemonPokedexProperty>?) {
    val adapter = recyclerView.adapter as PokedexAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataFavorite")
fun bindRecyclerView4(recyclerView: RecyclerView, data: List<PokemonPokedexProperty>?) {
    val adapter = recyclerView.adapter as FavoriteAdapter
    adapter.submitList(data)
}

@BindingAdapter("pokemonAPIStatusImage")
fun bindStatus(
    statusImageView: ImageView,
    status: PokemonAPIStatus?
) {
    if (status == PokemonAPIStatus.ERROR) {
        statusImageView.visibility = View.VISIBLE
        statusImageView.setImageResource(R.drawable.ic_signal_cellular_connected_no_internet_0_bar_black_24dp)
        Snackbar.make(statusImageView, "Sepertinya anda offline :(", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}

@BindingAdapter("pokemonAPIStatusSpinner")
fun bindStatus(
    statusProgressBar: ProgressBar,
    status: PokemonAPIStatus?
) {
    when (status) {
        PokemonAPIStatus.LOADING -> {
            statusProgressBar.visibility = View.VISIBLE
        }
        PokemonAPIStatus.DONE -> {
            statusProgressBar.visibility = View.GONE
        }
        PokemonAPIStatus.ERROR -> {
            statusProgressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageURL")
fun bindImage(imgView: ImageView, imgURL: String?) {
    imgURL?.let {
        val imgUri = Uri.parse(imgURL)
        Glide.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.kotak_loading)
            .error(R.drawable.kotak_broken)
            .into(imgView)
    }
}

@BindingAdapter("chipTipeMapper")
fun chipTipeMapper(chipGroup: ChipGroup, listTipePokemon: List<Int>) {
    listTipePokemon.let {
        chipGroup.removeAllViews()
        for (item in listTipePokemon) {
            var chip = Chip(chipGroup.context)
            chip.text = DAFTAR_TIPE_POKEMON[item - 1].nama
            chip.chipBackgroundColor =
                ColorStateList.valueOf(Color.parseColor(DAFTAR_TIPE_POKEMON[item - 1].warna))
            chipGroup.addView(chip)
        }
    }
}