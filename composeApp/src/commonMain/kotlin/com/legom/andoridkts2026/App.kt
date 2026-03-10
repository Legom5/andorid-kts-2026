package com.legom.andoridkts2026

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.legom.andoridkts2026.common.NavGraph
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
@Preview
fun App() {

    LaunchedEffect(Unit){
        Napier.base(DebugAntilog())
    }

    MaterialTheme {
        NavGraph()
    }
}