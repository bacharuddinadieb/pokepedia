package org.d3if0113.pokepedia.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0113.pokepedia.databinding.ItemFavoriteBinding
import org.d3if0113.pokepedia.property.PokemonPokedexProperty

class FavoriteAdapter(val clickListener: FavoriteListener) :
    ListAdapter<PokemonPokedexProperty, FavoriteAdapter.FavoritePropertyViewHolder>(
        DiffCallback
    ) {

    class FavoritePropertyViewHolder(private var binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(regionPropertyKota: PokemonPokedexProperty, clickListener: FavoriteListener) {
            binding.varPokedexProperty = regionPropertyKota
            binding.varPokemon = "#${regionPropertyKota.deretan.toString().padStart(
                3,
                '0'
            )} ${regionPropertyKota.nama.capitalize()}"
            binding.varClickListener = clickListener
            binding.executePendingBindings()
        }
    }

    class FavoriteListener(val clickListener: (pokemonPokedexProperty: PokemonPokedexProperty) -> Unit) {
        fun onClick(pokemonPokedexProperty: PokemonPokedexProperty) =
            clickListener(pokemonPokedexProperty)
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
    ): FavoritePropertyViewHolder {
        return FavoritePropertyViewHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: FavoritePropertyViewHolder, position: Int) {
        val regionProperty = getItem(position)
        holder.bind(regionProperty, clickListener)
    }

}