package com.betwinner.counterburst.data

import com.betwinner.counterburst.domain.model.*
import com.betwinner.counterburst.domain.repository.CounterBurstRepository

class InMemoryCounterBurstRepository : CounterBurstRepository {

    override suspend fun getBreakoutVectors(): List<BreakoutVector> {
        return listOf(
            BreakoutVector(
                id = "bv1",
                vectorName = "Diagonal Flank Spring",
                vectorArchetype = "Touchline Isolation Spring",
                transitionSpeedMps = 9.4f,
                passReleaseLatencySec = 1.1f,
                directGoalProbabilityPct = 78.5f,
                tacticalDescription = "Immediate diagonal outlet driven to a sprinting winger hugging the touchline, bypassing the opponent's compressed counter-press cluster.",
                triggerKeys = listOf(
                    "Midfield recovery in central defensive quadrant",
                    "Opponent full-back caught deep in attack transition",
                    "Winger initiates sprint on first defensive touch"
                ),
                vulnerabilityFactors = listOf(
                    "Overhit long ball running into goalkeeper corridor",
                    "Aggressive tactical foul prior to half-line crossing"
                )
            ),
            BreakoutVector(
                id = "bv2",
                vectorName = "Central Spine Surge",
                vectorArchetype = "Direct Vertical Penetration",
                transitionSpeedMps = 10.1f,
                passReleaseLatencySec = 0.8f,
                directGoalProbabilityPct = 84.2f,
                tacticalDescription = "One-touch forward pass into a withdrawing striker who instantly lays off to a surging central attacking midfielder tearing past the backline.",
                triggerKeys = listOf(
                    "Opponent defensive midfielder displaced sideways",
                    "Striker checks down toward ball carrier",
                    "Secondary runner bursts through half-space channel"
                ),
                vulnerabilityFactors = listOf(
                    "Offside flag triggered by early run initiation",
                    "Central congestion if first touch slows down momentum"
                )
            ),
            BreakoutVector(
                id = "bv3",
                vectorName = "Over-the-Top Direct Blitz",
                vectorArchetype = "Second-Line Ballistic Release",
                transitionSpeedMps = 8.8f,
                passReleaseLatencySec = 1.4f,
                directGoalProbabilityPct = 72.0f,
                tacticalDescription = "Deep driven lofted pass dropped precisely into space behind a high 50-meter defensive line, targeting an isolated footrace.",
                triggerKeys = listOf(
                    "Center-back with elite pass range receives facing forward",
                    "Opponent backline stationary or stepping up simultaneously",
                    "Striker holds run until pass trajectory takes flight"
                ),
                vulnerabilityFactors = listOf(
                    "Sweeper-keeper header clearance outside penalty box",
                    "Wind resistance affecting trajectory bounce velocity"
                )
            ),
            BreakoutVector(
                id = "bv4",
                vectorName = "Third-Man Overlap Burst",
                vectorArchetype = "Multi-Runner Overload Transition",
                transitionSpeedMps = 9.8f,
                passReleaseLatencySec = 1.6f,
                directGoalProbabilityPct = 81.5f,
                tacticalDescription = "Player A passes to Player B, who immediately slips a blindside runner (Player C) breaking with unchecked momentum from deep defense.",
                triggerKeys = listOf(
                    "Double decoy dummy run to drag center-backs",
                    "Late arrival from blindside box-to-box midfielder",
                    "Wall pass angled at 45 degrees into running path"
                ),
                vulnerabilityFactors = listOf(
                    "Heavy wall pass forcing runner wide of shooting angle",
                    "Defensive recovery sprint blocking the cutback lane"
                )
            )
        )
    }

    override suspend fun getTransitionDrills(): List<TransitionDrill> {
        return listOf(
            TransitionDrill(
                id = "td1",
                drillTitle = "3v2 Blitz Attack",
                scenarioType = "Overload Fast-Break Wave",
                fieldZoneLengthMeters = 55,
                targetExecutionTimeSec = 6.5f,
                runnersCount = 3,
                tacticalRules = listOf(
                    "Attacking wave must register shot on target within 7 seconds",
                    "Maximum 2 touches per player inside midfield transit zone",
                    "Defenders can only step forward, no backpedaling past the 18m line"
                ),
                coachingCues = listOf(
                    "Commit the nearest center-back before releasing the ball",
                    "Runners must diverge diagonally to stretch defensive spacing",
                    "Far-post runner attacks the blindside for second-chance rebounds"
                )
            ),
            TransitionDrill(
                id = "td2",
                drillTitle = "Turnover-to-Box Wave",
                scenarioType = "Live Transition Velocity Matrix",
                fieldZoneLengthMeters = 70,
                targetExecutionTimeSec = 8.2f,
                runnersCount = 4,
                tacticalRules = listOf(
                    "Drill begins on unannounced whistle or turnover prompt",
                    "Initial pass must travel forward a minimum of 25 meters",
                    "All 4 attacking runners must arrive inside the penalty box"
                ),
                coachingCues = listOf(
                    "Explosive initial 5-meter acceleration determines success",
                    "Pass ahead of the sprinting runner's stride, not to their feet",
                    "Trailing runner occupies the top of the box for cutback deliveries"
                )
            ),
            TransitionDrill(
                id = "td3",
                drillTitle = "10-Second Countdown Break",
                scenarioType = "Full-Pitch Blitz Scenario",
                fieldZoneLengthMeters = 90,
                targetExecutionTimeSec = 9.8f,
                runnersCount = 5,
                tacticalRules = listOf(
                    "Entire sequence from box clearance to goal finish under 10s",
                    "Any backward pass adds 2 seconds penalty to the team clock",
                    "Final shot must be taken from inside the 16-meter penalty box"
                ),
                coachingCues = listOf(
                    "Relentless forward momentum—first impulse is always penetrating",
                    "Scan goalkeeper position during final 15-meter dribble sprint",
                    "Finish across the keeper's body toward the lower side netting"
                )
            )
        )
    }

    override suspend fun getMatchClashes(): List<CounterClashMatch> {
        return listOf(
            CounterClashMatch(
                id = "cc1",
                headline = "The Munich Blitzkrieg",
                teamA = "Real Madrid",
                teamB = "Bayern Munich",
                score = "4 - 0",
                keyFastBreakRunner = "Gareth Bale & Cristiano Ronaldo",
                avgTransitionTimeSec = 6.1f,
                counterGoalsScored = 3,
                tacticalBreakdown = "Ancelotti's Madrid dismantled Guardiola's Bayern via devastating 3-man counter-attacks, turning defensive corners into goals in under 12 seconds.",
                breakoutMoments = listOf(
                    BreakoutMoment(
                        minute = 34,
                        triggerZone = "Defensive 18-yard box clearance",
                        ballWinner = "Xabi Alonso",
                        sprinter = "Gareth Bale",
                        goalOutcome = "Unstoppable 60m sprint setup for Cristiano Ronaldo tap-in"
                    ),
                    BreakoutMoment(
                        minute = 44,
                        triggerZone = "Center circle turnover tackle",
                        ballWinner = "Luka Modrić",
                        sprinter = "Ángel Di María",
                        goalOutcome = "Penetrating pass slicing Bayern's backline open"
                    )
                )
            ),
            CounterClashMatch(
                id = "cc2",
                headline = "The King Power Lightning",
                teamA = "Leicester City",
                teamB = "Manchester City",
                score = "3 - 1",
                keyFastBreakRunner = "Jamie Vardy & Riyad Mahrez",
                avgTransitionTimeSec = 5.8f,
                counterGoalsScored = 3,
                tacticalBreakdown = "Ranieri's historic title-winning machine executed textbook direct transitions, using Kante's turnovers and Mahrez's instant diagonal releases to unleash Vardy.",
                breakoutMoments = listOf(
                    BreakoutMoment(
                        minute = 18,
                        triggerZone = "Midfield right half-space interception",
                        ballWinner = "N'Golo Kanté",
                        sprinter = "Jamie Vardy",
                        goalOutcome = "Over-the-top ball behind Otamendi leading to direct goal strike"
                    ),
                    BreakoutMoment(
                        minute = 48,
                        triggerZone = "Defensive edge of area header",
                        ballWinner = "Wes Morgan",
                        sprinter = "Riyad Mahrez",
                        goalOutcome = "Solo fast-break carry past two defenders and clinical near-post finish"
                    )
                )
            ),
            CounterClashMatch(
                id = "cc3",
                headline = "Dortmund Heavy-Metal Break",
                teamA = "Borussia Dortmund",
                teamB = "Real Madrid",
                score = "4 - 1",
                keyFastBreakRunner = "Marco Reus & Robert Lewandowski",
                avgTransitionTimeSec = 6.4f,
                counterGoalsScored = 4,
                tacticalBreakdown = "Klopp's legendary Gegenpressing machine turned turnover seconds into lethal vertical darts, overpowering Madrid's defensive transition structure.",
                breakoutMoments = listOf(
                    BreakoutMoment(
                        minute = 50,
                        triggerZone = "Attacking third tackle",
                        ballWinner = "İlkay Gündoğan",
                        sprinter = "Marco Reus",
                        goalOutcome = "Reus drives into box, slipping Lewandowski on the turn"
                    ),
                    BreakoutMoment(
                        minute = 67,
                        triggerZone = "Central midfield turnover press",
                        ballWinner = "Sven Bender",
                        sprinter = "Mario Götze",
                        goalOutcome = "Rapid 4-touch vertical surge earning decisive penalty"
                    )
                )
            )
        )
    }

    override fun calculateBurstSimulation(
        sprintSpeedKmh: Float,
        distanceMeters: Float,
        defendersCount: Int
    ): BurstSimulationResult {
        // Speed in m/s = km/h / 3.6
        val speedMps = sprintSpeedKmh / 3.6f
        val timeSec = distanceMeters / speedMps

        // Defender recovery risk increases with more defenders and longer transit time
        val risk = ((defendersCount * 18) + (timeSec * 4.5f)).coerceIn(12f, 94f).toInt()

        // Conversion pct decreases if risk is high, increases with speed
        val conversion = (110 - risk + (sprintSpeedKmh * 0.4f)).coerceIn(15f, 96f).toInt()

        val analysis = when {
            timeSec < 5.0f && risk < 35 ->
                "LETHAL FAST-BREAK: Uncontested vertical trajectory. Defenders are completely caught backpedaling with zero coverage angles."
            timeSec < 7.5f && risk < 60 ->
                "HIGH VELOCITY MOMENTUM: Attacker maintains numerical edge. Quick release before second defender recovery recovers possession."
            else ->
                "CONGESTED TRANSITION: Recovery runners are closing the shooting corridor. Secondary trailing support runner required to recycle."
        }

        return BurstSimulationResult(
            runnerTopSpeedKmh = sprintSpeedKmh,
            sprintDistanceMeters = distanceMeters,
            defendersRecovering = defendersCount,
            timeToBoxArrivalSec = String.format("%.2f", timeSec).toFloat(),
            defenderInterceptionRiskPct = risk,
            shotConversionExpectedPct = conversion,
            tacticalAnalysis = analysis
        )
    }
}
