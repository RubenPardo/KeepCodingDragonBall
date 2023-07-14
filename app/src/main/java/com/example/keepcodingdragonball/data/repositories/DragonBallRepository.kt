package com.example.keepcodingdragonball.data.repositories

import android.util.Log
import com.example.keepcodingdragonball.data.datasources.DragonBallService
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response
import java.lang.Exception

class DragonBallRepository {


    suspend fun getAllHeroes(name:String): Response<List<Hero>> {
        try {
            return when(val resToken = AuthRepositoryImpl().getToken()){
                is Response.Error -> Response.Error("No token")
                is Response.Success -> {
                    return when(val resJson = DragonBallService().getHeroes(name,resToken.data!!)){
                        is Response.Error -> Response.Error("Unable to get heroes")
                        is Response.Success -> {
                            val heroList = Hero.fromJson(resJson.data!!)
                            Response.Success(heroList.toList())
                        }
                    }

                }
            }
        }catch (e:Exception){
            return Response.Error(e.message)
        }

    }

}