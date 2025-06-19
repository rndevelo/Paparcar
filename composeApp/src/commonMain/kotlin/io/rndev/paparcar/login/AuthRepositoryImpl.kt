package io.rndev.paparcar.login

import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(val auth: FirebaseAuth) : AuthRepository {

    override val isAuthenticated = auth.authStateChanged.map { it != null }

    override suspend fun signIn(email: String, password: String): Boolean {
        val authResult = auth.signInWithEmailAndPassword(email = email, password = password)
        return authResult.user != null
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        val authResult = auth.createUserWithEmailAndPassword(email = email, password = password)
        return authResult.user != null
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}