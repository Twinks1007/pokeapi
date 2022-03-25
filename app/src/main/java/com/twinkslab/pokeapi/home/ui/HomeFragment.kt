package com.twinkslab.pokeapi.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.databinding.FragmentHomeBinding
import com.twinkslab.pokeapi.evolution.adapter.ChainPokemonAdapter
import com.twinkslab.pokeapi.home.ui.adapter.PokemonAdapter
import com.twinkslab.pokeapi.home.ui.listeners.ClickListenerPosition
import com.twinkslab.pokeapi.service.viewModel.ViewModelPokemon
import com.twinkslab.pokeapi.service.model.ApiModelPokemon


class HomeFragment : Fragment() {


    private val viewModelHome: ViewModelPokemon by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelHome.pokeHome.observe(this.viewLifecycleOwner,handleListPokemon())
        services()
    }

    private fun handleListPokemon(): (ArrayList<ApiModelPokemon>?) -> Unit = {

        val listenerClick = object : ClickListenerPosition {
            override fun onItemClick(position: Int) {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailPokemonFragment5 (it?.get(position)?: ApiModelPokemon("",""))
                findNavController().navigate(direction)
            }
        }
        val adapter = PokemonAdapter(it, listenerClick)
        binding.rvPokemons.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        binding.rvPokemons.adapter = adapter

    }

    private fun services() {
        viewModelHome.getPokemosAll()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModelHome.pokeHome.removeObservers(this)
    }

}