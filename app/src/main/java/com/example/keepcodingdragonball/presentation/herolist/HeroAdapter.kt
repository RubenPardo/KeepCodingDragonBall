package com.example.keepcodingdragonball.presentation.herolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keepcodingdragonball.R
import com.example.keepcodingdragonball.databinding.HeroItemBinding
import com.example.keepcodingdragonball.domain.model.Hero

class HeroAdapter(
    private val onItemClick: ((Hero) -> Unit),
    private val onDeleteItem: ((position:Int) -> Unit),
):RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    lateinit var binding: HeroItemBinding
    private var mList: MutableList<Hero> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = HeroItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val hero = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = "${hero.name} ${getParImpar(position)}"
        holder.root.setOnClickListener{
            onItemClick(hero)
        }
        holder.ivDelete.setOnClickListener {
            onDeleteItem(position)
        }

    }

    private fun getParImpar(position: Int): String {
        if(position == mList.size-1) return "Soy el ultimo"
        return if(position%2 == 0) "Soy par" else "Soy impar"
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setHeroList(heroes:List<Hero>){
        mList = heroes.toMutableList()
        notifyDataSetChanged()
    }

    fun removeHeroAt(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mList.size);
    }

    fun addHero() {
        mList.add(Hero("Heroe ${mList.size}"))
        notifyItemInserted(mList.size)
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = binding.tvTitle
        val root = binding.root
        val ivDelete = binding.ivBorrarItem
    }


}