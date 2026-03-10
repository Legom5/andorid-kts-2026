package com.legom.andoridkts2026.common.remote.modelsDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("cover")
    val imageUrl: String = ""
)
