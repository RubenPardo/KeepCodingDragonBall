package com.example.keepcodingdragonball.data.datasources.implementations

import com.example.keepcodingdragonball.data.datasources.interfaces.AuthService
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

const val BASE_URL = "https://dragonball.keepcoding.education/api/"

class AuthServiceImpl: AuthService{

    /**
     * Returns de Acces Token
     */
    override suspend fun login(loginDataDO: LoginDataDO):Response<String>{
       val client =  OkHttpClient()
        val url = "${BASE_URL}auth/login"

        val credentials = Credentials.basic(loginDataDO.name,loginDataDO.password)
        val formBody = FormBody.Builder().build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .addHeader("Authorization",credentials)
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