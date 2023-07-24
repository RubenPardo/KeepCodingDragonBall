package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.data.datasources.AuthService
import com.example.keepcodingdragonball.data.datasources.DragonBallService
import com.example.keepcodingdragonball.data.datasources.SharedPreferencesService
import com.example.keepcodingdragonball.data.datasources.SharedPreferencesServiceImpl
import com.example.keepcodingdragonball.data.repositories.AuthRepository
import com.example.keepcodingdragonball.data.repositories.AuthRepositoryImpl
import com.example.keepcodingdragonball.data.repositories.DragonBallRepository
import com.example.keepcodingdragonball.data.repositories.DragonBallRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module{
    single <AuthRepository>{ AuthRepositoryImpl(get(),get())  }
    single <DragonBallRepository>{ DragonBallRepositoryImpl(get())  }

    single <SharedPreferencesService>{ SharedPreferencesServiceImpl()  }
    single <AuthService>{ AuthService()  }
    single <DragonBallService>{ DragonBallService()  }



    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi>{
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}