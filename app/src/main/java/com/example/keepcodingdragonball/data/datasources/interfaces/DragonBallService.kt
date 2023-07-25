package com.example.keepcodingdragonball.data.datasources.interfaces

import com.example.keepcodingdragonball.data.datasources.model.HeroDTO
import com.example.keepcodingdragonball.domain.model.Response

interface DragonBallService {
    suspend fun getHeroes(name:String, token: String): Response<List<HeroDTO>>
}