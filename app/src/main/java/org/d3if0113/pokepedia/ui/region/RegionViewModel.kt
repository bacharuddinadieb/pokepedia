package org.d3if0113.pokepedia.ui.region

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if0113.pokepedia.database.getDatabase
import org.d3if0113.pokepedia.network.PokemonAPIStatus
import org.d3if0113.pokepedia.property.PokemonRegionProperty
import org.d3if0113.pokepedia.repository.RegionRepository

class RegionViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<PokemonAPIStatus>()
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
        _status.value = PokemonAPIStatus.DONE
        coroutineScope.launch {
            try {
                _regionRepository.refreshRegion()
            } catch (e: Exception) {
                // _status.value = PokemonAPIStatus.ERROR
                Log.i("Error Ngga tau :'v", e.message)
            }
        }
    }

    // ----------------------------- public variable & function
    val status: LiveData<PokemonAPIStatus> get() = _status
    val properties: LiveData<List<PokemonRegionProperty>> = _regionRepository.regionJoin
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