package com.fuse.fieldeye.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fuse.fieldeye.domain.EvidenceState

/** Test/demo-only storage row. Never use this entity for real user or field records. */
@Entity(tableName = "phase1_synthetic_records")
data class SyntheticRecordEntity(
    @PrimaryKey val id: String,
    val label: String,
    val createdAtEpochMillis: Long,
    @ColumnInfo(defaultValue = "'UNAVAILABLE'")
    val evidenceState: String = EvidenceState.UNAVAILABLE.name,
)
