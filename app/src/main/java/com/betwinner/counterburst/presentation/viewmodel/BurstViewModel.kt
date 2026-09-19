package com.betwinner.counterburst.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betwinner.counterburst.data.InMemoryCounterBurstRepository
import com.betwinner.counterburst.domain.model.*
import com.betwinner.counterburst.domain.repository.CounterBurstRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BurstUiState(
    val selectedTab: Int = 0,
    val vectors: List<BreakoutVector> = emptyList(),
    val drills: List<TransitionDrill> = emptyList(),
    val clashes: List<CounterClashMatch> = emptyList(),
    // Simulator inputs
    val simSpeedKmh: Float = 33.5f,
    val simDistanceMeters: Float = 55.0f,
    val simDefendersCount: Int = 2,
    val simulationResult: BurstSimulationResult? = null,
    // Active deep-dive modals
    val activeVectorModal: BreakoutVector? = null,
    val activeDrillModal: TransitionDrill? = null,
    val activeClashModal: CounterClashMatch? = null
)

class BurstViewModel(
    private val repository: CounterBurstRepository = InMemoryCounterBurstRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(BurstUiState())
    val uiState: StateFlow<BurstUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val vecs = repository.getBreakoutVectors()
            val drs = repository.getTransitionDrills()
            val cls = repository.getMatchClashes()
            val sim = repository.calculateBurstSimulation(33.5f, 55.0f, 2)

            _uiState.update {
                it.copy(
                    vectors = vecs,
                    drills = drs,
                    clashes = cls,
                    simulationResult = sim
                )
            }
        }
    }

    fun onSelectTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedTab = tabIndex) }
    }

    fun onSelectVector(vector: BreakoutVector) {
        _uiState.update { it.copy(activeVectorModal = vector) }
    }

    fun onSelectDrill(drill: TransitionDrill) {
        _uiState.update { it.copy(activeDrillModal = drill) }
    }

    fun onSelectClash(clash: CounterClashMatch) {
        _uiState.update { it.copy(activeClashModal = clash) }
    }

    fun dismissModal() {
        _uiState.update {
            it.copy(
                activeVectorModal = null,
                activeDrillModal = null,
                activeClashModal = null
            )
        }
    }

    fun updateSimulation(speed: Float, distance: Float, defenders: Int) {
        val sim = repository.calculateBurstSimulation(speed, distance, defenders)
        _uiState.update {
            it.copy(
                simSpeedKmh = speed,
                simDistanceMeters = distance,
                simDefendersCount = defenders,
                simulationResult = sim
            )
        }
    }
}
