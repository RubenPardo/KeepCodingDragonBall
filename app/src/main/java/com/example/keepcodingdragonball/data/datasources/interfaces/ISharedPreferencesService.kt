package com.example.keepcodingdragonball.data.datasources.interfaces

interface SharedPreferencesService {
    fun getPrefString(prefName: String, defaultValue: String): String?
    fun putPrefString(prefName: String, value: String)
    fun removePrefString(prefName: String)
}