package com.betwinner.counterburst.presentation.ui.tabs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.counterburst.core.theme.*
import com.betwinner.counterburst.presentation.ui.components.BurstMetricBadge
import com.betwinner.counterburst.presentation.viewmodel.BurstUiState

@Composable
fun BurstSimulatorTab(
    uiState: BurstUiState,
    onUpdateSim: (Float, Float, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val sim = uiState.simulationResult

    val infiniteTransition = rememberInfiniteTransition(label = "burst")
    val sprintProgress by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sprintProgress"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "VERTICAL TRANSITION ACCELEROMETER",
            color = BurstGold,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
        )
        Text(
            text = "Model sprint velocity vs defender recovery vectors and shot conversion probabilities.",
            color = BurstMutedGreen,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Visual Canvas Pitch Simulation
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = BurstPitchDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGreenBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Pitch outlines
                    drawRect(
                        color = BurstGreenDeep.copy(alpha = 0.4f),
                        topLeft = Offset(16.dp.toPx(), 16.dp.toPx()),
                        size = androidx.compose.ui.geometry.Size(w - 32.dp.toPx(), h - 32.dp.toPx()),
                        style = Stroke(width = 1.5.dp.toPx())
                    )

                    // Penalty box target area on right
                    val boxLeft = w - 80.dp.toPx()
                    drawRect(
                        color = BurstGreenMid.copy(alpha = 0.6f),
                        topLeft = Offset(boxLeft, h * 0.25f),
                        size = androidx.compose.ui.geometry.Size(64.dp.toPx(), h * 0.5f),
                        style = Stroke(width = 1.dp.toPx())
                    )

                    // Sprint Corridor trajectory
                    val startX = 30.dp.toPx()
                    val endX = w - 50.dp.toPx()
                    val runnerY = h * 0.5f

                    // Trajectory dash line
                    drawLine(
                        color = BurstGoldAmber.copy(alpha = 0.4f),
                        start = Offset(startX, runnerY),
                        end = Offset(endX, runnerY),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )

                    // Current Sprinter Position based on animation
                    val currentRunnerX = startX + sprintProgress * (endX - startX)

                    // Velocity flame behind runner
                    drawLine(
                        color = BurstGoldBright.copy(alpha = 0.8f),
                        start = Offset(currentRunnerX - 25.dp.toPx(), runnerY),
                        end = Offset(currentRunnerX, runnerY),
                        strokeWidth = 3.dp.toPx()
                    )

                    // Draw Sprinter (Attacker)
                    drawCircle(
                        color = BurstGold,
                        center = Offset(currentRunnerX, runnerY),
                        radius = 12.dp.toPx()
                    )
                    drawCircle(
                        color = BurstPitchDark,
                        center = Offset(currentRunnerX, runnerY),
                        radius = 6.dp.toPx()
                    )

                    // Draw Recovering Defenders chasing from angles
                    val defCount = uiState.simDefendersCount
                    for (i in 0 until defCount) {
                        val defOffsetY = if (i % 2 == 0) -45.dp.toPx() else 45.dp.toPx()
                        val defLag = (i + 1) * 35.dp.toPx()
                        val defX = (currentRunnerX - defLag).coerceAtLeast(startX)
                        val defY = runnerY + defOffsetY

                        // Vector line from defender toward future runner position
                        drawLine(
                            color = BurstRedAlert.copy(alpha = 0.6f),
                            start = Offset(defX, defY),
                            end = Offset(currentRunnerX + 20.dp.toPx(), runnerY),
                            strokeWidth = 1.5.dp.toPx()
                        )

                        drawCircle(
                            color = BurstRedAlert,
                            center = Offset(defX, defY),
                            radius = 9.dp.toPx()
                        )
                    }

                    // Goal Target circle
                    drawCircle(
                        color = BurstGreenLime,
                        center = Offset(w - 24.dp.toPx(), runnerY),
                        radius = 8.dp.toPx()
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "● Fast-Breaker (Gold)",
                        color = BurstGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "● Chasing Defender (Red)",
                        color = BurstRedAlert,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "● Goal Target (Green)",
                        color = BurstGreenLime,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sliders & Controls
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = BurstGreenCard),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGreenBorder)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Attacker Top Sprint Speed",
                        color = BurstWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("%.1f km/h", uiState.simSpeedKmh),
                        color = BurstGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simSpeedKmh,
                    onValueChange = { onUpdateSim(it, uiState.simDistanceMeters, uiState.simDefendersCount) },
                    valueRange = 26.0f..37.5f,
                    colors = SliderDefaults.colors(
                        thumbColor = BurstGold,
                        activeTrackColor = BurstGold,
                        inactiveTrackColor = BurstGreenDeep
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Breakout Distance (Meters)",
                        color = BurstWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${uiState.simDistanceMeters.toInt()} m",
                        color = BurstGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simDistanceMeters,
                    onValueChange = { onUpdateSim(uiState.simSpeedKmh, it, uiState.simDefendersCount) },
                    valueRange = 30f..80f,
                    colors = SliderDefaults.colors(
                        thumbColor = BurstGreenLime,
                        activeTrackColor = BurstGreenLime,
                        inactiveTrackColor = BurstGreenDeep
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chasing Recovery Defenders",
                        color = BurstWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        (1..4).forEach { count ->
                            val isSel = uiState.simDefendersCount == count
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(if (isSel) BurstGold else BurstGreenDeep)
                                    .border(1.dp, BurstGreenBorder, CircleShape)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "$count",
                                    color = if (isSel) BurstPitchDark else BurstWhite,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Simulation Output
        if (sim != null) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BurstGreenDeep),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGold)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "BURST CONVERSION METRICS",
                        color = BurstGoldBright,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BurstMetricBadge(
                            label = "Time to Box",
                            value = "${sim.timeToBoxArrivalSec}s",
                            modifier = Modifier.weight(1f)
                        )
                        BurstMetricBadge(
                            label = "Recovery Risk",
                            value = "${sim.defenderInterceptionRiskPct}%",
                            modifier = Modifier.weight(1f)
                        )
                        BurstMetricBadge(
                            label = "Goal Prob",
                            value = "${sim.shotConversionExpectedPct}%",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = sim.tacticalAnalysis,
                        color = BurstSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
