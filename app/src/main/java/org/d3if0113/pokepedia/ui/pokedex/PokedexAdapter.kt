package org.d3if0113.pokepedia.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0113.pokepedia.databinding.ItemPokedexBinding
import org.d3if0113.pokepedia.property.PokemonPokedexProperty

class PokedexAdapter :
    ListAdapter<PokemonPokedexProperty, PokedexAdapter.PokemonPokedexPropertyViewHolder>(
        DiffCallback
    ) {

    class PokemonPokedexPropertyViewHolder(private var binding: ItemPokedexBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(regionPropertyKota: PokemonPokedexProperty) {
            binding.varPokedexProperty = regionPropertyKota
            binding.varPokemon = "#${regionPropertyKota.deretan.toString().padStart(
                3,
                '0'
            )} ${regionPropertyKota.nama.capitalize()}"
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonPokedexProperty>() {
        override fun areItemsTheSame(
            oldItem: PokemonPokedexProperty,
            newItem: PokemonPokedexProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonPokedexProperty,
            newItem: PokemonPokedexProperty
        ): Boolean {
            return oldItem.deretan == newItem.deretan
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonPokedexPropertyViewHolder {
        return PokemonPokedexPropertyViewHolder(
            ItemPokedexBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PokemonPokedexPropertyViewHolder, position: Int) {
        val regionProperty = getItem(position)
        holder.bind(regionProperty)
    }

}