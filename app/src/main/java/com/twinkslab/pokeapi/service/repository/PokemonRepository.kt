package com.twinkslab.pokeapi.service.repository

import com.twinkslab.pokeapi.service.model.ApiChain
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.ApiResult
import com.twinkslab.pokeapi.service.model.Chain
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.model.SkillsApi
import com.twinkslab.pokeapi.service.retrofit.RetrofitInstance

class PokemonRepository {
    private val pokemonsAllService = RetrofitInstance.pokemonsAllService
    private val pokemonService = RetrofitInstance.pokemonService
    private val chainService = RetrofitInstance.chainService
    private val skillService = RetrofitInstance.skillService

    suspend fun getPokemonsAllService(): ApiResult<ArrayList<ApiModelPokemon>> {
        return pokemonsAllService.getPokemonsAll()
    }

    suspend fun getPokemonService(pokemon: String): Pokemon? {
        return pokemonService.getPokemon(pokemon)
    }

    suspend fun getChainService(path: String): ApiChain? {
        return chainService.getChain(path)
    }
    suspend fun getSkillService(pokemon: String): SkillsApi? {
        return skillService.getChain(pokemon)
    }
}

