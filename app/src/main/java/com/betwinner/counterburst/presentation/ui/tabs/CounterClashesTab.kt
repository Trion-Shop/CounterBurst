package com.betwinner.counterburst.presentation.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.counterburst.core.theme.*
import com.betwinner.counterburst.domain.model.BreakoutVector
import com.betwinner.counterburst.domain.model.CounterClashMatch
import com.betwinner.counterburst.presentation.ui.components.CounterClashCard
import com.betwinner.counterburst.presentation.viewmodel.BurstUiState

@Composable
fun CounterClashesTab(
    uiState: BurstUiState,
    onSelectClash: (CounterClashMatch) -> Unit,
    onSelectVector: (BreakoutVector) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        // Carousel of Breakout Vectors
        item {
            Column {
                Text(
                    text = "EXPLOSIVE BREAKOUT VECTORS",
                    color = BurstGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.vectors, key = { it.id }) { vector ->
                        Card(
                            modifier = Modifier
                                .width(190.dp)
                                .clickable { onSelectVector(vector) }
                                .border(1.dp, BurstGreenBorder, RoundedCornerShape(14.dp)),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = BurstPitchDark)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = vector.vectorName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BurstGoldBright
                                )
                                Text(
                                    text = vector.vectorArchetype,
                                    fontSize = 10.sp,
                                    color = BurstMutedGreen
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${vector.transitionSpeedMps} m/s",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = BurstGreenLime
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(BurstGreenCard)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "${vector.passReleaseLatencySec}s delay",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = BurstGold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = vector.tacticalDescription,
                                    fontSize = 10.sp,
                                    color = BurstSoftSilver,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "HISTORIC COUNTER-ATTACK CLASHES",
                    color = BurstGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Masterclass match breakdowns where blistering counter-attacks devastated opponents.",
                    color = BurstMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.clashes, key = { it.id }) { clash ->
            CounterClashCard(
                clash = clash,
                onClick = { onSelectClash(clash) }
            )
        }
    }
}
