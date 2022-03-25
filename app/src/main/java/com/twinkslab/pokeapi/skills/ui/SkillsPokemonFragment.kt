package com.twinkslab.pokeapi.skills.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.R
import com.twinkslab.pokeapi.databinding.FragmentDetailPokemonBinding
import com.twinkslab.pokeapi.databinding.FragmentSkillsPokemonBinding
import com.twinkslab.pokeapi.details.ui.DetailPokemonFragmentArgs
import com.twinkslab.pokeapi.evolution.adapter.SkillsPokemonAdapter
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.model.SkillsApi
import com.twinkslab.pokeapi.service.viewModel.ViewModelPokemon

class SkillsPokemonFragment : Fragment() {

    private lateinit var binding:FragmentSkillsPokemonBinding
    private val viewModelHome: ViewModelPokemon by viewModels()

    lateinit var pokemon:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSkillsPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments()
    }

    private fun arguments(){
        pokemon = SkillsPokemonFragmentArgs.fromBundle(this.requireArguments()).pokemon
        service()
        viewModelHome.pokeSkills.observe(this.viewLifecycleOwner,handleSkillsPokemon())

    }

    private fun service(){
        viewModelHome.getSkills(pokemon)
    }

    private fun handleSkillsPokemon(): (SkillsApi?) -> Unit = { pokemonSkill ->

        val adapter = SkillsPokemonAdapter(pokemonSkill?.abilities)
        binding.rvSkills.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        binding.rvSkills.adapter = adapter

    }

}