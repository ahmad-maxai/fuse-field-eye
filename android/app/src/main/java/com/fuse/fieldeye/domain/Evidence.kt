package com.fuse.fieldeye.domain

/** Persistable evidence-state labels used by derived summaries and synthetic schema fixtures. */
enum class EvidenceState {
    OBSERVED,
    ESTIMATED,
    STALE,
    UNAVAILABLE,
}

/** Provenance-aware value: an absent measurement is never encoded as a numeric zero. */
sealed interface Evidence<out T> {
    data class Observed<T>(
        val value: T,
        val source: String,
        /** UTC epoch milliseconds from the originating observation. */
        val observedAtEpochMillis: Long,
        val accuracyDescription: String? = null,
    ) : Evidence<T>

    data class Estimated<T>(
        val value: T,
        val method: String,
        /** UTC epoch milliseconds when this derivation was made. */
        val estimatedAtEpochMillis: Long,
    ) : Evidence<T>

    data class Stale<T>(
        val value: T,
        val source: String,
        val observedAtEpochMillis: Long,
        val staleReason: String,
    ) : Evidence<T>

    data class Unavailable(
        val reason: String,
    ) : Evidence<Nothing>
}

enum class SessionStatus {
    IDLE,
    RECORDING,
    PAUSED,
    COMPLETED,
    INTERRUPTED,
}

data class SessionSummary(
    val id: String,
    val status: SessionStatus,
    /** UTC epoch milliseconds, null when the source timestamp is not known. */
    val startedAtEpochMillis: Long?,
    val elapsedSeconds: Evidence<Long>,
    val recordedDistanceMeters: Evidence<Double>,
    val sampleCount: Int?,
) {
    init {
        require(sampleCount == null || sampleCount >= 0) { "sampleCount must be non-negative when known" }
        require(id.isNotBlank()) { "session id must not be blank" }
    }
}

data class ActivityItem(
    val id: String,
    val title: String,
    val occurredAtEpochMillis: Long?,
    val evidenceState: Evidence<*>,
)
