package org.d3if0113.pokepedia.ui.region

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.d3if0113.pokepedia.network.PokemonAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegionViewModel(application: Application) : AndroidViewModel(application) {
    private val _response = MutableLiveData<String>()

    init {
        getRegionProperties()
    }

    private fun getRegionProperties() {
        _response.value = "Respon API Region"
        PokemonAPI.retrofitService.getData().enqueue(
            object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Gagal respon API Region: " + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }
            }
        )
    }

    // ----------------------------- public variable & function
    val response: LiveData<String> get() = _response
}