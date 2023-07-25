package com.example.keepcodingdragonball.data.datasources.remote.interfaces

import com.example.keepcodingdragonball.data.datasources.remote.model.HeroDTO
import com.example.keepcodingdragonball.domain.model.Response

interface RemoteDataSource {
    suspend fun getHeroes(name:String): Response<List<HeroDTO>>
}