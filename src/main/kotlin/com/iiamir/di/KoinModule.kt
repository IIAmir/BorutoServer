package com.iiamir.di

import com.iiamir.repository.HeroRepository
import com.iiamir.repository.HeroRepositoryAlternative
import com.iiamir.repository.HeroRepositoryImpl
import com.iiamir.repository.HeroRepositoryImplAlternative
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
    single<HeroRepositoryAlternative> {
        HeroRepositoryImplAlternative()
    }
}