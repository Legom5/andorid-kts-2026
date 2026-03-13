package com.legom.andoridkts2026.feature.main.data

import com.legom.andoridkts2026.common.remote.StepikApi
import com.legom.andoridkts2026.common.remote.StepikApiService
import com.legom.andoridkts2026.common.remote.modelsDto.CourseDto
import com.legom.andoridkts2026.feature.main.domain.entity.Course
import com.legom.andoridkts2026.feature.main.domain.entity.SearchResult
import com.legom.andoridkts2026.feature.main.domain.repository.MainRepository

class MainRepositoryImpl : MainRepository {

    private val api = StepikApi(StepikApiService.httpClient)

    override suspend fun searchCourses(
        query: String,
        page: Int
    ): Result<SearchResult> {
        val result = api.searchUsers(query, page)
        result.fold(
            onSuccess = { response ->
                val courses = response.courses.map { searchCourses ->
                    searchCourses.toCourse()
                }
                return Result.success(
                    SearchResult(
                        courses = courses,
                        hasNextPage = response.meta.hasNext,
                        currentPage = response.meta.page
                    )
                )
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }


    private fun CourseDto.toCourse(): Course = Course(
        id = id,
        title = title,
        slug = slug,
        imageUrl = imageUrl
    )
}