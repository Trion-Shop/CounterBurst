package com.betwinner.counterburst.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

    val tabTitles = listOf("Simulator", "Vectors", "Drills", "Clashes")

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

            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = BurstPitchDark,
                contentColor = BurstGold,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                        color = BurstGold,
                        height = 3.dp
                    )
                },
                divider = {
                    HorizontalDivider(color = BurstGreenBorder.copy(alpha = 0.4f))
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.onSelectTab(index) },
                        text = {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = if (uiState.selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                                color = if (uiState.selectedTab == index) BurstGold else BurstMutedGreen
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedTab) {
                    0 -> BurstSimulatorTab(
                        uiState = uiState,
                        onUpdateSim = { speed, dist, defs ->
                            viewModel.updateSimulation(speed, dist, defs)
                        }
                    )
                    1 -> BreakoutVectorsTab(
                        uiState = uiState,
                        onSelectVector = { viewModel.onSelectVector(it) }
                    )
                    2 -> TransitionDrillsTab(
                        uiState = uiState,
                        onSelectDrill = { viewModel.onSelectDrill(it) }
                    )
                    3 -> CounterClashesTab(
                        uiState = uiState,
                        onSelectClash = { viewModel.onSelectClash(it) }
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
