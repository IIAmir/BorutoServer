package com.iiamir.repository

import com.iiamir.core.Constants.DEFAULT_PAGE
import com.iiamir.models.ApiResponse
import com.iiamir.models.Hero

interface HeroRepository {

    val heroes: Map<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun getAllHeroes(page: Int = DEFAULT_PAGE): ApiResponse

    suspend fun searchHeroes(name: String?): ApiResponse

}