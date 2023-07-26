package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.domain.usecases.GetAllHeroesUseCase
import com.example.keepcodingdragonball.domain.usecases.GetDistanceFroHeroUseCase
import com.example.keepcodingdragonball.domain.usecases.GetHeroDetailByIdUseCase
import com.example.keepcodingdragonball.domain.usecases.GetHeroLasLocationUseCase
import com.example.keepcodingdragonball.domain.usecases.LoginUseCase
import com.example.keepcodingdragonball.domain.usecases.SetHeroFavByIdUseCase
import org.koin.dsl.module

val domainModel = module{

    factory {GetAllHeroesUseCase(get())}
    factory {LoginUseCase(get())}
    factory {GetHeroDetailByIdUseCase(get())}
    factory {SetHeroFavByIdUseCase(get())}
    factory {GetHeroLasLocationUseCase(get())}
    factory {GetDistanceFroHeroUseCase()}


}