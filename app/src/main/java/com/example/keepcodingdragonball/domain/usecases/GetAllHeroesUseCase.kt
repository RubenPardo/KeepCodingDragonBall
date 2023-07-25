package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.data.repositories.interfaces.AuthRepository
import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response

class GetAllHeroesUseCase(
    private val dragonBallRepository: DragonBallRepository,
    private val authRepository: AuthRepository
) {


    suspend operator fun invoke():List<Hero>{
        val token:String? = when(val responseToken = authRepository.getToken()){
            is Response.Error -> null
            is Response.Success -> responseToken.data
        }
        token?.let {
            return when(val response = dragonBallRepository.getAllHeroes("",it)){
                is Response.Error -> listOf()
                is Response.Success -> response.data!!
            }
        } ?: return listOf()


    }
}