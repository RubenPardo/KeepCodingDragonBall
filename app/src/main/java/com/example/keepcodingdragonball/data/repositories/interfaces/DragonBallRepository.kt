package com.example.keepcodingdragonball.data.repositories.interfaces

import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response

interface DragonBallRepository {
    suspend fun getAllHeroes(name:String): Response<List<Hero>>
    suspend fun getHeroById(id: String): Hero
    suspend fun updateHero(hero:Hero): Boolean
}