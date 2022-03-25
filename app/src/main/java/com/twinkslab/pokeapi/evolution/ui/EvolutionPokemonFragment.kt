package com.twinkslab.pokeapi.evolution.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.R
import com.twinkslab.pokeapi.databinding.FragmentDetailPokemonBinding
import com.twinkslab.pokeapi.databinding.FragmentEvolutionPokemonBinding
import com.twinkslab.pokeapi.details.ui.DetailPokemonFragmentArgs
import com.twinkslab.pokeapi.evolution.adapter.ChainPokemonAdapter
import com.twinkslab.pokeapi.home.ui.listeners.ClickListenerPosition
import com.twinkslab.pokeapi.service.model.ApiChain
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.Chain
import com.twinkslab.pokeapi.service.model.Pokemon
import com.twinkslab.pokeapi.service.viewModel.ViewModelPokemon

class EvolutionPokemonFragment : Fragment() {

    private lateinit var binding : FragmentEvolutionPokemonBinding
    private val viewModelHome: ViewModelPokemon by viewModels()

    private lateinit var pokeChain:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvolutionPokemonBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments()
        viewModelHome.pokeChain.observe(this.viewLifecycleOwner,handlePokemonChain())
        service()
    }

    private fun arguments(){
        pokeChain = EvolutionPokemonFragmentArgs.fromBundle(this.requireArguments()).evolution
    }

    private fun service(){
        viewModelHome.getChain(pokeChain)
    }

    private fun handlePokemonChain(): (ApiChain?) -> Unit = { pokemonChain ->

        val listChains :ArrayList<ApiModelPokemon?> = chain(pokemonChain?.chain)


        val listener = object : ClickListenerPosition{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@EvolutionPokemonFragment.context,listChains.get(position)?.name,Toast.LENGTH_LONG).show()
            }

        }
        val adapter = ChainPokemonAdapter(listChains,listener)

        binding.apply {
            rvChains.layoutManager = LinearLayoutManager(this@EvolutionPokemonFragment.context, RecyclerView.VERTICAL, false)
            rvChains.adapter = adapter
        }

    }

    private fun chain(chain: Chain?):ArrayList<ApiModelPokemon?>{
        val list : ArrayList<ApiModelPokemon?> = arrayListOf()
        list.add(chain?.species)
        if (chain?.evolves_to?.isNotEmpty() == true){
            chain.evolves_to.forEach {
                var result = chain(it)
                list.addAll(result)
            }
        }
        return list
    }
}