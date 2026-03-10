package com.legom.andoridkts2026.common.remote.modelsDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("has_next")
    val hasNext: Boolean = false,
    @SerialName("has_previous")
    val hasPrevious: Boolean = false
)
