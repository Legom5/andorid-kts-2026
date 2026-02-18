package com.legom.andoridkts2026

import andoridkts2026.composeapp.generated.resources.Res
import andoridkts2026.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        AsyncImage(
            modifier = Modifier
                .size(400.dp)
                .padding(bottom = 8.dp),
            model = "https://img.freepik.com/premium-vector/hello-word-text-grunge-lettering-simple-handlettering-typography-calligraphy-banner-poster-web-vector-illustration_549897-1393.jpg?semt=ais_hybrid&w=740",
            contentDescription = "Hello!",
            contentScale = ContentScale.Crop,
            error = painterResource(Res.drawable.compose_multiplatform)
        )
        Text(
            text = "Добро пожаловать!",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Войдите в свой аккаунт",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onNavigateToLogin
        ){
            Text("Войти")
        }
    }

}