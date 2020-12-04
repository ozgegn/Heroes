package com.ozgegn.heroes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.heroes.R
import com.ozgegn.heroes.data.local.HeroEntity
import com.ozgegn.heroes.databinding.ItemHeroListBinding

class AdapterHeroes : RecyclerView.Adapter<AdapterHeroes.HeroesViewHolder>() {

    private var items: MutableList<HeroEntity> = mutableListOf()

    class HeroesViewHolder(val binding: ItemHeroListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding = DataBindingUtil.inflate<ItemHeroListBinding>(
            LayoutInflater.from(parent.context), R.layout.item_hero_list, parent, false
        )

        return HeroesViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            this.hero = item
            executePendingBindings()
        }
    }

    fun addHeroes(newItems: List<HeroEntity>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addHeroListAfterReset(movieList: List<HeroEntity>) {
        items.apply {
            clear()
            addAll(movieList)
        }
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        items.clear()
    }

}