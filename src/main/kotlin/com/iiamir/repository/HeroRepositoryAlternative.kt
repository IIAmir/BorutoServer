package com.iiamir.repository

import com.iiamir.core.Constants.DEFAULT_LIMIT
import com.iiamir.core.Constants.DEFAULT_PAGE
import com.iiamir.models.ApiResponse
import com.iiamir.models.Hero

interface HeroRepositoryAlternative {

    val heroes: List<Hero>

    suspend fun getAllHeroes(page: Int = DEFAULT_PAGE, limit: Int = DEFAULT_LIMIT): ApiResponse

    suspend fun searchHeroes(name: String?): ApiResponse

}