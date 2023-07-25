package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.data.datasources.apis.DragonBallApi
import com.example.keepcodingdragonball.data.datasources.implementations.AuthServiceImpl
import com.example.keepcodingdragonball.data.datasources.implementations.DragonBallServiceRetrofitImpl
import com.example.keepcodingdragonball.data.datasources.implementations.SharedPreferencesServiceImpl
import com.example.keepcodingdragonball.data.datasources.interfaces.AuthService
import com.example.keepcodingdragonball.data.datasources.interfaces.DragonBallService
import com.example.keepcodingdragonball.data.datasources.interfaces.SharedPreferencesService
import com.example.keepcodingdragonball.data.repositories.AuthRepositoryImpl
import com.example.keepcodingdragonball.data.repositories.DragonBallRepositoryImpl
import com.example.keepcodingdragonball.data.repositories.interfaces.AuthRepository
import com.example.keepcodingdragonball.data.repositories.interfaces.DragonBallRepository
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
    single <AuthService>{ AuthServiceImpl()  }
    single <DragonBallService>{ DragonBallServiceRetrofitImpl(get())  }

    single<DragonBallApi>{
        getDragonBallService(get())
    }


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

private fun getDragonBallService(retrofit: Retrofit) = retrofit.create(DragonBallApi::class.java)