package com.twinkslab.pokeapi.evolution.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.databinding.ChainItemListBinding
import com.twinkslab.pokeapi.home.ui.listeners.ClickListenerPosition
import com.twinkslab.pokeapi.service.model.ApiModelPokemon
import com.twinkslab.pokeapi.service.model.Chain

class ChainPokemonAdapter(
    private var chains: ArrayList<ApiModelPokemon?>? = ArrayList(),
    private val clickListenerPosition: ClickListenerPosition? = null,
) : RecyclerView.Adapter<ChainPokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private var binding: ChainItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.chain

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChainItemListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chains?.size?:0
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = chains?.getOrNull(position)?.name
        holder.name.setOnClickListener{
            clickListenerPosition?.onItemClick(position)
        }
    }
}
