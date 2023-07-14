package com.example.keepcodingdragonball.domain.model

import com.google.gson.Gson
import java.io.Serializable

data class LoginDataDO(val name:String,val password:String):Serializable{
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(result: String): LoginDataDO? {
            return Gson().fromJson(result,LoginDataDO::class.java)
        }
    }
}