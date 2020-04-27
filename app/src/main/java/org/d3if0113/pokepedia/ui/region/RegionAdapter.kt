package org.d3if0113.pokepedia.ui.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0113.pokepedia.databinding.ItemRegionBinding
import org.d3if0113.pokepedia.property.PokemonRegionProperty

class RegionAdapter :
    ListAdapter<PokemonRegionProperty, RegionAdapter.RegionPropertyViewHolder>(DiffCallback) {
    class RegionPropertyViewHolder(private var binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(regionProperty: PokemonRegionProperty) {
            binding.varRegion = regionProperty.nama.capitalize()
            binding.varNumCity = "Total kota ${regionProperty.listKota.size}"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonRegionProperty>() {
        override fun areItemsTheSame(
            oldItem: PokemonRegionProperty,
            newItem: PokemonRegionProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonRegionProperty,
            newItem: PokemonRegionProperty
        ): Boolean {
            return oldItem.nama == newItem.nama
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionAdapter.RegionPropertyViewHolder {
        return RegionPropertyViewHolder(ItemRegionBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RegionAdapter.RegionPropertyViewHolder, position: Int) {
        val regionProperty = getItem(position)
        holder.bind(regionProperty)
    }

}