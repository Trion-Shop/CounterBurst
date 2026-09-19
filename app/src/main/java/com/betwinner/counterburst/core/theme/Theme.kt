package com.betwinner.counterburst.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BurstGold,
    onPrimary = BurstPitchDark,
    secondary = BurstGreenLime,
    onSecondary = BurstPitchDark,
    tertiary = BurstGoldBright,
    background = BurstPitchDark,
    surface = BurstGreenCard,
    onSurface = BurstWhite
)

@Composable
fun CounterBurstTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
