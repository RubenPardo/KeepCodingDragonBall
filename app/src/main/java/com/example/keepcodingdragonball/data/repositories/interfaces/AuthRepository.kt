package com.example.keepcodingdragonball.data.repositories.interfaces

import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

interface AuthRepository {
    suspend fun saveCredentials(loginDataDO: LoginDataDO): Response<Boolean>
    suspend fun makeLogin(loginDataDO: LoginDataDO): Response<String>
    suspend fun getCredentials(): Response<LoginDataDO?>
    suspend fun removeCredentials()
    suspend fun saveToken(token: String?)
    suspend fun getToken(): Response<String?>
}
