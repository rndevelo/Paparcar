package io.rndev.paparcar.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(UiState())

    data class UiState(
        val loggedIn: Boolean = false,
        val error: String? = null,
    )

    fun onLoginClick(user: String, password: String) {
        state = when {
            !user.contains("@") -> state.copy(error = "Invalid email")
            password.length < 5 -> state.copy(error = "Invalid password")
            else -> state.copy(loggedIn = true)
        }
    }
}