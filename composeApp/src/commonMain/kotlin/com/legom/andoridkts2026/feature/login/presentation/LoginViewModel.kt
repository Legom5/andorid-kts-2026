package com.legom.andoridkts2026.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legom.andoridkts2026.feature.login.data.LoginRepositoryImpl
import com.legom.andoridkts2026.feature.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepositoryImpl()
    private val loginUseCase = LoginUseCase(repository)

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<LoginUiEvent>()
    val events = _events.asSharedFlow()

    fun processCommand(command: LoginCommand) {
        when (command) {
            is LoginCommand.InputPassword -> {
                _state.update {
                    it.copy(
                        password = command.password
                    )
                }
            }

            is LoginCommand.InputUsername -> {
                _state.update {
                    it.copy(
                        username = command.username
                    )
                }
            }

            LoginCommand.LoginClick -> {
                handleLogin()
            }
        }
    }

    private fun handleLogin(){
        viewModelScope.launch {
            val result = loginUseCase(
                username = _state.value.username,
                password = _state.value.password
            )

            result.fold(
                onSuccess = {
                    _events.emit(LoginUiEvent.LoginSuccessEvent)
                },
                onFailure = {
                    _state.update {
                        it.copy(
                            username = "",
                            password = "",
                            error = true
                        )
                    }
                }
            )
        }

    }

}


sealed interface LoginCommand {
    data class InputUsername(val username: String) : LoginCommand

    data class InputPassword(val password: String) : LoginCommand

    data object LoginClick : LoginCommand
}

sealed interface LoginUiEvent {
    data object LoginSuccessEvent : LoginUiEvent
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val error: Boolean = false
) {
    val isLoginButtonActive: Boolean
        get() = username.isNotBlank() && password.isNotBlank()
}