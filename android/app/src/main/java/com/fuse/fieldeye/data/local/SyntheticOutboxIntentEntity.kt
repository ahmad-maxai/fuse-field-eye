package com.fuse.fieldeye.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** Synthetic transaction fixture only; not a production sync queue or network contract. */
@Entity(
    tableName = "phase1_synthetic_outbox_intents",
    foreignKeys = [
        ForeignKey(
            entity = SyntheticRecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["recordId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["recordId"], unique = true)],
)
data class SyntheticOutboxIntentEntity(
    @PrimaryKey val id: String,
    val recordId: String,
    val operationLabel: String,
    val createdAtEpochMillis: Long,
)
