package org.d3if0113.pokepedia.ui.pokedex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentDetailPokedexBinding
import org.d3if0113.pokepedia.ui.pokedex.PokedexViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailPokedexFragment : Fragment() {

    private lateinit var binding: FragmentDetailPokedexBinding
    private val viewModel: PokedexViewModel by lazy {
        ViewModelProviders.of(this).get(PokedexViewModel::class.java)
    }

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
        var apakahAdaDiFavorite: Boolean = false

        binding.varPokemonPokedexProperty = pokemonPokedexProperty
        binding.varNamaPokemonCapital = pokemonPokedexProperty.nama.capitalize()
        binding.varAbilityPokemonCapital = pokemonPokedexProperty.kemampuan.capitalize()

        viewModel.favoriteProperties.observe(viewLifecycleOwner, Observer {
            it?.let {
                for (item in it) {
                    if (item.deretan == pokemonPokedexProperty.deretan) {
                        apakahAdaDiFavorite = true
                    }
                }
                if (apakahAdaDiFavorite) {
                    binding.fabFavorite.setImageResource(R.drawable.ic_delete_black_24dp)
                }
            }
        })

        binding.fabFavorite.setOnClickListener {
            if (apakahAdaDiFavorite) {
            } else {
                viewModel.tambahkanFavorit(pokemonPokedexProperty)
            }
        }

        return binding.root
    }

}
