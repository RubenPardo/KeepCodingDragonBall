package com.example.keepcodingdragonball.data.datasources

import android.content.Context


interface SharedPreferencesService{
    fun getPrefString(context: Context, prefName: String, defaultValue: String): String?
    fun putPrefString(context: Context, prefName: String, value: String)
    fun removePrefString(context: Context, prefName: String)
}

class SharedPreferencesServiceImpl : SharedPreferencesService {

    override fun getPrefString(
        context: Context,
        prefName: String,
        defaultValue: String
    ): String? {
        val sp = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
        return sp.getString(prefName, defaultValue)
    }

    override fun putPrefString(
        context: Context,
        prefName: String,
        value: String
    ) {
        val sp = context.getSharedPreferences(prefName,Context.MODE_PRIVATE).edit()
        sp.putString(prefName, value)
        sp.apply()
    }

    override fun removePrefString(
        context: Context,
        prefName: String,
    ) {
        val sp = context.getSharedPreferences(prefName,Context.MODE_PRIVATE).edit()
        sp.remove(prefName)
        sp.apply()
    }

}