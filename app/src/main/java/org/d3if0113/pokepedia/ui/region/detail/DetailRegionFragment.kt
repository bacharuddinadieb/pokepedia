package org.d3if0113.pokepedia.ui.region.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.d3if0113.pokepedia.R
import org.d3if0113.pokepedia.databinding.FragmentDetailRegionBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailRegionFragment : Fragment() {

    private lateinit var binding: FragmentDetailRegionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_region, container, false)
        val args = arguments?.let { DetailRegionFragmentArgs.fromBundle(it) }
        val pokemonRegionProperty = args!!.SELECTEDREGIONPROPERTY

        Log.i("Data Parcel", args.SELECTEDREGIONPROPERTY.nama)
        binding.varRegionProperty = pokemonRegionProperty
        binding.varJudul = pokemonRegionProperty.nama.capitalize()
        binding.rvDaftarKota.adapter = DetailRegionAdapter()
        binding.rvDaftarKota.isNestedScrollingEnabled = false

        return binding.root
    }

}
