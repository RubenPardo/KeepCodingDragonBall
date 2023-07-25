package com.example.keepcodingdragonball.data.datasources.local.implementations

import com.example.keepcodingdragonball.data.datasources.local.interfaces.LocalDataSource
import com.example.keepcodingdragonball.data.datasources.local.model.HeroLocal
import com.example.keepcodingdragonball.data.datasources.local.room.HeroDao

class LocalDataSourceRoomImpl (
        private val heroDao: HeroDao
    ): LocalDataSource {

    override suspend fun insertHeroList(heroList: List<HeroLocal>) = heroDao.insertAll(heroList)

    override suspend fun getHeroList(): List<HeroLocal> = heroDao.getAll()
    override suspend fun getHeroById(id: String): HeroLocal{
        return heroDao.getById(id)
    }
}