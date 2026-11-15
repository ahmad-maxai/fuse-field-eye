package com.fuse.fieldeye.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class SyntheticSchemaMigrationTest {
    @Test
    fun v1RowsMigrateToUnavailableEvidenceWithoutDataLoss() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val databaseName = "phase1-synthetic-v1.db"
        context.deleteDatabase(databaseName)
        val oldDatabase = SQLiteDatabase.openOrCreateDatabase(
            context.getDatabasePath(databaseName),
            null,
        )
        oldDatabase.execSQL(
            "CREATE TABLE phase1_synthetic_records " +
                "(id TEXT NOT NULL, label TEXT NOT NULL, " +
                "createdAtEpochMillis INTEGER NOT NULL, PRIMARY KEY(id))",
        )
        oldDatabase.execSQL(
            "CREATE TABLE phase1_synthetic_outbox_intents " +
                "(id TEXT NOT NULL, recordId TEXT NOT NULL, operationLabel TEXT NOT NULL, " +
                "createdAtEpochMillis INTEGER NOT NULL, PRIMARY KEY(id), " +
                "FOREIGN KEY(recordId) REFERENCES phase1_synthetic_records(id) " +
                "ON UPDATE NO ACTION ON DELETE CASCADE)",
        )
        oldDatabase.execSQL(
            "CREATE UNIQUE INDEX index_phase1_synthetic_outbox_intents_recordId " +
                "ON phase1_synthetic_outbox_intents(recordId)",
        )
        oldDatabase.execSQL(
            "INSERT INTO phase1_synthetic_records " +
                "(id, label, createdAtEpochMillis) VALUES (?, ?, ?)",
            arrayOf<Any>("legacy-fixture", "Synthetic fixture", 1_000L),
        )
        oldDatabase.version = 1
        oldDatabase.close()

        val migrated = Room.databaseBuilder(context, FieldEyeDatabase::class.java, databaseName)
            .addMigrations(FieldEyeDatabase.MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()
        try {
            val row = migrated.syntheticFixtureDao().findRecord("legacy-fixture")
            assertEquals("Synthetic fixture", row?.label)
            assertEquals("UNAVAILABLE", row?.evidenceState)
            assertEquals(1, migrated.syntheticFixtureDao().recordCount())
        } finally {
            migrated.close()
            context.deleteDatabase(databaseName)
        }
    }
}
