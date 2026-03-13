package com.legom.andoridkts2026.common.remote.modelsDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponseDto(
    @SerialName("meta")
    val meta: MetaDto = MetaDto(),
    @SerialName("courses")
    val courses: List<CourseDto> = listOf()
)
