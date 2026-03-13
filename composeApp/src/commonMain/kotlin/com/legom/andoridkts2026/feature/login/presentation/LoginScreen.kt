package com.legom.andoridkts2026.feature.login.presentation

import andoridkts2026.composeapp.generated.resources.Res
import andoridkts2026.composeapp.generated.resources.email
import andoridkts2026.composeapp.generated.resources.enter_email
import andoridkts2026.composeapp.generated.resources.enter_password
import andoridkts2026.composeapp.generated.resources.log_in
import andoridkts2026.composeapp.generated.resources.login_error
import andoridkts2026.composeapp.generated.resources.password
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit
){

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.events){
        viewModel.events.collect { event ->
            when(event){
                LoginUiEvent.LoginSuccessEvent -> {
                    onLoginSuccess()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        OutlinedTextField(
            value = state.username,
            onValueChange = {
                viewModel.processCommand(LoginCommand.InputUsername(it))
            },
            label = { Text(text = stringResource(Res.string.email)) },
            placeholder = { Text(text = stringResource(Res.string.enter_email))  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )


        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.processCommand(LoginCommand.InputPassword(it))
            },
            label = { Text(text = stringResource(Res.string.password)) },
            placeholder = { Text(text = stringResource(Res.string.enter_password)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )


        Button(
            onClick = {
                viewModel.processCommand(LoginCommand.LoginClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = state.isLoginButtonActive
        ) {
            Text(text = stringResource(Res.string.log_in))
        }

        if (state.error){
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.login_error),
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}