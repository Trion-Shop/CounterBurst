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
import com.betwinner.counterburst.domain.model.CounterClashMatch
import com.betwinner.counterburst.presentation.ui.components.CounterClashCard
import com.betwinner.counterburst.presentation.viewmodel.BurstUiState

@Composable
fun CounterClashesTab(
    uiState: BurstUiState,
    onSelectClash: (CounterClashMatch) -> Unit,
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
                    text = "HISTORIC COUNTER-ATTACK CLASHES",
                    color = BurstGold,
                    fontSize = 16.sp,
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
