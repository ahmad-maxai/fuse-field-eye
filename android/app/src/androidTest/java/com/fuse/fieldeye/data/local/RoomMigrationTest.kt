package com.fuse.fieldeye.data.local

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomMigrationTest {
    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        FieldEyeDatabase::class.java,
        emptyList(),
    )

    @Test
    fun versionOneToTwoPreservesSyntheticRowsAndAddsEvidenceState() {
        val databaseName = "phase1-migration-test"
        helper.createDatabase(databaseName, 1).apply {
            execSQL(
                "INSERT INTO phase1_synthetic_records (id, label, createdAtEpochMillis) " +
                    "VALUES ('synthetic-before-migration', 'Synthetic fixture', 1000)",
            )
            close()
        }

        val migrated = helper.runMigrationsAndValidate(
            databaseName,
            2,
            true,
            FieldEyeDatabase.MIGRATION_1_2,
        )
        migrated.query(
            "SELECT evidenceState FROM phase1_synthetic_records WHERE id = 'synthetic-before-migration'",
        ).use { cursor ->
            check(cursor.moveToFirst())
            assertEquals("UNAVAILABLE", cursor.getString(0))
        }
        migrated.close()
    }
}
