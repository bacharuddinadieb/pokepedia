package org.d3if0113.pokepedia.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentFavoriteBinding

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.lifecycleOwner = this
        binding.favoriteViewModel = viewModel
        binding.rvPokemonFavorite.adapter = FavoriteAdapter(FavoriteAdapter.FavoriteListener {
            viewModel.navigateToDetailFavoriteClick(it)
        })

        viewModel.navigateToDetailFavorite.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteNavToDetailFavoriteFragment(it)
                )
                viewModel.navigatedToDetailFavorite()
            }
        })

        return binding.root
    }

}
