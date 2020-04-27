package org.d3if0113.pokepedia

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import org.d3if0113.pokepedia.ui.region.RegionAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PokemonRegionProperty>?) {
    val adapter = recyclerView.adapter as RegionAdapter
    adapter.submitList(data)
}