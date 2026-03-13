package com.legom.andoridkts2026.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legom.andoridkts2026.feature.main.data.MainRepositoryImpl
import com.legom.andoridkts2026.feature.main.domain.entity.Post
import com.legom.andoridkts2026.feature.main.domain.usecases.GetAllPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepositoryImpl()
    private val getAllPostUseCase = GetAllPostUseCase(repository)
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts(){
        viewModelScope.launch {
            getAllPostUseCase().collect { posts ->
                _state.update {
                    it.copy(
                        posts = posts
                    )
                }
            }
        }
    }
}

data class MainScreenState(
    val posts: List<Post> = listOf()
)