package com.example.keepcodingdragonball.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepcodingdragonball.data.datasources.implementations.SharedPreferencesServiceImpl
import com.example.keepcodingdragonball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreferencesServiceImpl.init(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}