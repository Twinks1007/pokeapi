package com.twinkslab.pokeapi.details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.twinkslab.pokeapi.R
import com.twinkslab.pokeapi.databinding.FragmentDetailPokemonBinding
import com.twinkslab.pokeapi.databinding.FragmentHomeBinding
import com.twinkslab.pokeapi.home.ui.HomeFragmentDirections
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.viewModel.ViewModelPokemon

class DetailPokemonFragment : Fragment() {

    private lateinit var binding :FragmentDetailPokemonBinding
    private lateinit var pokemon: ApiModelPokemon
    private val viewModelHome: ViewModelPokemon by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments()

    }

    fun arguments(){
        pokemon = DetailPokemonFragmentArgs.fromBundle(this.requireArguments()).pokemon
        service()
        viewModelHome.pokeDetails.observe(this.viewLifecycleOwner,handleListPokemon())
    }

    private fun handleListPokemon(): (Pokemon?) -> Unit = { pokemonDetail ->

        binding.apply {
            pokename.text = pokemon.name
            happiness.text = "Felicidad base: ${pokemonDetail?.base_happiness.toString()}"
            rate.text = "Tasa de captura: ${pokemonDetail?.capture_rate.toString()}"
            color.text = "Color: ${pokemonDetail?.color?.name}"

            eggGroup.text = "Grupo de huevos: ${pokemonDetail?.egg_groups?.map { it?.name }}"

            evolutionary.setOnClickListener {
                val direction = DetailPokemonFragmentDirections.actionDetailPokemonFragmentToEvolutionPokemonFragment(pokemonDetail?.evolution_chain?.url?:"")
                findNavController().navigate(direction)
            }
            skills.setOnClickListener {
                val direction = DetailPokemonFragmentDirections.actionDetailPokemonFragmentToSkillsPokemonFragment (pokemon.name?:"")
                findNavController().navigate(direction)
            }
        }

    }

    private fun service() {
        viewModelHome.getPokemon(pokemon.name?:"")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelHome.pokeDetails.removeObservers(this)
    }
}