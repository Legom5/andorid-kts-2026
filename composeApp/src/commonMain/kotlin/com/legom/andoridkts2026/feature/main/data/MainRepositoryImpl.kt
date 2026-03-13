package com.legom.andoridkts2026.feature.main.data

import com.legom.andoridkts2026.feature.main.domain.entity.Post
import com.legom.andoridkts2026.feature.main.domain.repository.MainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Clock

class MainRepositoryImpl : MainRepository {

    override fun getList(): Flow<List<Post>> = flow {
        delay(500)
        emit(generateMockPosts())
    }

    private fun generateMockPosts(): List<Post> {
        val subreddits = listOf("AskReddit", "funny", "gaming", "worldnews", "pics", "todayilearned")
        val authors = listOf("u/reddit_user", "u/kotlin_fan", "u/android_dev", "u/compose_master", "u/kmp_lover")

        return List(30) { index ->
            Post(
                id = index,
                title = when (index % 5) {
                    0 -> "What's your unpopular opinion about Reddit?"
                    1 -> "Kotlin Multiplatform is amazing! Here's why..."
                    2 -> "I made a Reddit client with Compose Multiplatform"
                    3 -> "Daily discussion thread: What are you working on?"
                    4 -> "Tip: Use LazyColumn with keys for better performance"
                    else -> "Post number $index"
                },
                author = authors[index % authors.size],
                score = (100..5000).random() * (index + 1),
                commentCount = (10..500).random(),
                thumbnailUrl = "https://mimimi.ru/content/${index+10}.jpg",
                createdAt = Clock.System.now().epochSeconds - (index * 3600000),
                subreddit = "r/${subreddits[index % subreddits.size]}"
            )
        }
    }
}