package com.twinkslab.pokeapi.service.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.twinkslab.pokeapi.service.model.ApiChain
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.Chain
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.model.SkillsApi
import com.twinkslab.pokeapi.service.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ViewModelPokemon : ViewModel() {

    private val repository = PokemonRepository()


    private val _pokeHome = MutableLiveData<ArrayList<ApiModelPokemon>?>()
    val pokeHome: LiveData<ArrayList<ApiModelPokemon>?> = _pokeHome

    private val _pokeDetails = MutableLiveData<Pokemon?>()
    val pokeDetails: LiveData<Pokemon?> = _pokeDetails

    private val _pokeChain = MutableLiveData<ApiChain?>()
    val pokeChain: LiveData<ApiChain?> = _pokeChain

    private val _pokeSkills = MutableLiveData<SkillsApi?>()
    val pokeSkills: LiveData<SkillsApi?> = _pokeSkills

    fun getPokemosAll() {
        viewModelScope.launch {
            try {
                val pokemons = repository.getPokemonsAllService()
                _pokeHome.value = pokemons.result
            } catch (e: Exception) {
                Log.e("getPokemosAll", e.message.toString());
            }
        }
    }

    fun getPokemon(pokemon:String) {
        viewModelScope.launch {
            try {
                val pokemons = repository.getPokemonService(pokemon)
                _pokeDetails.value = pokemons
            } catch (e: Exception) {
                Log.e("getPokemon", e.message.toString());
            }
        }
    }

    fun getChain(chain:String) {
        viewModelScope.launch {
            try {
                val pokemons = repository.getChainService(clearUrl(chain))
                _pokeChain.value = pokemons
            } catch (e: Exception) {
                Log.e("getChain", e.message.toString());
            }
        }
    }

    private fun clearUrl(data:String):String{
        return data.replace("https://pokeapi.co","")
    }

    fun getSkills(pokemon: String){
        viewModelScope.launch {
            try {
                val pokemons = repository.getSkillService(pokemon)
                _pokeSkills.value = pokemons
            } catch (e: Exception) {
                Log.e("getSkills", e.message.toString());
            }
        }
    }
}