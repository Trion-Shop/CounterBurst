package com.betwinner.counterburst.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// BETWINNER BRAND PALETTE (Counter-attack explosive green theme)
val BurstPitchDark = Color(0xFF0F261B)
val BurstGreenDeep = Color(0xFF1B4D36)
val BurstGreenMid = Color(0xFF266649)
val BurstGreenCard = Color(0xFF1E523A)
val BurstGreenBorder = Color(0xFF388B63)
val BurstGreenLime = Color(0xFF3DF08D)

val BurstGold = Color(0xFFF6CB45)
val BurstGoldBright = Color(0xFFFFDE6A)
val BurstGoldAmber = Color(0xFFDFAC1E)

val BurstWhite = Color(0xFFFFFFFF)
val BurstSoftSilver = Color(0xFFE2EBE6)
val BurstMutedGreen = Color(0xFFB0D5C3)
val BurstRedAlert = Color(0xFFFF5252)

val BurstBgGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF143B29),
        BurstGreenDeep,
        BurstPitchDark
    )
)
