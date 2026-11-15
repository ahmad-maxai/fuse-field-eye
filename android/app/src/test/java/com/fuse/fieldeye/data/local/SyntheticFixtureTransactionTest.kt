package com.fuse.fieldeye.data.local

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class SyntheticFixtureTransactionTest {
    private lateinit var database: FieldEyeDatabase
    private lateinit var dao: SyntheticFixtureDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, FieldEyeDatabase::class.java)
            .addMigrations(FieldEyeDatabase.MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()
        dao = database.syntheticFixtureDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun recordAndOutboxIntentAreCommittedTogether() = runBlocking {
        val record = record("record-a")
        val intent = intent("intent-a", record.id)
        dao.insertRecordAndIntent(record, intent)
        assertEquals(1, dao.recordCount())
        assertEquals(1, dao.intentCount())
        assertEquals(record, dao.findRecord(record.id))
    }

    @Test
    fun outboxInsertFailureRollsBackTheNewRecord() = runBlocking {
        val existing = record("existing-record")
        dao.insertRecordAndIntent(existing, intent("duplicate-intent-id", existing.id))

        val newRecord = record("must-roll-back")
        val collidingIntent = intent("duplicate-intent-id", newRecord.id)
        try {
            dao.insertRecordAndIntent(newRecord, collidingIntent)
            throw AssertionError("Expected duplicate outbox primary key to fail")
        } catch (_: SQLiteConstraintException) {
            // Expected: the enclosing Room transaction must roll back newRecord.
        }

        assertEquals(1, dao.recordCount())
        assertEquals(1, dao.intentCount())
        assertEquals(null, dao.findRecord(newRecord.id))
    }

    private fun record(id: String) = SyntheticRecordEntity(
        id = id,
        label = "Synthetic fixture",
        createdAtEpochMillis = 1_000L,
    )

    private fun intent(id: String, recordId: String) = SyntheticOutboxIntentEntity(
        id = id,
        recordId = recordId,
        operationLabel = "SYNTHETIC_TEST_ONLY",
        createdAtEpochMillis = 1_001L,
    )
}
