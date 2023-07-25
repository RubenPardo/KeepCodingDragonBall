package com.example.keepcodingdragonball.presentation.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.usecases.GetHeroDetailByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroDetailsViewModel(
    private val getHeroDetailByIdUseCase: GetHeroDetailByIdUseCase
) : ViewModel() {

    private val _heroLiveData = MutableLiveData<Hero>()
    val heroLiveData: LiveData<Hero> = _heroLiveData


    fun getHeroById(id:String){
        viewModelScope.launch (Dispatchers.IO){
            val result = getHeroDetailByIdUseCase.invoke(id)
            _heroLiveData.postValue(result)
        }
    }

}