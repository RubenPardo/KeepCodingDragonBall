package com.example.keepcodingdragonball.presentation.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.presentation.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HeroListViewModel : ViewModel() {

    private val _uiState = MutableLiveData<HeroListUiState>(HeroListUiState.InitState)
    val uiState: LiveData<HeroListUiState> = _uiState


    init {
        viewModelScope.launch {
            getHeroes()
        }
    }

    private suspend fun getHeroes() {

        _uiState.value = HeroListUiState.Loading

        delay(1000)
        val heroes = List<Hero>(16){
            Hero("Heroe $it")
        }

        _uiState.value = HeroListUiState.Loaded(heroes)

    }


}


sealed class HeroListUiState{
    object InitState:HeroListUiState()
    object Loading:HeroListUiState()
    class Error(val messageError:String):HeroListUiState()
    class Loaded(val heroes:List<Hero>):HeroListUiState()
}