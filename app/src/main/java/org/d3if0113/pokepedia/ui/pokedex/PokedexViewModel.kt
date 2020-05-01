package org.d3if0113.pokepedia.ui.pokedex

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if0113.pokepedia.network.PokemonAPI
import org.d3if0113.pokepedia.property.PokemonPokedexProperty

class PokedexViewModel(application: Application) : AndroidViewModel(application) {
    private val _properties = MutableLiveData<List<PokemonPokedexProperty>>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getPokedexProperties()
    }

    private fun getPokedexProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonAPI.retrofitService.getDataPokedex()
            try {
                var listResult = getPropertiesDeferred.await()
                _properties.value = listResult
                Log.i("Data Pokemon", "a ${listResult.size}")
            } catch (e: Exception) {
                // _status.value = PokemonAPIStatus.ERROR
                Log.i("Error Ngga tau :'v", e.message)
            }
        }
    }

    // ----------------------------- public variable & function
    val properties: LiveData<List<PokemonPokedexProperty>> get() = _properties

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}