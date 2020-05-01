package org.d3if0113.pokepedia.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentPokedexBinding

/**
 * A simple [Fragment] subclass.
 */
class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding
    private val viewModel: PokedexViewModel by lazy {
        ViewModelProviders.of(this).get(PokedexViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex, container, false)
        binding.lifecycleOwner = this
        binding.pokedexViewModel = viewModel

        return binding.root
    }

}
