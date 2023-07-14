package com.example.keepcodingdragonball.data.datasources

import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class DragonBallService {

    fun getHeroes(name: String,token:String): Response<String> {
        val client =  OkHttpClient()
        val url = "${BASE_URL}heros/all"


        val formBody = FormBody.Builder()
            .add("name",name).build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .addHeader("Authorization","Bearer $token")
            .build()
        val call = client.newCall(request)
        val response:okhttp3.Response = call.execute()
        return if(response.isSuccessful){
            response.body?.let {
                Response.Success(response.body!!.string())
            } ?: Response.Error(response.message)
        }else{
            Response.Error(response.message)
        }
    }

}