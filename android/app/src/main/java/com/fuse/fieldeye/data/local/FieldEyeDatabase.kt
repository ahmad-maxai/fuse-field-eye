package com.fuse.fieldeye.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Phase 1 verification database. Contains synthetic fixtures only; it is not a personal-record
 * database and is not wired to production screens, authentication, or synchronization.
 */
@Database(
    entities = [SyntheticRecordEntity::class, SyntheticOutboxIntentEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class FieldEyeDatabase : RoomDatabase() {
    abstract fun syntheticFixtureDao(): SyntheticFixtureDao

    companion object {
        /** A legacy row has no evidence; migration preserves the row but does not invent a value. */
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE phase1_synthetic_records " +
                        "ADD COLUMN evidenceState TEXT NOT NULL DEFAULT 'UNAVAILABLE'",
                )
            }
        }
    }
}
