package com.legom.andoridkts2026.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legom.andoridkts2026.feature.main.data.MainRepositoryImpl
import com.legom.andoridkts2026.feature.main.domain.entity.Course
import com.legom.andoridkts2026.feature.main.domain.entity.SearchResult
import com.legom.andoridkts2026.feature.main.domain.usecases.SearchCoursesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.emptyList
import kotlin.collections.listOf

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MainViewModel : ViewModel() {

    private val repository = MainRepositoryImpl()
    private val searchCoursesUseCase = SearchCoursesUseCase(repository)

    private val query = MutableStateFlow("")
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        query
            .onEach { input ->
                _state.update {
                    it.copy(
                        query = input,
                        courses = emptyList(),
                        currentPage = 1,
                        hasNextPage = false,
                        isLoading = input.isNotBlank()
                    )
                }
            }
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { input ->
                flow {
                    val result = searchCoursesUseCase(input, page = 1)
                    emit(result)
//                    }
                }
            }
            .onEach { result ->
                searchResult(result as Result<SearchResult>, isLoadMore = false)
            }
            .launchIn(viewModelScope)
    }

    fun processCommand(command: MainCommand) {
        when (command) {
            is MainCommand.InputSearchQuery -> {
                query.update {
                    command.query.trim()
                }
            }

            MainCommand.LoadNextPage -> {
                loadNextPage()
            }

            MainCommand.RetrySearch -> {
                query.update {
                    _state.value.query
                }
            }
        }
    }

    private fun searchResult(
        result: Result<SearchResult>,
        isLoadMore: Boolean
    ) {
        result.fold(
            onSuccess = { searchResult ->
                _state.update { currentState ->
                    val updatedCourses = if (isLoadMore) {
                        currentState.courses + searchResult.courses
                    } else {
                        searchResult.courses
                    }

                    currentState.copy(
                        courses = updatedCourses,
                        isLoading = false,
                        isLoadingMore = false,
                        currentPage = searchResult.currentPage,
                        hasNextPage = searchResult.hasNextPage,
                        error = null
                    )
                }
            },
            onFailure = { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        error = error.message ?: "Unknown error"
                    )
                }
            }
        )
    }

    private fun loadNextPage() {
        val currentState = _state.value

        if (currentState.query.isBlank() ||
            !currentState.hasNextPage ||
            currentState.isLoadingMore ||
            currentState.isLoading
        ) {
            return
        }


        viewModelScope.launch {
            _state.update { it.copy(isLoadingMore = true) }

            val nextPage = currentState.currentPage + 1
            val result = searchCoursesUseCase(currentState.query, nextPage)

            searchResult(result, isLoadMore = true)
        }
    }


}


sealed interface MainCommand {

    data class InputSearchQuery(val query: String) : MainCommand

    object LoadNextPage : MainCommand

    data object RetrySearch : MainCommand
}

data class MainScreenState(
    val query: String = "",
    val courses: List<Course> = listOf(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = false,
    val error: String? = null
)