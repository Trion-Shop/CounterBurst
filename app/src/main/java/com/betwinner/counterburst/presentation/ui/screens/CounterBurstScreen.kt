package com.betwinner.counterburst.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.counterburst.core.theme.*
import com.betwinner.counterburst.presentation.ui.components.*
import com.betwinner.counterburst.presentation.ui.tabs.*
import com.betwinner.counterburst.presentation.viewmodel.BurstViewModel

@Composable
fun CounterBurstScreen(
    viewModel: BurstViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val tabTitles = listOf("Breakouts", "Speed Sim", "Vectors", "Drills")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BurstBgGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            BurstHeader(
                title = "Counter Burst",
                subtitle = "Vertical Breakout Velocity & Fast-Break Engine"
            )

            // Top Segmented Lightning Header Navigation with status bar safety
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                color = BurstPitchDark,
                border = androidx.compose.foundation.BorderStroke(1.dp, BurstGreenBorder.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        val isSelected = uiState.selectedTab == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) BurstGreenMid else Color.Transparent)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { viewModel.onSelectTab(index) }
                                .padding(vertical = 9.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                color = if (isSelected) BurstGold else BurstMutedGreen.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedTab) {
                    0 -> CounterClashesTab(
                        uiState = uiState,
                        onSelectClash = { viewModel.onSelectClash(it) },
                        onSelectVector = { viewModel.onSelectVector(it) }
                    )
                    1 -> BurstSimulatorTab(
                        uiState = uiState,
                        onUpdateSim = { speed, dist, defs ->
                            viewModel.updateSimulation(speed, dist, defs)
                        }
                    )
                    2 -> BreakoutVectorsTab(
                        uiState = uiState,
                        onSelectVector = { viewModel.onSelectVector(it) }
                    )
                    3 -> TransitionDrillsTab(
                        uiState = uiState,
                        onSelectDrill = { viewModel.onSelectDrill(it) }
                    )
                }
            }
        }

        // Active Deep-Dive Modals
        uiState.activeVectorModal?.let { vector ->
            VectorDetailModal(
                vector = vector,
                onDismiss = { viewModel.dismissModal() }
            )
        }

        uiState.activeDrillModal?.let { drill ->
            DrillDetailModal(
                drill = drill,
                onDismiss = { viewModel.dismissModal() }
            )
        }

        uiState.activeClashModal?.let { clash ->
            ClashDetailModal(
                clash = clash,
                onDismiss = { viewModel.dismissModal() }
            )
        }
    }
}
