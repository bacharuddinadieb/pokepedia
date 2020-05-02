package org.d3if0113.pokepedia.ui.pokedex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentDetailPokedexBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailPokedexFragment : Fragment() {

    private lateinit var binding: FragmentDetailPokedexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_pokedex, container, false)
        val args = arguments?.let { DetailPokedexFragmentArgs.fromBundle(it) }
        val pokemonPokedexProperty = args!!.SELECTEDPOKEDEXPROPERTY
        (activity as AppCompatActivity).supportActionBar?.title =
            pokemonPokedexProperty.nama.capitalize()

        binding.varPokemonPokedexProperty = pokemonPokedexProperty
        binding.varNamaPokemonCapital = pokemonPokedexProperty.nama.capitalize()
        binding.varAbilityPokemonCapital = pokemonPokedexProperty.kemampuan.capitalize()

        return binding.root
    }

}
