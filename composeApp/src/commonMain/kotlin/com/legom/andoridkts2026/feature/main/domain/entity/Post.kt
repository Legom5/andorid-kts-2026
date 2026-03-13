package com.legom.andoridkts2026.feature.main.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val author: String,
    val score: Int,
    val commentCount: Int,
    val thumbnailUrl: String,
    val createdAt: Long,
    val subreddit: String
)