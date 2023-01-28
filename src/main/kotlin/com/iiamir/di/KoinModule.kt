package com.iiamir.di

import com.iiamir.repository.HeroRepository
import com.iiamir.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> { HeroRepositoryImpl() }
}