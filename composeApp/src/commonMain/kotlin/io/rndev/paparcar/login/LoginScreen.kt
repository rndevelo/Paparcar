package io.rndev.paparcar.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import paparcar.composeapp.generated.resources.Res
import paparcar.composeapp.generated.resources.ic_google
import paparcar.composeapp.generated.resources.ic_paparcar_black
import paparcar.composeapp.generated.resources.login_text_email
import paparcar.composeapp.generated.resources.login_text_hide_password
import paparcar.composeapp.generated.resources.login_text_password
import paparcar.composeapp.generated.resources.login_text_show_password
import paparcar.composeapp.generated.resources.login_text_sign_in
import paparcar.composeapp.generated.resources.login_text_sign_in_with_email
import paparcar.composeapp.generated.resources.login_text_sign_in_with_google
import paparcar.composeapp.generated.resources.login_text_signing_in

@Preview
@Composable
fun LoginScreen(
    vm: LoginViewModel = viewModel(),
    onLoginClick: (String, String) -> Unit,
    onGoogleSignInClick: () -> Unit
) {

    val state = vm.state
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isShowEmailForm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .animateContentSize()
            .verticalScroll(rememberScrollState()) // 👈 permite hacer scroll si el teclado tapa
            .imePadding()                          // 👈 añade padding automático cuando aparece el teclado
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(Res.drawable.ic_paparcar_black),
            contentDescription = Res.drawable.ic_paparcar_black.toString(),
            modifier = Modifier.size(90.dp)
        )

        Spacer(Modifier.height(12.dp))

        // Título
        Text(
            text = "Bienvenido de nuevo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(24.dp))

        AnimatedVisibility(visible = !isShowEmailForm) {
            Column {
                // Botón Email
                OutlinedButton(
                    onClick = { isShowEmailForm = !isShowEmailForm },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                ) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = stringResource(Res.string.login_text_sign_in),
                        tint = Color.Gray
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = stringResource(Res.string.login_text_sign_in_with_email))
                }

                Spacer(Modifier.height(12.dp))

                // Botón Google
                OutlinedButton(
                    onClick = onGoogleSignInClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_google),
                        contentDescription = Res.drawable.ic_google.toString(),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (false) stringResource(Res.string.login_text_signing_in)
                        else stringResource(Res.string.login_text_sign_in_with_google)
                    )
                }
            }
        }

        // Formulario de Email
        AnimatedVisibility(visible = isShowEmailForm) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { isShowEmailForm = false }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text("Inicia sesión con Email", style = MaterialTheme.typography.titleLarge)
                }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(Res.string.login_text_email)) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    isError = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    onDone = { onLoginClick(email.trim(), password.trim()) },
                    isError = state.error != null,
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { onLoginClick(email.trim(), password.trim()) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Text(stringResource(Res.string.login_text_sign_in))
                }
            }
        }
    }
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    isError: Boolean = false,
) {

    var isPasswordVisible by remember { mutableStateOf(false) } // 👈 estado para visibilidad

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(Res.string.login_text_password)) },
        leadingIcon = {
            Icon(
                Icons.Default.Lock,
                contentDescription = Icons.Default.Lock.toString()
            )
        },
        trailingIcon = {
            val icon =
                if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val description =
                if (isPasswordVisible) stringResource(Res.string.login_text_hide_password)
                else stringResource(Res.string.login_text_show_password)
            Icon(
                imageVector = icon,
                contentDescription = description,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { isPasswordVisible = !isPasswordVisible }
            )
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions { onDone },
        modifier = Modifier.fillMaxWidth()

    )
}