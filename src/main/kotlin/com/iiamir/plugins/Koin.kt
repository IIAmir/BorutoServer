package com.iiamir.plugins

import com.iiamir.module
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import com.iiamir.di.koinModule

fun Application.configureKoin(){
    install(Koin){
        slf4jLogger()
        modules(koinModule)
    }
}