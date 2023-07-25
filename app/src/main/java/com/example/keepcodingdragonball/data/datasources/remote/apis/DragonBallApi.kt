package com.example.keepcodingdragonball.data.datasources.remote.apis

import com.example.keepcodingdragonball.data.datasources.remote.model.HeroDTO
import com.example.keepcodingdragonball.data.datasources.remote.model.SearchDto
import com.example.keepcodingdragonball.domain.model.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val TOKEN:String = "eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJleHBpcmF0aW9uIjo2NDA5MjIxMTIwMCwiZW1haWwiOiJiZWpsQGtlZXBjb2RpbmcuZXMiLCJpZGVudGlmeSI6IjdBQjhBQzRELUFEOEYtNEFDRS1BQTQ1LTIxRTg0QUU4QkJFNyJ9.lehepzhRj4gt7ThkRdsPI9aAEph8SgGLMZOjJ1534jI"
interface DragonBallApi{
    @POST("heros/all")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getHeroes(@Body name: SearchDto): List<HeroDTO>
}