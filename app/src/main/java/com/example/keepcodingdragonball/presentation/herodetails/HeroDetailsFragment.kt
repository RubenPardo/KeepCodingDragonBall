package com.example.keepcodingdragonball.presentation.herodetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keepcodingdragonball.core.serializable
import com.example.keepcodingdragonball.databinding.FragmentHeroDetailsBinding
import com.example.keepcodingdragonball.domain.model.Hero
import com.squareup.picasso.Picasso


private const val ARG_PARAM1 = "hero"

class HeroDetailsFragment : Fragment() {

    private lateinit var hero: Hero
    private lateinit var binding: FragmentHeroDetailsBinding
    //private val viewModel: HeroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.serializable<Hero>(ARG_PARAM1)?.let { hero ->
                this.hero = hero
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)

        setUpViews()
        return binding.root
    }

    private fun setUpViews() {
        binding.tvName.text = hero.name
        binding.tvDescription.text = hero.description
        Picasso.with(context).load(hero.photo).into(binding.ivHero)
    }

    companion object {
        /**
         * @param hero Hero to show details
         * @return A new instance of fragment HeroDetailsFragment.
         */
        @JvmStatic
        fun newInstance(hero: Hero) =
            HeroDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, hero)
                }
            }
    }
}