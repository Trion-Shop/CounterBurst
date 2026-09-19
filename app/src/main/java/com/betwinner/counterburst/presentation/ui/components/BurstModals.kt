package com.betwinner.counterburst.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.betwinner.counterburst.core.theme.*
import com.betwinner.counterburst.domain.model.*

@Composable
fun VectorDetailModal(
    vector: BreakoutVector,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = BurstPitchDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGreenLime)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = vector.vectorName.uppercase(),
                    color = BurstGold,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = vector.vectorArchetype,
                    color = BurstGreenLime,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BurstMetricBadge(
                        label = "Sprint Speed",
                        value = "${vector.transitionSpeedMps} m/s",
                        modifier = Modifier.weight(1f)
                    )
                    BurstMetricBadge(
                        label = "Release Delay",
                        value = "${vector.passReleaseLatencySec}s",
                        modifier = Modifier.weight(1f)
                    )
                    BurstMetricBadge(
                        label = "Direct Goal %",
                        value = "${vector.directGoalProbabilityPct.toInt()}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Mechanism",
                    color = BurstWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = vector.tacticalDescription,
                    color = BurstSoftSilver,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Triggers",
                    color = BurstGoldBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                vector.triggerKeys.forEach { trigger ->
                    Text(
                        text = "• $trigger",
                        color = BurstMutedGreen,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Breakdown Risks",
                    color = BurstGoldBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                vector.vulnerabilityFactors.forEach { risk ->
                    Text(
                        text = "• $risk",
                        color = BurstSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BurstGreenDeep),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = BurstGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun DrillDetailModal(
    drill: TransitionDrill,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = BurstPitchDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = drill.drillTitle.uppercase(),
                    color = BurstGoldBright,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = drill.scenarioType,
                    color = BurstMutedGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BurstMetricBadge(
                        label = "Pitch Zone",
                        value = "${drill.fieldZoneLengthMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    BurstMetricBadge(
                        label = "Execution Time",
                        value = "${drill.targetExecutionTimeSec}s",
                        modifier = Modifier.weight(1f)
                    )
                    BurstMetricBadge(
                        label = "Attack Wave",
                        value = "${drill.runnersCount} players",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Constraints & Rules",
                    color = BurstWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.tacticalRules.forEach { rule ->
                    Text(
                        text = "• $rule",
                        color = BurstSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Coaching Cues",
                    color = BurstGreenLime,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.coachingCues.forEachIndexed { idx, cue ->
                    Text(
                        text = "${idx + 1}. $cue",
                        color = BurstMutedGreen,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BurstGreenDeep),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = BurstGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ClashDetailModal(
    clash: CounterClashMatch,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = BurstPitchDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(BurstGreenBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = clash.headline.uppercase(),
                        color = BurstGold,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = clash.score,
                        color = BurstWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${clash.teamA} vs ${clash.teamB}",
                    color = BurstGreenLime,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BurstMetricBadge(
                        label = "Avg Transition",
                        value = "${clash.avgTransitionTimeSec}s",
                        modifier = Modifier.weight(1f)
                    )
                    BurstMetricBadge(
                        label = "Counter Goals",
                        value = "${clash.counterGoalsScored}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Breakdown",
                    color = BurstWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clash.tacticalBreakdown,
                    color = BurstSoftSilver,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Decisive Counter Moments",
                    color = BurstGoldBright,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                clash.breakoutMoments.forEach { moment ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = BurstGreenCard),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${moment.minute}' | ${moment.sprinter}",
                                    color = BurstGold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "FAST BREAK",
                                    color = BurstGreenLime,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Trigger: ${moment.triggerZone} (Won by ${moment.ballWinner})",
                                color = BurstWhite,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Outcome: ${moment.goalOutcome}",
                                color = BurstMutedGreen,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BurstGreenDeep),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = BurstGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
