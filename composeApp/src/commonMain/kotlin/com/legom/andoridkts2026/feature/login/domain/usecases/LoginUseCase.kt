package com.legom.andoridkts2026.feature.login.domain.usecases

import com.legom.andoridkts2026.feature.login.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(username: String, password: String): Result<Any>{
        return repository.login(username, password)
    }
}