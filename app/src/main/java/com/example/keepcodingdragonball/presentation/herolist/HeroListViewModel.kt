package com.example.keepcodingdragonball.presentation.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.usecases.GetAllHeroesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val getAllHeroesUseCase: GetAllHeroesUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<HeroListUiState>(HeroListUiState.InitState)
    val uiState: LiveData<HeroListUiState> = _uiState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getHeroes()
        }
    }

    private suspend fun getHeroes() {

        _uiState.postValue(HeroListUiState.Loading)

        val heroes = getAllHeroesUseCase.invoke()

        _uiState.postValue( HeroListUiState.Loaded(heroes))

    }


}


sealed class HeroListUiState{
    object InitState:HeroListUiState()
    object Loading:HeroListUiState()
    class Error(val messageError:String):HeroListUiState()
    class Loaded(val heroes:List<Hero>):HeroListUiState()
}