package io.rndev.paparcar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import io.rndev.paparcar.login.AuthRepositoryImpl
import io.rndev.paparcar.login.LoginScreen
import io.rndev.paparcar.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PpcTheme {

        val loginViewModel = LoginViewModel(
            authRepository = AuthRepositoryImpl(auth = Firebase.auth),
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            LoginScreen(loginViewModel)
        }
    }
}
