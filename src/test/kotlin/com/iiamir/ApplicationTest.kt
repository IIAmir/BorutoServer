package com.iiamir

import com.iiamir.models.ApiResponse
import com.iiamir.repository.HeroRepository
import com.iiamir.repository.HeroRepositoryImpl
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `access root endpoint, assert correct information`() {
        testApplication {
            val response = client.get("/")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            assertEquals(
                expected = "Welcome to Boruto API!",
                actual = response.bodyAsText()
            )
        }
    }

    @Test
    fun `access all heroes endpoint, query all pages, assert correct information`() {
        testApplication {
            val heroRepository: HeroRepository = HeroRepositoryImpl()
            val pages = 1..5
            val heroes = listOf(
                heroRepository.page1,
                heroRepository.page2,
                heroRepository.page3,
                heroRepository.page4,
                heroRepository.page5,
            )
            pages.forEach { page ->
                val response = client.get("/boruto/heroes?page=$page")
                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = response.status
                )
                val expected = ApiResponse(
                    success = true,
                    message = "OK",
                    prevPage = if (page == 1) null else page - 1,
                    nextPage = if (page == 5) null else page + 1,
                    heroes = heroes[page - 1]
                )

                val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

                assertEquals(
                    expected = expected,
                    actual = actual
                )
            }
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun `access all heroes endpoint, query non existing page number, assert error`() =
        testApplication {
            val response = client.get("/boruto/heroes?page=6")
            assertEquals(
                expected = HttpStatusCode.NotFound,
                actual = response.status
            )
            assertEquals(
                expected = "Page not Found.",
                actual = response.bodyAsText()
            )
        }

    @Test
    fun `access all heroes endpoint, query invalid page number, assert error`() {
        testApplication {
            val response = client.get("/boruto/heroes?page=invalid")
            assertEquals(
                expected = HttpStatusCode.BadRequest,
                actual = response.status
            )
            val expected = ApiResponse(
                success = false,
                message = "Only Numbers Allowed."
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
            assertEquals(
                expected = expected,
                actual = actual
            )
        }
    }

    @Test
    fun `access search heroes endpoint,query hero name, assert single hero result`() {
        testApplication {
            val response = client.get("/boruto/heroes/search?name=sas")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
                .heroes.size
            assertEquals(
                expected = 1,
                actual = actual
            )
        }
    }

    @Test
    fun `access search heroes endpoint,query hero name, assert multiple hero result`() {
        testApplication {
            val response = client.get("/boruto/heroes/search?name=sa")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
                .heroes.size
            assertEquals(
                expected = 3,
                actual = actual
            )
        }
    }

    @Test
    fun `access search heroes endpoint,query an empty test, assert empty list as a result`() {
        testApplication {
            val response = client.get("/boruto/heroes/search?name=")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
                .heroes
            assertEquals(
                expected = emptyList(),
                actual = actual
            )
        }
    }

    @Test
    fun `access search heroes endpoint,query non existing hero, assert empty list as a result`() {
        testApplication {
            val response = client.get("/boruto/heroes/search?name=unknown")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
                .heroes
            assertEquals(
                expected = emptyList(),
                actual = actual
            )
        }
    }

    @Test
    fun `access non existing endpoint, assert not found`() {
        testApplication {
            val response = client.get("/unknown")
            assertEquals(
                expected = HttpStatusCode.NotFound,
                actual = response.status
            )
            assertEquals(
                expected = "Page not Found.",
                actual = response.bodyAsText()
            )
        }
    }
}