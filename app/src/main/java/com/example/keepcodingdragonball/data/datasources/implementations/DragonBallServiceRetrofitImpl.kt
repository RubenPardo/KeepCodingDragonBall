package com.example.keepcodingdragonball.data.datasources.implementations

import com.example.keepcodingdragonball.data.datasources.apis.DragonBallApi
import com.example.keepcodingdragonball.data.datasources.interfaces.DragonBallService
import com.example.keepcodingdragonball.data.datasources.model.HeroDTO
import com.example.keepcodingdragonball.data.datasources.model.SearchDto
import com.example.keepcodingdragonball.domain.model.Response

class DragonBallServiceRetrofitImpl(
    private val dragonBallApi: DragonBallApi
) : DragonBallService {
    override suspend fun getHeroes(name: String, token: String): Response<List<HeroDTO>> = Response.Success(dragonBallApi.getHeroes(
        SearchDto(name)
    ))
}