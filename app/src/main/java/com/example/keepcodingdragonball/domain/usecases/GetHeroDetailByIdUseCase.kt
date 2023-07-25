package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository

class GetHeroDetailByIdUseCase(
    private val dragonBallRepository: DragonBallRepository
) {

    suspend fun invoke(id:String) = dragonBallRepository.getHeroById(id)
}