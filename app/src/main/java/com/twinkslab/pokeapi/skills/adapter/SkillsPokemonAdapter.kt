package com.twinkslab.pokeapi.evolution.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twinkslab.pokeapi.databinding.ChainItemListBinding
import com.twinkslab.pokeapi.databinding.SkillItemListBinding
import com.twinkslab.pokeapi.home.ui.listeners.ClickListenerPosition
import com.twinkslab.pokeapi.service.model.Ability
import com.twinkslab.pokeapi.service.model.ApiModelPokemon

class SkillsPokemonAdapter(
    private var chains: ArrayList<Ability?>? = ArrayList(),
    private val clickListenerPosition: ClickListenerPosition? = null,
) : RecyclerView.Adapter<SkillsPokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private var binding: SkillItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.chain

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SkillItemListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chains?.size?:0
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = chains?.getOrNull(position)?.ability?.name
    }
}
