package com.iiamir.plugins

import com.iiamir.core.Constants.PAGE_NOT_FOUND_ERROR
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(
                message = PAGE_NOT_FOUND_ERROR,
                status = HttpStatusCode.NotFound
            )
        }
    }
}