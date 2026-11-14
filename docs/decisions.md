# Decisions and Approval Register

**Status:** Architecture review draft. Recommendations in this file are not owner-approved implementation decisions.

## Already specified by the project owner

- V1 is for independent agents and private personal records; teams and supervisor accounts are not mandatory.
- Approved technology direction: native Android/Kotlin/Jetpack Compose/MVVM with Room, WorkManager and Google Play services Location APIs; Supabase/PostgreSQL/PostGIS with Auth, Storage, limited Realtime and Edge Functions for trusted operations; Google Maps Platform mapping direction.
- Location capture is user-controlled, preserves timestamps and accuracy, adapts sampling, minimizes battery use and clearly indicates active tracking. Offline visit/session recording and duplicate-safe synchronization are requirements.
- Raw measurements remain distinct from calculations; missing information is not fabricated; deterministic calculations are preferred.
- Five primary tabs: Home, Map, Activity, Analytics and Profile; the V1 UI/UX screens/prototype were approved for transition to architecture.
- **Authoritative V1.1 addendum (13 November 2026):** an actively running session must continue location recording while the screen is locked, using a foreground-service design subject to platform limits; no guarantee after force-stop, reboot, permission revocation or disabled location services. One account must work across multiple Android devices. Photos and file attachments are included in V1. Opportunity scoring is explicitly undecided; the architecture must present include/defer trade-offs rather than silently deciding.
- **Authoritative owner decisions update (14 November 2026):** multiple devices on one account may record independent field sessions simultaneously; do not impose a one-active-session-per-account restriction. Attachments remain included in V1, but supported formats, file/count/aggregate limits, capture/import UX, EXIF handling, derivatives/originals and retention policies are open decisions; earlier proposed values are not approved. Block session start if Android cannot display the required persistent foreground-service notification and clearly explain the failure.
- These updates authorize documentation revision only. They do not authorize Android implementation, database deployment, cloud resources, paid API activation or credentials; `main` must remain unchanged.

## Prior UI/UX questions that remain open unless explicitly resolved

The UI/UX specification recorded these questions. Approval of the screens/prototype does not by itself answer implementation and policy details below:

- The owner requires active screen-lock recording and has approved a hard gate: block session start if the required persistent foreground-service notification cannot be displayed, and explain why. Verify compliant behavior against the supported Android versions; do not weaken the product gate.
- Define when precise location is essential and where approximate location is sufficient.
- Approve visit outcome categories and whether visit timing is a timestamp or interval.
- Define freshness/staleness thresholds by data type and minimum-data rules for comparisons or any opportunity assessment.
- Approve place, route, neighbourhood-boundary and network-measurement sources, data terms, cost and data-flow approach before integration.
- Confirm route modes and service behavior; route alternatives are not guaranteed and turn-by-turn navigation is not promised by the approved prototype.
- Decide measurement behavior/limits, location-sampling policy, retention, export/deletion and unsynced-record handling. For attachments, decide supported formats, file/count/aggregate limits, capture/import UX, EXIF handling, derivative/original behavior, quota and retention; no specific proposed values or policy are approved.
- Confirm final design tokens and logo asset handling for native implementation if the approved prototype did not settle them.
- Complete accessibility, contrast, font-scaling, outdoor-use and device testing before implementation approval.

## Architecture recommendations — awaiting approval

The following are proposals in `docs/technical-architecture-v1.md`, not decisions already made by the owner:

- Room as the sole source read by the UI; local record plus outbox operation committed atomically.
- Stable client-generated UUIDs, append-only raw location samples, idempotent retry behavior and optimistic revision checks for editable rows; no blanket last-write-wins conflict policy.
- Installation UUIDs as non-secret provenance and account-wide reconciliation. Any promise of immediate per-device revocation requires a server-verifiable installation/session binding; an ordinary account JWT plus client-supplied UUID is not enough.
- Start location tracking only from visible user action; use a non-exported location foreground service and persistent notification for locked-screen continuity; enforce the approved hard gate by blocking start when the required notice cannot be displayed; no always-on background permission or reboot/force-stop restart by default.
- Allow independent concurrent sessions from multiple installations under the same account, with stable session UUIDs, originating-device provenance, account-scoped synchronization and conflict isolation; do not impose an account-wide active-session lock.
- Begin with a small number of Gradle modules and package-level feature boundaries; split only when testing, isolation or build complexity justifies it.
- Use PostGIS `geography(Point,4326)` with GiST indexes for earth-distance point queries; keep shared neighborhood reference data source/version/licence-aware and separate from personal records.
- Enforce owner-scoped RLS plus minimal grants for every exposed personal table; keep attachments in private Storage with owner-constrained policies.
- Keep Realtime optional and advisory; synchronization correctness comes from the durable outbox and reconciliation.
- Do not persist Google route geometry by default; review the applicable provider terms and show route estimates separately from recorded GPS.
- Keep scoring decision open. If included, require owner-approved method, data, evidence threshold and validation; if deferred, label the capability as not assessed rather than silently omit the decision.
- Define explicit Android backup rules and database-plus-object recovery before release; do not treat platform backup as the only recovery path for unsynced work.
- Keep operational diagnostics privacy-minimized and defer third-party telemetry until its purpose and data handling are approved.

## Unresolved architecture decisions requiring owner approval

- Screen-lock recording and the persistent-notification start hard gate are approved. Confirm the proposed best-effort ordinary process-death restart; Android cannot guarantee recording after force-stop, reboot, permission revocation or location services being disabled.
- Multi-device accounts and simultaneous independent sessions across devices are approved. Decide device list/revocation UX, synced edit conflicts, deletion tombstones and optional concurrent-session messaging; none may create an account-wide active-session restriction.
- What is the minimum and target Android version, and which accessibility/device baseline is required?
- What is the approved visit outcome vocabulary and point-in-time versus interval model?
- What are retention, export, deletion-cascade, account-deletion, unsynced sign-out, local encryption and device-backup rules?
- Which mapping/route, place/search, neighbourhood-boundary and network-measurement providers or datasets are acceptable? Is Google Routes API or Places API in V1 scope?
- What route modes and offline behavior are required? Which map/provider terms allow locally storing provider responses?
- Attachments are included in V1, but decide supported formats, file/count/aggregate limits, capture/import UX, EXIF handling, derivative/original behavior, quota and retention. Earlier sample formats, numeric caps and privacy transformations are proposals only, not owner-approved policy.
- Which network observations can the target Android devices expose, and is an opt-in speed test required?
- Opportunity scoring is undecided. Explicitly choose either a minimal deterministic, evidence-backed V1 assessment or defer scoring from V1. If included, approve definition, licensed inputs, sample/freshness threshold, validation, explanation, access scope and recurring cost before schema/API design.
- What location-sample intervals, accuracy/gap rules and battery targets are required?
- What cost ceiling, billing owner, data region/residency, API quota and privacy disclosures apply?
- What are the recovery-point and recovery-time objectives and associated backup budget?
- Is third-party crash/error telemetry allowed, and what may leave the device?

No database schema, resource, provider, permission or implementation choice is approved by this register. See the architecture document for rationale, official references and phased acceptance criteria.
