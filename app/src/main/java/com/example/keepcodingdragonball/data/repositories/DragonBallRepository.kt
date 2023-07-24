package com.example.keepcodingdragonball.data.repositories

import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.Response

interface DragonBallRepository {
    fun getAllHeroes(name:String, token:String): Response<List<Hero>>
}