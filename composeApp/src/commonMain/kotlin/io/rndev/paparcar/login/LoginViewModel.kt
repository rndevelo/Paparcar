package io.rndev.paparcar.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var state by mutableStateOf(UiState())

    val isAuthenticated: StateFlow<Boolean> = authRepository.isAuthenticated
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    data class UiState(
        val loggedIn: Boolean = false,
        val error: String? = null,
    )

    fun onSignIn(user: String, password: String) = viewModelScope.launch {
        authRepository.signIn(user, password)
    }

    fun onSignUp(user: String, password: String) = viewModelScope.launch {
        authRepository.signUp(user, password)
    }

//    fun onGoogleSignIn() = viewModelScope.launch {
//        authRepository.signInWithGoogle()
//    }


//    Fixme: This shouldn't be here
    fun onSignOut() = viewModelScope.launch {
        authRepository.signOut()
    }
}