# Decisions and Approval Register

**Status:** Architecture review draft with independent official-source audit completed 14 November 2026. The bounded Phase 1 recommendation does not approve later capture, sensitive-data, backend, provider or synchronization work; recommendations are not owner-approved implementation decisions.

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
- Define when precise location is essential and where approximate location is sufficient; the audit added an approximate-only test, but does not decide whether that quality is adequate for recording.
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
- Stable entity UUIDs, append-only raw location samples, and distinct mutation operation UUIDs with payload-hash receipts, atomic expected-revision updates and exact-replay handling; no blanket last-write-wins conflict policy.
- Server-ordered account change feed with page-and-cursor atomic local commit, tombstones in the same feed, cursor-expiry full resnapshot, race-safe WorkManager drain/retrigger, and offline/tombstone/receipt horizons approved before sync. These contracts are recommendations pending detailed technical approval; numeric retention/offline targets require owner approval.
- Installation UUIDs as non-secret provenance and account-wide reconciliation. Any promise of immediate per-device revocation requires a server-verifiable installation/session binding; an ordinary account JWT plus client-supplied UUID is not enough.
- Start location tracking only from visible user action; use a non-exported location foreground service and persistent notification for locked-screen continuity; enforce the approved hard gate by blocking start when the required notice cannot be displayed; no always-on background permission or reboot/force-stop restart by default.
- Allow independent concurrent sessions from multiple installations under the same account, with stable session UUIDs, originating-device provenance, account-scoped synchronization and conflict isolation; do not impose an account-wide active-session lock.
- Begin with a small number of Gradle modules and package-level feature boundaries; split only when testing, isolation or build complexity justifies it.
- Use PostGIS `geography(Point,4326)` with GiST indexes for earth-distance point queries; keep shared neighborhood reference data source/version/licence-aware and separate from personal records.
- Enforce owner-scoped RLS plus minimal grants for every exposed personal table; keep attachments in private Storage with owner-constrained policies. Store local attachment bytes app-privately; approve encryption/key lifecycle and backup rules before real sensitive files. Prefer authenticated downloads when current authorization must be checked. Signed URLs are bearer links that remain valid until expiry after Auth revocation; any TTL must be owner-approved and links must not be logged, persisted or shared.
- Keep Realtime optional and advisory; synchronization correctness comes from the durable outbox and reconciliation.
- Do not persist Google route geometry by default; review the applicable provider terms and show route estimates separately from recorded GPS. If Google Routes/Places is selected, display its content on a Google Map with attribution, follow storage/caching limits and never use Google Places coordinates for point-in-polygon neighbourhood analysis; verify applicable EEA terms.
- Map quota planning must map each enabled SKU to its actual billable unit; budgets/alerts do not cap spend.
- Keep scoring decision open. If included, require owner-approved method, data, evidence threshold and validation; if deferred, label the capability as not assessed rather than silently omit the decision.
- Define explicit Android backup rules and database-plus-object recovery before release; do not treat platform backup as the only recovery path for unsynced work.
- Keep operational diagnostics privacy-minimized and defer third-party telemetry until its purpose and data handling are approved.

## Unresolved architecture decisions requiring owner approval

### Phase 1 boundary and later-phase gates

- **Audit recommendation: APPROVE FOR PHASE 1 (bounded).** Phase 1 may proceed only as shell/domain-model work using synthetic or otherwise non-sensitive fixtures: Compose navigation, Room schema/migration contract, and transaction tests. It does not include real location/visit/attachment persistence, tracking, providers, Supabase, or synchronization. If the proposed scope expands to sensitive local data, it is no longer covered by the Phase 1 approval recommendation.
- Before real sensitive local persistence, approve the threat model and whether/how Room and app-private attachment bytes are encrypted, Android Keystore/key-loss recovery, account sign-out/deletion handling, explicit cloud and device-to-device backup include/exclude rules, and restored-account/install-identity reconciliation.
- Before Phase 2 capture, confirm which Android system surface satisfies the persistent notification requirement and behavior if a user dismisses it after start; retain the approved hard gate. Under the strict recommendation, Task-Manager-only visibility does not satisfy the notification-drawer requirement. Also approve Android/device baseline, approximate-versus-precise adequacy, measurable freshness/accuracy/gap and battery targets.
- Before Phase 4 sync, approve an offline/replay horizon or mandatory full-resnapshot behavior, tombstone/operation-receipt retention, and account conflict/sign-out semantics. The technical design must validate the server cursor, atomic operation receipt/expected-revision contract and race-safe WorkManager scheduling before implementation.
- Before production attachment handling, approve V1 formats, file/count/aggregate limits, capture/import, EXIF, derivatives/originals, retention, quota, signed-link TTL policy (if any), and local/cloud recovery semantics. V1 attachment inclusion remains approved; these policies do not.



- Screen-lock recording and the persistent-notification start hard gate are approved. Confirm the proposed best-effort ordinary process-death restart; Android cannot guarantee recording after force-stop, reboot, permission revocation or location services being disabled.
- Multi-device accounts and simultaneous independent sessions across devices are approved. Decide device list/revocation UX, synced edit conflicts, deletion tombstones and optional concurrent-session messaging; none may create an account-wide active-session restriction.
- What is the minimum and target Android version, and which accessibility/device baseline is required?
- For the approved foreground-service notification gate, is notification-drawer visibility required (recommended strict interpretation), or does another Android surface count? What is the approved behavior if the user dismisses the notice mid-session, noting that Android may not provide an immediate loss signal? Do not weaken the existing hard gate.
- What is the approved visit outcome vocabulary and point-in-time versus interval model?
- What are retention, export, deletion-cascade, account-deletion, unsynced sign-out, local encryption/key recovery and device cloud/D2D backup rules? If deleted Storage content is restored from an object backup, how will deletion intent be re-applied?
- Which mapping/route, place/search, neighbourhood-boundary and network-measurement providers or datasets are acceptable? Is Google Routes API or Places API in V1 scope?
- What route modes and offline behavior are required? Which map/provider terms allow locally storing provider responses?
- Attachments are included in V1, but decide supported formats, file/count/aggregate limits, capture/import UX, EXIF handling, derivative/original behavior, quota and retention. Earlier sample formats, numeric caps and privacy transformations are proposals only, not owner-approved policy.
- Which network observations can the target Android devices expose, and is an opt-in speed test required?
- Opportunity scoring is undecided. Explicitly choose either a minimal deterministic, evidence-backed V1 assessment or defer scoring from V1. If included, approve definition, licensed inputs, sample/freshness threshold, validation, explanation, access scope and recurring cost before schema/API design.
- What location-sample intervals, accuracy/gap/freshness rules and measurable battery/track-quality targets are required? Requested cadence is not a platform guarantee. What maximum offline/replay horizon is supported, or should a full resnapshot be required beyond it? Set RPO/RTO, backup budget and restore cadence.
- What cost ceiling, billing owner, data region/residency, API quota and privacy disclosures apply?
- What are the recovery-point and recovery-time objectives and associated backup budget?
- Is third-party crash/error telemetry allowed, and what may leave the device?

No database schema, resource, provider, permission or implementation choice is approved by this register. See the architecture document for rationale, official references and phased acceptance criteria.
