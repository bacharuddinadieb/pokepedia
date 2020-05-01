package org.d3if0113.pokepedia.ui.region

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.d3if0113.pokepedia.database.getDatabase
import org.d3if0113.pokepedia.network.PokemonAPI
import org.d3if0113.pokepedia.network.PokemonAPIStatus
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import org.d3if0113.pokepedia.repository.RegionRepository

class RegionViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<PokemonAPIStatus>()
    private val _properties = MutableLiveData<List<PokemonRegionProperty>>()
    private val _navigateToDetailRegion = MutableLiveData<PokemonRegionProperty>()
    private val _regionRepository = RegionRepository(getDatabase(application))
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getRegionProperties()
    }

    private fun getRegionProperties() {
        _status.value = PokemonAPIStatus.LOADING
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonAPI.retrofitService.getData()
            try {
                var listResult = getPropertiesDeferred.await()
                _properties.value = listResult
                _regionRepository.refreshRegion()
                _status.value = PokemonAPIStatus.DONE
            } catch (e: Exception) {
                delay(2500)
                _status.value = PokemonAPIStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    // ----------------------------- public variable & function
    val status: LiveData<PokemonAPIStatus> get() = _status
    val properties: LiveData<List<PokemonRegionProperty>> get() = _properties
    val navigateToDetailRegion: LiveData<PokemonRegionProperty> get() = _navigateToDetailRegion

    fun navigatedToDetailRegion() {
        _navigateToDetailRegion.value = null
    }

    fun navigateToDetailRegionClick(pokemonRegionProperty: PokemonRegionProperty) {
        _navigateToDetailRegion.value = pokemonRegionProperty
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}