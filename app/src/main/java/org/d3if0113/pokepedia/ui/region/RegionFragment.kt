package org.d3if0113.pokepedia.ui.region

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentRegionBinding

/**
 * A simple [Fragment] subclass.
 */
class RegionFragment : Fragment() {

    private lateinit var binding: FragmentRegionBinding
    private val viewModel: RegionViewModel by lazy {
        ViewModelProviders.of(this).get(RegionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_region, container, false)
        binding.lifecycleOwner = this
        binding.regionViewModel = viewModel

        binding.rvRegion.adapter =
            RegionAdapter(RegionAdapter.RegionListener { pokemonRegionProperty ->
                Log.i(
                    "ahahaha",
                    "${pokemonRegionProperty.nama} yes"
                )
            })
        return binding.root
    }

}
