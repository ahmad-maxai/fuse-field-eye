# FUSE: FIELD EYE

**Project status: PHASE 1 NATIVE FOUNDATION — INDEPENDENT REVIEW**

FIELD EYE is a proposed native Android field-intelligence application for independent field agents. The V1 UI/UX and prototype have owner approval; the V1.1 technical architecture was approved for a bounded Phase 1 shell. The native foundation is being developed on `phase-1/native-shell` and is not merged into `main`.

## Project materials

- [Product brief](docs/product-brief.md)
- [UI/UX Design Specification V1](docs/ui-ux-spec-v1.md)
- [Interactive HTML prototype](prototype/FIELD_EYE_UI_UX_PROTOTYPE_V1.html)
- [Technical Architecture and Feasibility V1.1](docs/technical-architecture-v1.md)
- [Architecture status](docs/architecture.md)
- [Decisions and open approvals](docs/decisions.md)
- [Review log](docs/review-log.md)
- [Android foundation and build instructions](android/README.md)

## Phase 1 boundary

The current branch is limited to Kotlin/Compose/MVVM foundation, five-tab navigation, evidence-state/domain contracts, a synthetic-only Room schema and migration contract, repository/ViewModel structure, local synthetic tests, and basic light/dark theme support. It does not implement real location capture or foreground services, personal field records, attachments, authentication, synchronization, Supabase, maps, routes, provider APIs, opportunity scoring, credentials, deployment or cloud resources. No sensitive personal data is stored.

## Build and test

From `android/`, use `./gradlew assembleDebug` and `./gradlew testDebugUnitTest`. Room migration instrumentation tests are run with `./gradlew connectedDebugAndroidTest` on an Android emulator or device. The current SDK baseline is provisional for development and documented in `android/README.md`.

The architecture and implementation review branches remain separate from `main`; do not merge until independent review and project-owner approval are complete.
