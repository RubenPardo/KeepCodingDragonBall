package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.data.repositories.interfaces.AuthRepository
import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response

class GetAllHeroesUseCase(
    private val dragonBallRepository: DragonBallRepository,
) {


    suspend operator fun invoke():List<Hero>{

        return when(val response = dragonBallRepository.getAllHeroes("")){
            is Response.Error -> listOf()
            is Response.Success -> response.data!!
        }



    }
}