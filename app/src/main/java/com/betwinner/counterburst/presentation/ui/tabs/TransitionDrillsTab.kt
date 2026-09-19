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
import com.betwinner.counterburst.domain.model.TransitionDrill
import com.betwinner.counterburst.presentation.ui.components.TransitionDrillCard
import com.betwinner.counterburst.presentation.viewmodel.BurstUiState

@Composable
fun TransitionDrillsTab(
    uiState: BurstUiState,
    onSelectDrill: (TransitionDrill) -> Unit,
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
                    text = "TRANSITION VELOCITY DRILLS",
                    color = BurstGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Targeted training scenarios engineered to condition instant turnover breakout bursts.",
                    color = BurstMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.drills, key = { it.id }) { drill ->
            TransitionDrillCard(
                drill = drill,
                onClick = { onSelectDrill(drill) }
            )
        }
    }
}
