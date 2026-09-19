package com.betwinner.counterburst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.betwinner.counterburst.core.theme.CounterBurstTheme
import com.betwinner.counterburst.presentation.ui.screens.CounterBurstScreen
import com.betwinner.counterburst.presentation.viewmodel.BurstViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BurstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterBurstTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) {
                    CounterBurstScreen(viewModel = viewModel)
                }
            }
        }
    }
}
