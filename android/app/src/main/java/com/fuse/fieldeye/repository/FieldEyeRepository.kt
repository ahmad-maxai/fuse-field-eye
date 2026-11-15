package com.fuse.fieldeye.repository

import com.fuse.fieldeye.domain.Evidence

/** Minimal read contract; Phase 1 has no live data source. */
interface FieldEyeRepository {
    fun overview(): PhaseOneOverview
}

data class PhaseOneOverview(
    val sessions: Evidence<Int>,
    val visits: Evidence<Int>,
    val recordedDistanceMeters: Evidence<Double>,
)

class PhaseOneEmptyRepository : FieldEyeRepository {
    override fun overview() = PhaseOneOverview(
        sessions = Evidence.Unavailable("No personal records are connected in this Phase 1 shell."),
        visits = Evidence.Unavailable("No personal records are connected in this Phase 1 shell."),
        recordedDistanceMeters = Evidence.Unavailable("No location evidence is collected in this Phase 1 shell."),
    )
}
