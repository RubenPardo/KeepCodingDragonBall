package com.example.keepcodingdragonball.data.repositories

import android.content.Context
import com.example.keepcodingdragonball.data.datasources.SharedPreferencesServiceImpl
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response
import java.lang.Exception

interface AuthRepository {
    suspend fun saveCredentials(context:Context, loginDataDO: LoginDataDO):Response<Boolean>
    suspend fun getCredentials(context:Context):Response<LoginDataDO?>
    suspend fun removeCredentials(context:Context)
}


class AuthRepositorySharedPref: AuthRepository{

    private val loginDataKEY = "LOGIN"

    override suspend fun saveCredentials(context:Context, loginDataDO: LoginDataDO): Response<Boolean> {
        return try {
            SharedPreferencesServiceImpl().putPrefString(context, loginDataKEY, loginDataDO.toJson())
            Response.Success(true)
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun getCredentials(context: Context): Response<LoginDataDO?> {
        return try {
            val loginDataStr = SharedPreferencesServiceImpl().getPrefString(context, loginDataKEY,"")
            if(loginDataStr!=null && loginDataStr != ""){
                Response.Success(LoginDataDO.fromJson(loginDataStr))
            }else{
                Response.Success(null)
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun removeCredentials(context: Context) {
        SharedPreferencesServiceImpl().removePrefString(context, loginDataKEY)

    }


}