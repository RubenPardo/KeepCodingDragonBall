package com.example.keepcodingdragonball.presentation.herolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keepcodingdragonball.R
import com.example.keepcodingdragonball.databinding.HeroItemBinding
import com.example.keepcodingdragonball.domain.model.Hero

class HeroAdapter(private val mList: List<Hero>):RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    lateinit var binding: HeroItemBinding

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

    }

    private fun getParImpar(position: Int): String {
        if(position == itemCount-1) return "Soy el ultimo"
        return if(position%2 == 0) "Soy par" else "Soy impar"
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = binding.tvTitle
    }


}