package com.example.keepcodingdragonball.domain.usecases

import android.content.Context
import com.example.keepcodingdragonball.data.repositories.AuthRepository
import com.example.keepcodingdragonball.data.repositories.AuthRepositorySharedPref
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

class LoginUseCase {

    private val authRepository:AuthRepository = AuthRepositorySharedPref()

    suspend operator fun invoke(
        loginDataDO: LoginDataDO,
        saveCredentials: Boolean,
        context: Context
    ):Boolean{
        // make login ----------------
        // if(login) ---
        return if(saveCredentials){
            when (authRepository.saveCredentials(context, loginDataDO)){
                is Response.Success ->{
                    true
                }
                is Response.Error ->   {
                    false
                }
            }
        }else{
            true
        }

    }
}