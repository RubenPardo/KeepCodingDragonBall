package com.example.keepcodingdragonball.presentation.herodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.keepcodingdragonball.databinding.FragmentHeroDetailsBinding
import com.example.keepcodingdragonball.domain.model.Hero
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val ARG_PARAM1 = "hero_id"

class HeroDetailsFragment : Fragment() {

    private lateinit var heroId: String
    private lateinit var binding: FragmentHeroDetailsBinding
    private val viewModel: HeroDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getString(ARG_PARAM1)?.let { hero ->
                this.heroId = hero
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservable()
        setListeners()
        viewModel.getHeroById(heroId)
    }

    private fun setListeners() {
        binding.ivFav.setOnClickListener { viewModel.changeFavHeroById() }
    }

    private fun setObservable() {
        viewModel.heroLiveData.observe(this.viewLifecycleOwner,this::setUpViews)
    }

    private fun setUpViews(hero:Hero) {
        binding.tvName.text = hero.name
        binding.tvDescription.text = hero.description
        Picasso.with(context).load(hero.photo).into(binding.ivHero)
        binding.ivFav.setImageResource(if(hero.favorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)

    }
}