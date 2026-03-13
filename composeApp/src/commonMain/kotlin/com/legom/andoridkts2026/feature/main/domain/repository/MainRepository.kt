package com.legom.andoridkts2026.feature.main.domain.repository

import com.legom.andoridkts2026.feature.main.domain.entity.SearchResult

interface MainRepository {

    suspend fun searchCourses(query: String, page: Int): Result<SearchResult>

}