package org.d3if0113.pokepedia.ui.region.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0113.pokepedia.databinding.ItemDetailRegionkotaBinding
import org.d3if0113.pokepedia.property.PokemonRegionPropertyKota

class DetailRegionAdapter :
    ListAdapter<PokemonRegionPropertyKota, DetailRegionAdapter.DetailRegionPropertyKotaViewHolder>(
        DiffCallback
    ) {

    class DetailRegionPropertyKotaViewHolder(private var binding: ItemDetailRegionkotaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(regionPropertyKota: PokemonRegionPropertyKota) {
            binding.varRegionKotaProperty = regionPropertyKota
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonRegionPropertyKota>() {
        override fun areItemsTheSame(
            oldItem: PokemonRegionPropertyKota,
            newItem: PokemonRegionPropertyKota
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonRegionPropertyKota,
            newItem: PokemonRegionPropertyKota
        ): Boolean {
            return oldItem.nama == newItem.nama
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailRegionAdapter.DetailRegionPropertyKotaViewHolder {
        return DetailRegionPropertyKotaViewHolder(
            ItemDetailRegionkotaBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: DetailRegionAdapter.DetailRegionPropertyKotaViewHolder,
        position: Int
    ) {
        val regionProperty = getItem(position)
        holder.bind(regionProperty)
    }

}