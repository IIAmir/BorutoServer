package com.iiamir.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(
                message = "Page not Found.",
                status = HttpStatusCode.NotFound
            )
        }
//        exception<AuthenticationException> {
//            call.respond(
//                message = "We cought an exception!",
//                status = HttpStatusCode.OK
//            )
//        }
    }
}