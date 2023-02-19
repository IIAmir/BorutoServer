package com.iiamir.plugins

import com.iiamir.core.Constants.RESOURCE_PACKAGE_ADDRESS
import com.iiamir.core.Constants.RESOURCE_PACKAGE_NAME
import com.iiamir.routes.getAllHeroesAlternative
import com.iiamir.routes.root
import com.iiamir.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
//        getAllHeroes()
        getAllHeroesAlternative()
        searchHeroes()
        static(RESOURCE_PACKAGE_ADDRESS) {
            resources(RESOURCE_PACKAGE_NAME)
        }
    }
}
