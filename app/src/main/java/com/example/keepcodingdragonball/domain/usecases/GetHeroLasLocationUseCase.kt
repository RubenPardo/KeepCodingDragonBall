package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository
import com.example.keepcodingdragonball.domain.model.LocationModel

class GetHeroLasLocationUseCase(
    private val dragonBallRepository: DragonBallRepository
) {

    suspend fun invoke(id:String): LocationModel?{
       val locations = dragonBallRepository.getHeroLocations(id)
        return if(locations.isNotEmpty()){
            locations.last()
        }else{
            null
        }
    }

}