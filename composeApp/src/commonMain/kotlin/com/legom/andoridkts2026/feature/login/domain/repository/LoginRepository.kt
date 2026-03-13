package com.legom.andoridkts2026.feature.login.domain.repository

interface LoginRepository {

    suspend fun login(username: String, password: String): Result<Any>
}