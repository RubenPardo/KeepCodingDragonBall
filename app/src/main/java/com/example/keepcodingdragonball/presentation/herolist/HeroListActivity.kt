package com.example.keepcodingdragonball.presentation.herolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepcodingdragonball.databinding.ActivityHeroListBinding
import com.example.keepcodingdragonball.domain.model.Hero

class HeroListActivity : AppCompatActivity(), HeroAdapter.HeroAdapterInterface {


    private lateinit var binding: ActivityHeroListBinding
    private val viewModel: HeroListViewModel by viewModels()
    private lateinit var adapter : HeroAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        setObservers()
        setListeners()

    }

    private fun initRecyclerView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        adapter = HeroAdapter(this)
        binding.rvHeroes.adapter = adapter
    }

    override fun onItemClick(hero: Hero) {
        Toast.makeText(this,"Se pulso el heroe: ${hero.name}",Toast.LENGTH_LONG).show()
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
        viewModel.uiState.observe(this, this::handleUiState)
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
        Toast.makeText(this,messageError,Toast.LENGTH_SHORT).show()
    }

    private fun setListeners() {
        binding.floatingActionButton.setOnClickListener{
            adapter.addHero()
        }
    }


}