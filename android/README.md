# Android — Phase 1 native foundation

**Status:** Phase 1 implementation branch; submitted for independent review. This module is a shell only. It has no real location tracking, user records, file handling, authentication, synchronization, maps, routing, providers, scoring, or backend connection.

## Build prerequisites

- Android Studio with Android SDK Platform 36 and Build Tools 36.0.0 (provisional development baseline).
- JDK 17 or newer; this branch was designed with Gradle 8.13 and JDK 17 bytecode compatibility.
- From this directory, run `./gradlew assembleDebug` to build the app.
- Run `./gradlew testDebugUnitTest` for domain, repository, and synthetic Room transaction tests.
- With an emulator/device available, run `./gradlew connectedDebugAndroidTest` for Room migration tests.

The five tabs and theme preference are in-memory review-shell behavior. Room entities and writes are limited to synthetic test fixtures. The exported Room schemas and migration contract are versioned under `app/schemas/`.
