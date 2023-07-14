package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.data.repositories.AuthRepository
import com.example.keepcodingdragonball.data.repositories.AuthRepositoryImpl
import com.example.keepcodingdragonball.data.repositories.DragonBallRepository
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

class GetAllHeroresUseCase {

    private val dragonBallRepository: DragonBallRepository = DragonBallRepository()

    suspend operator fun invoke():List<Hero>{
        return when(val response = dragonBallRepository.getAllHeroes("")){
            is Response.Error -> listOf()
            is Response.Success -> response.data!!
        }

    }
}