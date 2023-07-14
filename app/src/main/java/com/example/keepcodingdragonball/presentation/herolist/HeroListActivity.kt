package com.example.keepcodingdragonball.presentation.herolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepcodingdragonball.databinding.ActivityHeroListBinding
import com.example.keepcodingdragonball.domain.model.Hero

class HeroListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHeroListBinding
    private val viewModel: HeroListViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        setObservers()
        setListeners()

    }

    private fun initRecyclerView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this@HeroListActivity)
    }

    private fun setAdapter(heroes:List<Hero>) {
        with(binding){
            rvHeroes.adapter = HeroAdapter(heroes)
        }
    }


    private fun setObservers() {
        viewModel.uiState.observe(this, this::handleUiState)
    }

    private fun handleUiState(heroListUiState: HeroListUiState) {
        showLoading(heroListUiState is HeroListUiState.Loading)
        when(heroListUiState){
            is HeroListUiState.Error -> showError(heroListUiState.messageError)
            HeroListUiState.InitState -> {}
            is HeroListUiState.Loaded -> setAdapter(heroListUiState.heroes)
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

    }
}