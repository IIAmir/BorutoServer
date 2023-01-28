package com.iiamir

import io.ktor.server.application.*
import com.iiamir.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureStatusPages()
    configureDefaultHeader()
}
