package com.legom.andoridkts2026

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform