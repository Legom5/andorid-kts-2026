package com.legom.andoridkts2026.feature.main.domain.usecases

import com.legom.andoridkts2026.feature.main.domain.entity.SearchResult
import com.legom.andoridkts2026.feature.main.domain.repository.MainRepository

class SearchCoursesUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(query: String, page: Int = 1): Result<SearchResult> {
        return repository.searchCourses(query, page)
    }

}