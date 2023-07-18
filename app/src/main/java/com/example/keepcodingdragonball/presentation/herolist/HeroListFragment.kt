package com.example.keepcodingdragonball.presentation.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepcodingdragonball.R
import com.example.keepcodingdragonball.databinding.ActivityHeroListBinding
import com.example.keepcodingdragonball.databinding.FragmentHeroListBinding
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.presentation.login.LoginFragmentDirections


class HeroListFragment : Fragment(), HeroAdapter.HeroAdapterInterface{

    private lateinit var binding: FragmentHeroListBinding
    private val viewModel: HeroListViewModel by viewModels()
    private lateinit var adapter : HeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHeroListBinding.inflate(layoutInflater)

        initRecyclerView()
        setObservers()
        setListeners()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HeroListFragment()
    }

    private fun initRecyclerView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(context)
        adapter = HeroAdapter(this)
        binding.rvHeroes.adapter = adapter
    }

    override fun onItemClick(hero: Hero) {
        Toast.makeText(context,"Se pulso el heroe: ${hero.name}", Toast.LENGTH_LONG).show()
        val directions = HeroListFragmentDirections.navigateToHeroDetails(hero)
        findNavController().navigate(directions)

    }

    override fun onDeleteItem(position: Int) {
        // se puede hacer directamente desde el adapter pero de esta forma si luego tenemos que
        // borrar en bd ya tendriamos el paso hecho
        adapter.removeHeroAt(position)
    }


    private fun setHeroListToAdapter(heroes:List<Hero>) {
        adapter.setHeroList(heroes)
    }


    private fun setObservers() {
        viewModel.uiState.observe(this.viewLifecycleOwner, this::handleUiState)
    }

    private fun handleUiState(heroListUiState: HeroListUiState) {
        showLoading(heroListUiState is HeroListUiState.Loading)
        when(heroListUiState){
            is HeroListUiState.Error -> showError(heroListUiState.messageError)
            HeroListUiState.InitState -> {}
            is HeroListUiState.Loaded -> setHeroListToAdapter(heroListUiState.heroes)
            HeroListUiState.Loading -> { }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding){
            rvHeroes.visibility = if(isLoading) View.GONE else View.VISIBLE
            pbHeroes.visibility = if(!isLoading) View.GONE else View.VISIBLE
        }
    }

    private fun showError(messageError: String) {
        Toast.makeText(context,messageError, Toast.LENGTH_SHORT).show()
    }

    private fun setListeners() {
        binding.floatingActionButton.setOnClickListener{
            adapter.addHero()
        }
    }
}