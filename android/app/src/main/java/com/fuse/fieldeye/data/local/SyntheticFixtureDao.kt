package com.fuse.fieldeye.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class SyntheticFixtureDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    protected abstract suspend fun insertRecord(record: SyntheticRecordEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    protected abstract suspend fun insertIntent(intent: SyntheticOutboxIntentEntity)

    @Query("SELECT COUNT(*) FROM phase1_synthetic_records")
    abstract suspend fun recordCount(): Int

    @Query("SELECT COUNT(*) FROM phase1_synthetic_outbox_intents")
    abstract suspend fun intentCount(): Int

    @Query("SELECT * FROM phase1_synthetic_records WHERE id = :id LIMIT 1")
    abstract suspend fun findRecord(id: String): SyntheticRecordEntity?

    @Transaction
    open suspend fun insertRecordAndIntent(
        record: SyntheticRecordEntity,
        intent: SyntheticOutboxIntentEntity,
    ) {
        require(record.id == intent.recordId) { "synthetic outbox intent must reference its record" }
        insertRecord(record)
        insertIntent(intent)
    }
}
