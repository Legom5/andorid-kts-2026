package com.legom.andoridkts2026.common


import kotlin.time.Clock

fun formatKNumber(number: Int): String {
    return when {
        number >= 1000 -> "${number / 1000}k"
        else -> number.toString()
    }
}

fun formatTimeAgo(timestamp: Long): String {
    val diff = Clock.System.now().epochSeconds - timestamp
    val hours = diff / (1000 * 60 * 60)

    return when {
        hours < 1 -> "now"
        hours < 24 -> "${hours}h"
        else -> "${hours / 24}d"

    }
}