package io.rndev.paparcar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.rndev.paparcar.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = { email, password ->
                // Handle login logic here
            },
            onGoogleSignInClick = {}
        )
    }
}
