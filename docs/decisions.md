# Decisions and Approval Register

**Status:** Review draft; this register does not grant implementation approval.

## Already specified by the project owner

- V1 is for independent agents and private personal records; teams and supervisor accounts are not mandatory.
- The approved technology direction is native Android/Kotlin/Jetpack Compose/MVVM with Room, WorkManager and Google Play services Location APIs, and Supabase/PostgreSQL/PostGIS with Auth, Storage, limited Realtime and Edge Functions for trusted operations.
- Location capture must be user-controlled, preserve timestamps and accuracy, adapt sampling, minimize battery use, and make active tracking visible.
- Offline visit/session capture and duplicate-safe synchronization are requirements.
- Raw measurements must remain distinct from calculated results; missing data must not be fabricated; deterministic methods are preferred for distance, duration and scoring.
- No full application, production database, deployment, secret use, or paid API use is authorized during the UI/UX milestone.
- The UI/UX draft records a map-first visual direction and five-tab navigation (Home, Map, Activity, Analytics, Profile) for review; it remains subject to independent review and owner approval.

## Open approvals recorded in the UI/UX draft

- Confirm final design tokens and temporary text-only identity treatment; official logo assets are not supplied.
- Decide whether an active session continues tracking when the app is minimized or the screen is locked, and therefore whether a location foreground service/background access is required.
- Define when precise location is essential and where approximate location is sufficient.
- Approve visit outcome categories and whether visit timing is a timestamp or interval.
- Define freshness/staleness thresholds by data type and minimum-data rules for comparisons or any opportunity assessment.
- Approve place, route, neighbourhood-boundary and network-measurement sources, data terms, cost, and data-flow approach before integration.
- Confirm route modes and service behavior; route alternatives are not guaranteed and turn-by-turn navigation is not promised by the prototype.
- Decide measurement behavior/limits, location-sampling policy, retention, export/deletion, unsynced-record handling, and attachment requirements.
- Complete accessibility, contrast, font-scaling, outdoor-use, and device testing before implementation approval.

Refer to [UI/UX Design Specification V1](ui-ux-spec-v1.md) for context and source references. No additional architectural decision is made here.
