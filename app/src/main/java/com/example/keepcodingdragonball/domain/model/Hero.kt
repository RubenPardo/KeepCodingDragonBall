package com.example.keepcodingdragonball.domain.model

import com.google.gson.Gson

data class Hero(val name:String,val photo:String,val description:String,val favorite:Boolean,val id:String){
    companion object{
        fun fromJson(json:String):Array<Hero>{
            return Gson().fromJson(json,Array<Hero>::class.java)
        }
    }
}