package org.d3if0113.pokepedia.ui.region

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if0113.pokepedia.network.PokemonAPI
import org.d3if0113.pokepedia.property.PokemonRegionProperty

class RegionViewModel(application: Application) : AndroidViewModel(application) {
    private val _response = MutableLiveData<String>()
    private val _properties = MutableLiveData<List<PokemonRegionProperty>>()
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getRegionProperties()
    }

    private fun getRegionProperties() {
        _response.value = "Respon API Region"
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonAPI.retrofitService.getData()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Sukses: ${listResult.size} region"
                _properties.value = listResult
            } catch (e: Exception) {
                _response.value = "Gagal respon API Region: " + e.message
            }
        }
    }

    // ----------------------------- public variable & function
    val response: LiveData<String> get() = _response
    val properties: LiveData<List<PokemonRegionProperty>> get() = _properties

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}