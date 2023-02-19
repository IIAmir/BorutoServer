package com.iiamir.routes

import com.iiamir.core.Constants.WELCOME_MESSAGE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root(){
    get("/"){
        call.respond(
            message = WELCOME_MESSAGE,
            status = HttpStatusCode.OK
        )
    }
}