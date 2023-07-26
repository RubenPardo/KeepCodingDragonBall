package com.example.keepcodingdragonball.presentation.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.LocationModel
import com.example.keepcodingdragonball.domain.model.Response
import com.example.keepcodingdragonball.domain.usecases.GetHeroDetailByIdUseCase
import com.example.keepcodingdragonball.domain.usecases.GetHeroLasLocationUseCase
import com.example.keepcodingdragonball.domain.usecases.SetHeroFavByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroDetailsViewModel(
    private val getHeroDetailByIdUseCase: GetHeroDetailByIdUseCase,
    private val getHeroLasLocationUseCase: GetHeroLasLocationUseCase,
    private val setHeroFavByIdUseCase: SetHeroFavByIdUseCase
) : ViewModel() {

    private val _heroLiveData = MutableLiveData<Hero>()
    val heroLiveData: LiveData<Hero> = _heroLiveData

    private val _heroLastLocationLiveData = MutableLiveData<LocationModel>()
    val heroLastLocationLiveData: LiveData<LocationModel> = _heroLastLocationLiveData

    private lateinit var heroModel:Hero

    fun getData(id: String) {
        getHeroById(id)
        getLasLocation(id)
    }

    private fun getLasLocation(id: String) = viewModelScope.launch (Dispatchers.IO){
        val result = getHeroDetailByIdUseCase.invoke(id)
        heroModel = result
        _heroLiveData.postValue(result)
    }

    private fun getHeroById(id:String){
        viewModelScope.launch (Dispatchers.IO){
            val result = getHeroLasLocationUseCase.invoke(id)
            result?.let {
                _heroLastLocationLiveData.postValue(it)
            }
        }
    }

    fun changeFavHeroById() = viewModelScope.launch (Dispatchers.IO){
        when(val result = setHeroFavByIdUseCase.invoke(heroModel,!heroModel.favorite)){
            is Response.Error -> {}
            is Response.Success -> {
                heroModel = result.data!!
                _heroLiveData.postValue(heroModel)
            }
        }
    }

}