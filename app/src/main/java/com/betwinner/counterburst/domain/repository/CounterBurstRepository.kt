package com.betwinner.counterburst.domain.repository

import com.betwinner.counterburst.domain.model.*

interface CounterBurstRepository {
    suspend fun getBreakoutVectors(): List<BreakoutVector>
    suspend fun getTransitionDrills(): List<TransitionDrill>
    suspend fun getMatchClashes(): List<CounterClashMatch>
    fun calculateBurstSimulation(
        sprintSpeedKmh: Float,
        distanceMeters: Float,
        defendersCount: Int
    ): BurstSimulationResult
}
