package com.fuse.fieldeye.domain

import com.fuse.fieldeye.viewmodel.displayLabel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EvidenceTest {
    @Test
    fun evidenceStatesRemainExplicit() {
        val observed: Evidence<Int> = Evidence.Observed(7, "synthetic-test", 1_000L)
        val estimated: Evidence<Double> = Evidence.Estimated(12.5, "synthetic-method", 1_000L)
        val stale: Evidence<Int> = Evidence.Stale(3, "synthetic-test", 1_000L, "fixture")
        val unavailable: Evidence<Int> = Evidence.Unavailable("not measured")

        assertTrue(observed is Evidence.Observed)
        assertTrue(estimated is Evidence.Estimated)
        assertTrue(stale is Evidence.Stale)
        assertTrue(unavailable is Evidence.Unavailable)
    }

    @Test
    fun unavailableEvidenceIsNotRenderedAsNumericZero() {
        val missing: Evidence<Double> = Evidence.Unavailable("No source data")
        assertEquals("Unavailable", missing.displayLabel())
        assertTrue(missing.displayLabel() != "0")
        assertTrue(missing.displayLabel() != "0.0")
    }

    @Test
    fun sessionSummaryAllowsUnknownMetricsWithoutInventingValues() {
        val summary = SessionSummary(
            id = "synthetic-session",
            status = SessionStatus.IDLE,
            startedAtEpochMillis = null,
            elapsedSeconds = Evidence.Unavailable("not recorded"),
            recordedDistanceMeters = Evidence.Unavailable("no location samples"),
            sampleCount = null,
        )
        assertTrue(summary.elapsedSeconds is Evidence.Unavailable)
        assertTrue(summary.recordedDistanceMeters is Evidence.Unavailable)
        assertEquals(null, summary.sampleCount)
    }
}
