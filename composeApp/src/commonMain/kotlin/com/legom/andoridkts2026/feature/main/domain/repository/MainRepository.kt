package com.legom.andoridkts2026.feature.main.domain.repository

import com.legom.andoridkts2026.feature.main.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getList(): Flow<List<Post>>

}