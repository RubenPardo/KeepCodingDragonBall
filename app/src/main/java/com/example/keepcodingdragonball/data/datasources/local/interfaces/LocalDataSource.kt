package com.example.keepcodingdragonball.data.datasources.local.interfaces

import com.example.keepcodingdragonball.data.datasources.local.model.HeroLocal

interface LocalDataSource {
    suspend fun insertHeroList(heroList: List<HeroLocal>)
    suspend fun getHeroList() : List<HeroLocal>
}