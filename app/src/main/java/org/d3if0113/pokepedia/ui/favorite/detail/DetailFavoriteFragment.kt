package org.d3if0113.pokepedia.ui.favorite.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentDetailFavoriteBinding
import org.d3if0113.pokepedia.ui.favorite.FavoriteViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentDetailFavoriteBinding
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_favorite, container, false)
        val args = arguments?.let { DetailFavoriteFragmentArgs.fromBundle(it) }
        val pokemonPokedexProperty = args!!.SELECTEDFAVORITEPROPERTY
        (activity as AppCompatActivity).supportActionBar?.title =
            pokemonPokedexProperty.nama.capitalize()

        binding.varPokemonPokedexProperty = pokemonPokedexProperty
        binding.varNamaPokemonCapital = pokemonPokedexProperty.nama.capitalize()
        binding.varAbilityPokemonCapital = pokemonPokedexProperty.kemampuan.capitalize()

        return binding.root
    }

}
