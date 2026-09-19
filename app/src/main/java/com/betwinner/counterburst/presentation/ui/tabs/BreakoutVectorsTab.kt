package com.betwinner.counterburst.presentation.ui.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.counterburst.core.theme.BurstGold
import com.betwinner.counterburst.core.theme.BurstMutedGreen
import com.betwinner.counterburst.domain.model.BreakoutVector
import com.betwinner.counterburst.presentation.ui.components.BreakoutVectorCard
import com.betwinner.counterburst.presentation.viewmodel.BurstUiState

@Composable
fun BreakoutVectorsTab(
    uiState: BurstUiState,
    onSelectVector: (BreakoutVector) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        item {
            Column {
                Text(
                    text = "EXPLOSIVE BREAKOUT VECTORS",
                    color = BurstGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Sprint lines, release latency, and tactical triggers for lethal counter-attacks.",
                    color = BurstMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.vectors, key = { it.id }) { vector ->
            BreakoutVectorCard(
                vector = vector,
                onClick = { onSelectVector(vector) }
            )
        }
    }
}
