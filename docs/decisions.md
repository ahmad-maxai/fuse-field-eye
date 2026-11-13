# Decisions and Approval Register

**Status:** Architecture review draft. Recommendations in this file are not owner-approved implementation decisions.

## Already specified by the project owner

- V1 is for independent agents and private personal records; teams and supervisor accounts are not mandatory.
- The approved technology direction is native Android/Kotlin/Jetpack Compose/MVVM with Room, WorkManager and Google Play services Location APIs, and Supabase/PostgreSQL/PostGIS with Auth, Storage, limited Realtime and Edge Functions for trusted operations.
- Location capture must be user-controlled, preserve timestamps and accuracy, adapt sampling, minimize battery use, and make active tracking visible.
- Offline visit/session capture and duplicate-safe synchronization are requirements.
- Raw measurements must remain distinct from calculated results; missing data must not be fabricated; deterministic methods are preferred for distance, duration and scoring.
- The evidence states are Observed, Estimated, Stale and Unavailable. “Predicted” is not a synonym for “Estimated.” Planned route geometry must be visually distinct from recorded GPS tracks.
- No offline map tiles or offline routing may be claimed unless designed and verified.
- The five primary tabs are Home, Map, Activity, Analytics and Profile.
- The V1 UI/UX screenshots and prototype have been reviewed and approved; the project is now moving into technical architecture. This status was supplied in the project owner's 13 November 2026 architecture assignment.
- No Android implementation, production database, deployment, secret use or paid API use is authorized by the architecture-documentation task.

## Prior UI/UX questions that remain open unless explicitly resolved

The UI/UX specification recorded these questions. Approval of the screens/prototype does not by itself answer implementation and policy details below:

- Decide whether an active session continues tracking when the app is minimized or the screen is locked, and therefore whether a location foreground service/background-location access is required.
- Define when precise location is essential and where approximate location is sufficient.
- Approve visit outcome categories and whether visit timing is a timestamp or interval.
- Define freshness/staleness thresholds by data type and minimum-data rules for comparisons or any opportunity assessment.
- Approve place, route, neighbourhood-boundary and network-measurement sources, data terms, cost and data-flow approach before integration.
- Confirm route modes and service behavior; route alternatives are not guaranteed and turn-by-turn navigation is not promised by the approved prototype.
- Decide measurement behavior/limits, location-sampling policy, retention, export/deletion, unsynced-record handling and attachment requirements.
- Confirm final design tokens and logo asset handling for native implementation if the approved prototype did not settle them.
- Complete accessibility, contrast, font-scaling, outdoor-use and device testing before implementation approval.

## Architecture recommendations — awaiting approval

The following are proposals in `docs/technical-architecture-v1.md`, not decisions already made by the owner:

- Room as the sole source read by the UI; local record plus outbox operation committed atomically.
- Stable client-generated UUIDs, append-only raw location samples, idempotent retry behavior and optimistic revision checks for editable rows; no blanket last-write-wins conflict policy.
- Start tracking only from a visible user action. If the user-approved session must continue with screen locked, use a user-started location foreground service; do not request background location by default.
- Begin with a small number of Gradle modules and package-level feature boundaries; split only when testing, isolation or build complexity justifies it.
- Use PostGIS `geography(Point,4326)` with GiST indexes for earth-distance point queries; keep shared neighborhood reference data source/version/licence-aware and separate from personal records.
- Enforce owner-scoped RLS plus minimal grants for every exposed personal table; keep attachments in private Storage with owner-constrained policies.
- Keep Realtime optional and advisory; synchronization correctness comes from the durable outbox and reconciliation.
- Do not persist Google route geometry by default; review the applicable provider terms and show route estimates separately from recorded GPS.
- Defer opportunity scoring until its method, evidence threshold and validation are approved.
- Define explicit Android backup rules and database-plus-object recovery before release; do not treat platform backup as the only recovery path for unsynced work.
- Keep operational diagnostics privacy-minimized and defer third-party telemetry until its purpose and data handling are approved.

## Unresolved architecture decisions requiring owner approval

- Does a field session continue when the app is minimized or the screen is locked? What should happen after the process or service stops?
- May one account sync on multiple devices? Which synced visit/session edits are permitted and how are concurrent edits/deletions resolved?
- What is the minimum and target Android version, and which accessibility/device baseline is required?
- What is the approved visit outcome vocabulary and point-in-time versus interval model?
- What are retention, export, deletion-cascade, account-deletion, unsynced sign-out, local encryption and device-backup rules?
- Which mapping/route, place/search, neighbourhood-boundary and network-measurement providers or datasets are acceptable? Is Google Routes API or Places API in V1 scope?
- What route modes and offline behavior are required? Which map/provider terms allow locally storing provider responses?
- Are attachments included in V1; if so, which types, size limits, upload and recovery behavior apply?
- Which network observations can the target Android devices expose, and is an opt-in speed test required?
- Is an opportunity assessment in V1? If so, what method, input data, minimum sample, freshness and explanation are acceptable?
- What location-sample intervals, accuracy/gap rules and battery targets are required?
- What cost ceiling, billing owner, data region/residency, API quota and privacy disclosures apply?
- What are the recovery-point and recovery-time objectives and associated backup budget?
- Is third-party crash/error telemetry allowed, and what may leave the device?

No database schema, resource, provider, permission or implementation choice is approved by this register. See the architecture document for rationale, official references and phased acceptance criteria.
