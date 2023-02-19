package com.iiamir.routes

import com.iiamir.core.Constants.HERO_NOT_FOUND_ERROR
import com.iiamir.core.Constants.NUMBER_FORMAT_EXCEPTION
import com.iiamir.core.Constants.PAGE_QUERY
import com.iiamir.models.ApiResponse
import com.iiamir.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun Route.getAllHeroes() {
    val heroRepository: HeroRepository by inject()

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters[PAGE_QUERY]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page = page)

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