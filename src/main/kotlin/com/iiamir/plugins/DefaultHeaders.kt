package com.iiamir.plugins

import com.iiamir.core.Constants.DURATION_OF_DAYS
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.http.*
import java.time.Duration

fun Application.configureDefaultHeader() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(DURATION_OF_DAYS).seconds
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=$oneYearInSeconds, immutable"
        )
    }
}