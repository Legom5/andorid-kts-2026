package com.legom.andoridkts2026.common.remote

import com.legom.andoridkts2026.common.remote.modelsDto.CoursesResponseDto
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class StepikApi(private val httpClient: HttpClient) {

    suspend fun searchUsers(query: String, page: Int = 1): Result<CoursesResponseDto>{
        return try {

            val response: CoursesResponseDto = httpClient.get("courses") {
                parameter("search", query)
                parameter("page", page)

            }.body()

            Result.success(response)
        }catch (e: Exception){
            Napier.e("Ошибка при поиске курсов", e)
            Result.failure(e)
        }
    }
}