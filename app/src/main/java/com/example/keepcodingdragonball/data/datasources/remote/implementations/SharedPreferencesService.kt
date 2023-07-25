package com.example.keepcodingdragonball.data.datasources.remote.implementations

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.keepcodingdragonball.data.datasources.remote.interfaces.SharedPreferencesService

class SharedPreferencesServiceImpl : SharedPreferencesService {


    companion object{
        @Volatile
        var mSharedPref: SharedPreferences? = null
        fun init(context: Context) {
            mSharedPref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        }
    }

    override fun getPrefString(
        prefName: String,
        defaultValue: String
    ): String? {
        return mSharedPref!!.getString(prefName, defaultValue)
    }

    override fun putPrefString(
        prefName: String,
        value: String
    ) {
        val sp = mSharedPref!!.edit()
        sp.putString(prefName, value)
        sp.apply()
    }

    override fun removePrefString(
        prefName: String,
    ) {
        val sp = mSharedPref!!.edit()
        sp.remove(prefName)
        sp.apply()
    }

}