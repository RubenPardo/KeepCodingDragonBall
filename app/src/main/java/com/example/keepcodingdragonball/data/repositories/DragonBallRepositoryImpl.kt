package com.example.keepcodingdragonball.data.repositories

import com.example.keepcodingdragonball.data.datasources.DragonBallService
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response
import java.lang.Exception

class DragonBallRepositoryImpl(
    private val dragonBallService: DragonBallService
): DragonBallRepository {


    override fun getAllHeroes(name:String, token:String): Response<List<Hero>> {
        return try {

            when(val resJson = dragonBallService.getHeroes(name,token)){
                is Response.Error -> Response.Error("Unable to get heroes")
                is Response.Success -> {
                    val heroList = Hero.fromJson(resJson.data!!)
                    Response.Success(heroList.toList())
                }
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }

    }

}