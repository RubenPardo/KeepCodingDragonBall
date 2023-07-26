package com.example.keepcodingdragonball.data.datasources.remote.implementations

import com.example.keepcodingdragonball.data.datasources.remote.interfaces.RemoteDataSource
import com.example.keepcodingdragonball.data.datasources.remote.model.HeroDTO
import com.example.keepcodingdragonball.data.datasources.remote.model.LocationDto
import com.example.keepcodingdragonball.domain.model.Response
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteDataSourceOkHttpImpl : RemoteDataSource {

    override suspend fun getHeroes(name: String): Response<List<HeroDTO>> {
        val client =  OkHttpClient()
        val url = "${BASE_URL}heros/all"


        val formBody = FormBody.Builder()
            .add("name",name).build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            //.addHeader("Authorization","Bearer $token")
            .build()
        val call = client.newCall(request)
        val response:okhttp3.Response = call.execute()
        return if(response.isSuccessful){
            response.body?.let {
               // Response.Success(response.body!!.string())
                Response.Success(listOf())
            } ?: Response.Error(response.message)
        }else{
            Response.Error(response.message)
        }
    }

    override suspend fun getLocationsHero(id: String): List<LocationDto> {
        return listOf()
    }

}