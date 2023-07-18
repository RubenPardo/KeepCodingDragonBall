package com.example.keepcodingdragonball.domain.usecases

import android.content.Context
import com.example.keepcodingdragonball.data.repositories.AuthRepository
import com.example.keepcodingdragonball.data.repositories.AuthRepositoryImpl
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

class LoginUseCase {

    private val authRepository:AuthRepository = AuthRepositoryImpl()

    suspend operator fun invoke(
        loginDataDO: LoginDataDO,
        saveCredentials: Boolean,
    ):Boolean{
        // make login ----------------
        // if(login) ---
        return when(val response = authRepository.makeLogin(loginDataDO)){
            is Response.Error -> false
            is Response.Success -> {
                authRepository.saveToken(response.data)
                if(saveCredentials) authRepository.saveCredentials(loginDataDO)
                return true
            }
        }


    }
}