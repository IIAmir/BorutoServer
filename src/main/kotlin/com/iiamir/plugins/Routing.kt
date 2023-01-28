package com.iiamir.plugins

import com.iiamir.routes.getAllHeroes
import com.iiamir.routes.root
import com.iiamir.routes.searchHeroes
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        searchHeroes()
        static("/images") {
            resources("images")
        }
    }
}
