package com.example.keepcodingdragonball.data.datasources.remote.implementations

import com.example.keepcodingdragonball.data.datasources.remote.apis.DragonBallApi
import com.example.keepcodingdragonball.data.datasources.remote.interfaces.RemoteDataSource
import com.example.keepcodingdragonball.data.datasources.remote.model.HeroDTO
import com.example.keepcodingdragonball.data.datasources.remote.model.SearchDto
import com.example.keepcodingdragonball.domain.model.Response

class RemoteDataSourceRetrofitImpl(
    private val dragonBallApi: DragonBallApi
) : RemoteDataSource {
    override suspend fun getHeroes(name: String): Response<List<HeroDTO>> = Response.Success(dragonBallApi.getHeroes(
        SearchDto(name)
    ))
}