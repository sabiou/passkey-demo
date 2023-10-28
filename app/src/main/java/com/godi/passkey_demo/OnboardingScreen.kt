package com.godi.passkey_demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen() {
    Scaffold(
        Modifier.fillMaxSize()
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 600.dp)
                    .fillMaxWidth(),
            ) {
                CompositionLocalProvider(LocalContentColor provides Color.White) {
                    Text(
                        text = "Passkey demo",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(48.dp))
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Create account", modifier = Modifier.padding(vertical = 8.dp))
                    }
                    Spacer(Modifier.height(12.dp))
                    TextButton(
                        onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            "Log in",
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun OnboardingScreenPreview() {
    OnboardingScreen()
}