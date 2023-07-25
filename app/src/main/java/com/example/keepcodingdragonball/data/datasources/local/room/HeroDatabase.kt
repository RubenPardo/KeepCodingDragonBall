package com.example.keepcodingdragonball.data.datasources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keepcodingdragonball.data.datasources.local.model.HeroLocal

@Database(entities = [HeroLocal::class], version = 1)
//Database(entities = [SuperHeroLocal::class, AnotherLocal::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {
    // List of Dao
    abstract fun superHeroDao(): HeroDao
}