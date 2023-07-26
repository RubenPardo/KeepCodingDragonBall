package com.example.keepcodingdragonball.di

import com.example.keepcodingdragonball.presentation.herodetails.HeroDetailsViewModel
import com.example.keepcodingdragonball.presentation.herolist.HeroListViewModel
import com.example.keepcodingdragonball.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module{
    viewModel{ LoginViewModel(get(),get()) }
    viewModelOf(::LoginViewModel)
    viewModel{ HeroListViewModel(get()) }
    viewModelOf(::HeroListViewModel)
    viewModel{ HeroDetailsViewModel(get(), get()) }
    viewModelOf(::HeroDetailsViewModel)
}