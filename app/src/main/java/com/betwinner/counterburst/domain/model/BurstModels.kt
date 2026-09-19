package com.betwinner.counterburst.domain.model

data class BreakoutVector(
    val id: String,
    val vectorName: String,
    val vectorArchetype: String, // Diagonal Flank Release, Central Spine Surge, Over-the-Top Direct, Third-Man Overlap Burst
    val transitionSpeedMps: Float, // Meters per second
    val passReleaseLatencySec: Float,
    val directGoalProbabilityPct: Float,
    val tacticalDescription: String,
    val triggerKeys: List<String>,
    val vulnerabilityFactors: List<String>
)

data class TransitionDrill(
    val id: String,
    val drillTitle: String,
    val scenarioType: String, // 3v2 Blitz Attack, Turnover-to-Box Wave, 10-Second Countdown Break
    val fieldZoneLengthMeters: Int,
    val targetExecutionTimeSec: Float,
    val runnersCount: Int,
    val tacticalRules: List<String>,
    val coachingCues: List<String>
)

data class CounterClashMatch(
    val id: String,
    val headline: String,
    val teamA: String,
    val teamB: String,
    val score: String,
    val keyFastBreakRunner: String,
    val avgTransitionTimeSec: Float,
    val counterGoalsScored: Int,
    val tacticalBreakdown: String,
    val breakoutMoments: List<BreakoutMoment>
)

data class BreakoutMoment(
    val minute: Int,
    val triggerZone: String,
    val ballWinner: String,
    val sprinter: String,
    val goalOutcome: String
)

data class BurstSimulationResult(
    val runnerTopSpeedKmh: Float,
    val sprintDistanceMeters: Float,
    val defendersRecovering: Int,
    val timeToBoxArrivalSec: Float,
    val defenderInterceptionRiskPct: Int,
    val shotConversionExpectedPct: Int,
    val tacticalAnalysis: String
)
