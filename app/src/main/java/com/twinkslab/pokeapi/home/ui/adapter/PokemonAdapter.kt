package com.twinkslab.pokeapi.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.databinding.PokemonItemListBinding
import com.twinkslab.pokeapi.evolution.adapter.ChainPokemonAdapter
import com.twinkslab.pokeapi.home.ui.listeners.ClickListenerPosition
import com.twinkslab.pokeapi.service.model.ApiModelPokemon

class PokemonAdapter(
    private var pokeList: ArrayList<ApiModelPokemon>? = ArrayList(),
    private val clickListenerPosition: ClickListenerPosition? = null,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private var binding: PokemonItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.pokename

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokeList?.size?:0
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = pokeList?.getOrNull(position)?.name?:""
        holder.name.setOnClickListener{
            clickListenerPosition?.onItemClick(position)
        }
    }
}
