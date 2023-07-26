package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.domain.usecases.GetAllHeroesUseCase
import com.example.keepcodingdragonball.domain.usecases.GetHeroDetailByIdUseCase
import com.example.keepcodingdragonball.domain.usecases.LoginUseCase
import com.example.keepcodingdragonball.domain.usecases.SetHeroFavByIdUseCase
import org.koin.dsl.module

val domainModel = module{

    factory <GetAllHeroesUseCase>{GetAllHeroesUseCase(get())}
    factory <LoginUseCase>{LoginUseCase(get())}
    factory <GetHeroDetailByIdUseCase>{GetHeroDetailByIdUseCase(get())}
    factory <SetHeroFavByIdUseCase>{SetHeroFavByIdUseCase(get())}


}