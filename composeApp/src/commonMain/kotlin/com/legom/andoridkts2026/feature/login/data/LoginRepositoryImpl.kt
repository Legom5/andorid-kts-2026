package com.legom.andoridkts2026.feature.login.data

import com.legom.andoridkts2026.feature.login.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<Any> {
        return try {
            if (username == "admin" && password == "123"){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Unknown User"))
            }
        } catch (e: Exception){
            Result.failure(Exception("Network error"))
        }
    }
}