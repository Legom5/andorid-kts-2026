package com.legom.andoridkts2026.feature.main.domain.usecases

import com.legom.andoridkts2026.feature.main.domain.entity.Post
import com.legom.andoridkts2026.feature.main.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetAllPostUseCase(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<List<Post>>{
        return repository.getList()
    }
}