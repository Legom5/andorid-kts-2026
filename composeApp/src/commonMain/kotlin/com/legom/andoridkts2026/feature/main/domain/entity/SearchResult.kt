package com.legom.andoridkts2026.feature.main.domain.entity

data class SearchResult(
    val courses: List<Course>,
    val hasNextPage: Boolean,
    val currentPage: Int
)
