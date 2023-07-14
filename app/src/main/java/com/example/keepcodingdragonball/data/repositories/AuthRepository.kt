package com.example.keepcodingdragonball.data.repositories

import android.content.Context
import android.util.Log
import com.example.keepcodingdragonball.data.datasources.AuthService
import com.example.keepcodingdragonball.data.datasources.SharedPreferencesServiceImpl
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

interface AuthRepository {
    suspend fun saveCredentials(loginDataDO: LoginDataDO):Response<Boolean>
    suspend fun makeLogin(loginDataDO: LoginDataDO):Response<String>
    suspend fun getCredentials():Response<LoginDataDO?>
    suspend fun removeCredentials()
    suspend fun saveToken(token: String?)
    suspend fun getToken():Response<String?>
}


class AuthRepositoryImpl: AuthRepository{

    private val loginDataKEY = "LOGIN"
    private val tokenDataKEY = "TOKEN"

    override suspend fun saveCredentials( loginDataDO: LoginDataDO): Response<Boolean> {
        return try {
            SharedPreferencesServiceImpl().putPrefString( loginDataKEY, loginDataDO.toJson())
            Response.Success(true)
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun makeLogin(loginDataDO: LoginDataDO): Response<String> {

        val response = AuthService().login(loginDataDO)
       try {
           return when(response){
               is Response.Error -> Response.Error(response.message)
               is Response.Success -> {
                   response.data?.let {
                       return Response.Success(it)
                   } ?: return Response.Error("Unable to login")
               }
           }
       }catch (e:Exception){
           Log.e("LOGIN",e.message.toString())
           return Response.Error("Unable to login")
       }
    }

    override suspend fun getCredentials(): Response<LoginDataDO?> {
        return try {
            val loginDataStr = SharedPreferencesServiceImpl().getPrefString(loginDataKEY,"")
            if(loginDataStr!=null && loginDataStr != ""){
                Response.Success(LoginDataDO.fromJson(loginDataStr))
            }else{
                Response.Success(null)
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun removeCredentials() {
        SharedPreferencesServiceImpl().removePrefString(loginDataKEY)

    }

    override suspend fun saveToken(token: String?) {
        Log.d("TOKEN",token.toString())
        SharedPreferencesServiceImpl().putPrefString( tokenDataKEY, token!!)
    }

    override suspend fun getToken():Response<String?> {
        return try {
            val token = SharedPreferencesServiceImpl().getPrefString(tokenDataKEY,"")
            if(token!=null && token != ""){
                Response.Success(token)
            }else{
                Response.Error("No token")
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }


}