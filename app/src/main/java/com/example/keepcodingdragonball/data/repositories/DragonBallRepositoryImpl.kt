package com.example.keepcodingdragonball.data.repositories

import com.example.keepcodingdragonball.data.datasources.interfaces.DragonBallService
import com.example.keepcodingdragonball.data.mappers.toDomain
import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response

class DragonBallRepositoryImpl(
    private val dragonBallService: DragonBallService
): DragonBallRepository {


    override suspend fun getAllHeroes(name:String, token:String): Response<List<Hero>> {
        return try {

            when(val heroesDTO = dragonBallService.getHeroes(name,token)){
                is Response.Error -> Response.Error("Unable to get heroes")
                is Response.Success -> {
                    val heroesDO = heroesDTO.data?.let {
                        it.map { hero-> hero.toDomain() }
                    }
                   return heroesDO?.let {
                        Response.Success(it)
                    } ?: Response.Error("")

                }
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }

    }

}