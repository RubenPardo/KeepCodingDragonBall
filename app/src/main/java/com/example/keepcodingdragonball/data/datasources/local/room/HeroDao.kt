package com.example.keepcodingdragonball.data.datasources.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keepcodingdragonball.data.datasources.local.model.HeroLocal

@Dao
interface HeroDao {

    @Query("SELECT * FROM SuperHeroTable")
    suspend fun getAll(): List<HeroLocal>

    // TODO
    //@Query("SELECT * FROM SuperHeroTable")
    //suspend fun getFavorites(): List<HeroLocal>

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertAll(vararg superHero: HeroLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<HeroLocal>)

    @Delete
    suspend fun delete(model: HeroLocal)
    @Query("SELECT * FROM SuperHeroTable WHERE id = :id")
    suspend fun getById(id: String): HeroLocal
}