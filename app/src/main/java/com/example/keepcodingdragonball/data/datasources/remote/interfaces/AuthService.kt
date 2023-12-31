package com.example.keepcodingdragonball.data.datasources.remote.interfaces

import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response

interface AuthService {
    suspend fun login(loginDataDO: LoginDataDO): Response<String>
}