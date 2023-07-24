package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.domain.usecases.GetAllHeroesUseCase
import com.example.keepcodingdragonball.domain.usecases.LoginUseCase
import org.koin.dsl.module

val domainModel = module{

    factory <GetAllHeroesUseCase>{GetAllHeroesUseCase(get(),get())}
    factory <LoginUseCase>{LoginUseCase(get())}


}