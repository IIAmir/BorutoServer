package com.iiamir.routes

import com.iiamir.core.Constants.HERO_NOT_FOUND_ERROR
import com.iiamir.core.Constants.LIMIT_QUERY
import com.iiamir.core.Constants.NUMBER_FORMAT_EXCEPTION
import com.iiamir.core.Constants.PAGE_QUERY
import com.iiamir.models.ApiResponse
import com.iiamir.repository.HeroRepositoryAlternative
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroesAlternative() {
    val heroRepositoryAlternative: HeroRepositoryAlternative by inject()

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters[PAGE_QUERY]?.toInt() ?: 1
            val limit = call.request.queryParameters[LIMIT_QUERY]?.toInt() ?: 4

            val apiResponse = heroRepositoryAlternative.getAllHeroes(
                page = page, limit = limit
            )

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = NUMBER_FORMAT_EXCEPTION),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = HERO_NOT_FOUND_ERROR),
                status = HttpStatusCode.NotFound
            )
        }
    }
}