package com.twinkslab.pokeapi.service.retrofit

import com.twinkslab.pokeapi.service.model.ApiChain
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.ApiResult
import com.twinkslab.pokeapi.service.model.Chain
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.model.SkillsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsAllService {
    @GET("/api/v2/pokemon")
    suspend fun getPokemonsAll(): ApiResult<ArrayList<ApiModelPokemon>>
}

interface PokemonService {
    @GET("/api/v2/pokemon-species/{pokemon}")
    suspend fun getPokemon(
        @Path("pokemon") pokemon:String
    ): Pokemon?
}

interface ChainService {
    @GET("/{path}")
    suspend fun getChain(
        @Path("path") chain:String
    ): ApiChain?
}

interface SkillsService {
    @GET("/api/v2/pokemon/{pokemon}")
    suspend fun getChain(
        @Path("pokemon") pokemon:String
    ): SkillsApi?
}




object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co"
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val pokemonsAllService: PokemonsAllService by lazy {
        retrofit.create(PokemonsAllService::class.java)
    }

    val pokemonService: PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }

    val chainService: ChainService by lazy {
        retrofit.create(ChainService::class.java)
    }
    val skillService: SkillsService by lazy {
        retrofit.create(SkillsService::class.java)
    }
}
