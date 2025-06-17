package io.rndev.paparcar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightColorScheme = lightColorScheme(
    primary = Color.Black
)

val darkColorScheme = darkColorScheme(
    primary = Color.White
)

@Composable
fun PpcTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = if(isDarkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}