package org.d3if0113.pokepedia.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if0113.pokepedia.database.getDatabase
import org.d3if0113.pokepedia.property.PokemonPokedexProperty
import org.d3if0113.pokepedia.repository.RegionRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToDetailFavorite = MutableLiveData<PokemonPokedexProperty>()
    private val _favoriteRepository = RegionRepository(getDatabase(application))

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

//    init {
//        getPokedexProperties()
//    }
//
//    private fun getPokedexProperties() {
//        coroutineScope.launch {
//            try {
//
//            } catch (e: Exception) {
//                Log.i("Error Ngga tau :'v", e.message)
//            }
//        }
//    }

    // ----------------------------- public variable & function
    val properties: LiveData<List<PokemonPokedexProperty>> = _favoriteRepository.favorit
    val navigateToDetailFavorite: LiveData<PokemonPokedexProperty> get() = _navigateToDetailFavorite

    fun navigatedToDetailFavorite() {
        _navigateToDetailFavorite.value = null
    }

    fun navigateToDetailFavoriteClick(pokemonPokedexProperty: PokemonPokedexProperty) {
        _navigateToDetailFavorite.value = pokemonPokedexProperty
    }

    fun hapusFavorit(pokemonPokedexProperty: PokemonPokedexProperty) {
        coroutineScope.launch {
            _favoriteRepository.hapusFavoritPokemon(pokemonPokedexProperty)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}