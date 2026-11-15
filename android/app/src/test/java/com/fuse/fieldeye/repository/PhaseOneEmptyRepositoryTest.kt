package com.fuse.fieldeye.repository

import com.fuse.fieldeye.domain.Evidence
import org.junit.Assert.assertTrue
import org.junit.Test

class PhaseOneEmptyRepositoryTest {
    @Test
    fun noLiveDataIsExplicitlyUnavailable() {
        val overview = PhaseOneEmptyRepository().overview()
        assertTrue(overview.sessions is Evidence.Unavailable)
        assertTrue(overview.visits is Evidence.Unavailable)
        assertTrue(overview.recordedDistanceMeters is Evidence.Unavailable)
    }
}
