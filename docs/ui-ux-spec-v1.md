# FUSE: FIELD EYE — UI/UX Design Specification V1

**Status:** Review draft for project-owner approval  
**Project phase:** Pre-development  
**Prepared:** 12 November 2026  
**Product:** FIELD EYE, native Android field-intelligence application  
**Scope:** Interface and interaction design only. No Android application, Supabase schema, deployment, credentials, or paid API use is included.

This specification follows the approved product blueprint and the project owner's 12 November visual-direction approval. The accompanying HTML file is a reviewable interaction prototype, not an Android build, functional map, live data source, or implementation commitment. All prototype measurements and locations are explicitly illustrative.

## Product experience and design commitments

FIELD EYE should read as a professional personal intelligence tool for field agents, not as a delivery dispatch or consumer navigation app. Its primary surface is a map supported by traceable observations and concise field actions. Every relevant value is identified as measured, estimated, stale, or unavailable. The interface never makes a prediction look like an observation, and never represents missing measurements as zero.

The design is mobile-first and tuned for one-handed use. The map remains easy to reach, the field-session action is always close at hand, and detail is progressively disclosed in sheets rather than stacked into a dense map dashboard. Light mode is the initial design reference; dark mode is a fully specified alternate theme. System navigation, status bars, accessibility settings, and permission dialogs remain Android-owned UI.

The approved five-tab navigation is **Home**, **Map**, **Activity**, **Analytics**, and **Profile**. A persistent, context-aware quick-action entry point provides **Start session** or **Resume session**, **Record visit**, and **Locate me** where each action is available. The center Map tab is the primary spatial destination; a session action is not a sixth tab.

## Design system

### Visual direction

The approved visual language uses evergreen ink, warm paper, and restrained cartographic detail. Teal denotes observed or measured information. Amber denotes estimates, warnings, or data requiring attention. Neutral text and patterns support states that color alone cannot communicate.

FIELD EYE has no supplied official logo or existing UI asset. The prototype therefore uses a temporary text-only “FIELD EYE” wordmark and marks the logo as pending. It does not invent a permanent emblem, claim a final identity, or use fabricated product imagery.

### Color tokens

The following values are proposed working tokens for review. They are not an approved logo identity and can be tuned after contrast checks on actual Android screens.

| Token | Light value | Dark value | Use |
|---|---:|---:|---|
| Evergreen / primary | `#183A33` | `#B7D8C9` | Primary actions, headings, navigation emphasis |
| Evergreen deep | `#102A25` | `#0F1917` | High-emphasis text and dark-theme base |
| Warm paper / canvas | `#F4F2EA` | `#101A18` | Main background |
| Surface | `#FFFEFA` | `#192622` | Cards, sheets, input surfaces |
| Raised surface | `#FFFFFF` | `#21312C` | Dialogs and overlays |
| Primary text | `#18231F` | `#F0F2EA` | Main text |
| Secondary text | `#52615A` | `#B5C1BA` | Supporting labels and metadata |
| Divider / outline | `#D6DCD4` | `#34443D` | Separators, borders, input outlines |
| Measured / observed teal | `#08766F` | `#63C4B5` | Directly observed measurements and GPS state |
| Estimate / warning amber | `#986000` | `#E9B85C` | Provider estimates, calculations, stale-data warning |
| Error / destructive | `#A33F35` | `#F08C7E` | Errors and destructive actions |
| Unavailable neutral | `#68736E` | `#A8B1AB` | Unavailable, unsupported, or unknown values |

The UI pairs color with text, iconography or pattern. Amber is not used as a generic brand accent. Contrast must be checked against WCAG 2.2 AA targets during implementation, including large text, controls, disabled states and map labels. Do not rely on color alone to distinguish route type or confidence.

### Evidence and freshness language

Use these terms consistently throughout the product:

- **Observed** means a device or user directly recorded a value. Show its source and timestamp when available. Location observations show reported horizontal accuracy in metres.
- **Estimated** means a provider or deterministic calculation supplied a value that was not directly measured. Label it “Estimated” beside the value. Route duration is estimated; route distance may be a provider-computed route estimate, while GPS-track distance is a calculated summary of recorded samples.
- **Stale** means a value exists but is older than a product-approved freshness threshold. Show the last-measured time and a stale label. Freshness thresholds depend on data type and have not yet been approved; until they are defined, show timestamps and avoid silently converting old data into a confident current status.
- **Unavailable** means no value was recorded, permission is absent, the API does not expose the value, or the source failed. Show “Unavailable” or “Not measured”; never use zero as a substitute.
- **Predicted** is not a synonym for estimated. A prediction is a future inference and is outside the initial UI's factual status vocabulary unless a later approved opportunity feature provides a documented method, evidence, confidence and explanation.

On network observations, show **measurement time**, **location accuracy**, network type/provider where available, and the individual unavailable metrics. An old observation must not be styled like a live reading. Any map aggregation or interpolation must be labeled as estimated and visually distinguished from measured point observations.

### Type, spacing and geometry

Use Android's native sans-serif type family for the implemented UI, following system text scaling and locale behavior. Use one type family, a clear hierarchy, and tabular numeral styling for distances, durations and signal values. Avoid decorative display fonts. The prototype uses a system sans-serif to preview the intended native feel.

Use a 4 dp base unit and an 8 dp primary rhythm. Common gaps are 8, 12, 16, 24 and 32 dp. Screen insets and scroll areas respect system bars and display cutouts. Primary actions and frequently used controls target at least 48 × 48 dp. Compact chips still need an adequately sized touch region. Prefer 12–16 dp control radii and 16–20 dp card/sheet radii; keep elevation subtle and use borders or tonal contrast before shadows.

### Buttons, cards, fields and icons

A screen has one visually dominant primary action. Primary buttons use evergreen fill and clear action verbs. Secondary buttons use an outline or low-emphasis surface. Tertiary actions are text buttons. Destructive actions are visually separated and require a confirmation step when data can be lost. Loading, disabled, pressed, focused and error states are designed for all interactive controls.

Cards group a single concept and its source/confidence metadata. Avoid decorative KPI grids, repeated card-in-card nesting and colored left-border accents. Map information belongs in a compact draggable sheet or focused detail panel rather than a large opaque overlay. Search fields retain text, expose clear controls, and support a visible filter count.

The final Android icon set is not yet selected. Use consistent, labeled Material-style outline icons or an approved equivalent; icon names and touch targets need semantics for TalkBack. No emoji are used as icons. In the prototype, a small number of simple typographic glyphs are placeholders, not a final icon library.

### Map presentation

The map is the central visual element of FIELD EYE. In Map, it occupies the majority of the available viewport; controls are compact floating actions, and contextual information appears in a bottom sheet. On Home, a useful map preview is the largest visual block and opens directly into Map.

Default map treatment is a low-clutter road map with quiet land and water colors, reduced POI density, legible street labels, and strong contrast for user-selected geometry and the current position. The current position includes an accuracy cue where useful and a timestamp in its detail sheet. Markers use distinct shapes for saved places, visits and network observations. Neighbourhood polygons use a light translucent fill and a clear boundary line. Selected polygons remain distinguishable from route lines.

Routes are not differentiated by color alone: **planned route** uses a continuous solid stroke and “Planned” label; **recorded route** uses a broken/dashed stroke and “Recorded” label. Both are listed in a legend whenever shown together. The location dot, selected destination and route line must remain visible against light and dark basemaps.

The Google Maps Android SDK supports displaying map data and interactive markers, polygons and overlays. Google cloud-based styling supports color and visibility controls for multiple map types and modes, but requires Google Cloud styling and a map ID; therefore the style is a validated design direction, not a configured service in this deliverable.[4] The prototype's map is a schematic illustration, not a live Google map. Google Maps' Android SDK is a map display/interaction SDK; route computation is treated as a separate provider/service decision, not inferred from drawing a line on a map.[5]

### Light and dark themes

Light mode uses warm paper for app surfaces and the approved low-clutter map palette. Dark mode uses deep evergreen-charcoal surfaces and a separate dark map style, not a simple inversion. Data-status colors are adjusted for contrast. The theme follows Android's system setting by default and may be overridden in Profile settings. The first implementation must support system font scaling and reduce or remove nonessential animation under reduced-motion settings.

## Navigation model

The five persistent destinations are:

1. **Home** — today's work, session state, intelligence preview and direct actions.
2. **Map** — location, place search, neighbourhoods, network observations and route setup.
3. **Activity** — session and visit history, including local/pending-sync status.
4. **Analytics** — personal, evidence-qualified summaries.
5. **Profile** — account, privacy, location, sync and display preferences.

The quick-action control is present on Home and Map and accessible from other tab roots without obscuring primary content. It opens an anchored menu or bottom sheet with session-sensitive actions. During a session, **Resume session** or **Open session** replaces **Start session**. **Record visit** launches the visit form with the active session preselected, if any. **Locate me** recentres Map and must not imply continuous tracking by itself.

Secondary navigation uses back navigation to the prior context. Search results open a place or neighbourhood detail. Selecting a place can pass it to Route. Route's primary action starts an explicit field session or returns to an already active session. A neighbourhood detail opened from Map retains a clear “Back to map” path.

## Screen-by-screen specification

Every prototype value is demo-only. Empty and failure states below are part of V1 design, even when the prototype offers only a representative example.

### 1. Splash

**Layout and content:** Full-screen warm paper or dark surface with the temporary text-only FIELD EYE name, a “Logo pending” note in review builds, and a brief progress cue. Do not show a fabricated logo, map screenshot or feature claim.

**Interaction and navigation:** No user decision is required. Continue to onboarding when signed out or Home when the app has a valid session. The splash must not wait on network synchronization or request permissions.

**States:** Loading is brief and non-blocking. If startup cannot restore an account, route to Login with an actionable error. If no network is available, do not imply local records were lost.

**Permissions:** None.

### 2. Onboarding

**Layout and content:** A short, skippable, 2–3 step introduction explains (1) map-based field planning, (2) local session/visit capture and later synchronization, and (3) when tracking is active and what location information is saved. Use a real, approved product screenshot only when one exists; the prototype uses diagrammatic placeholder content.

**Interaction and navigation:** Primary action advances, secondary action skips to account entry. A privacy link is available before registration. Location is explained here but the Android permission dialog is deferred until a location-dependent action is chosen. Finish leads to Registration or Login.

**States:** Onboarding progress is local and can resume. If the user exits, no account or location record is created. Avoid claims of offline base-map access.

**Permissions:** No system location prompt at onboarding. Android distinguishes foreground/background access and approximate/precise accuracy; the app should request the least access needed at the moment it is needed.[1]

### 3. Registration

**Layout and content:** Compact account form with email, password, password confirmation if required by the chosen auth flow, terms/privacy links and a clear “Create account” primary action. A link switches to Login. No social sign-in provider is assumed.

**Interaction and navigation:** Validate field format inline without clearing user entries. Show password visibility toggle with accessible label. On success, show a verification-required state only if the approved Supabase Auth configuration requires it, then route to onboarding completion or Login. “Already have an account?” goes to Login.

**States:** Idle, submitting, invalid fields, account already exists, network error, confirmation pending, success. Errors use plain language and recovery action. The app must not claim registration works offline.

**Permissions:** None.

### 4. Login and recovery

**Layout and content:** Email and password, password visibility toggle, “Forgot password?”, “Sign in”, and a route back to Registration. Keep optional auth methods out until approved and supported.

**Interaction and navigation:** Preserve typed email on failure, show explicit submitting state, and offer retry. Successful sign-in opens Home. Password reset uses an in-app email-entry step and confirmation state.

**States:** Invalid credentials, unverified account if applicable, offline/network failure, throttling, session restoration, and success. Never present local/offline access as authenticated server access; locally stored records remain subject to their approved privacy/retention model.

**Permissions:** None.

### 5. Location education and permission handoff

**Layout and content:** A contextual explanation appears when the user first taps Locate me, opens a location-dependent map feature, plans from current position, or starts tracking. It states why the requested accuracy is useful, whether tracking is one-time or during an active session, and how to proceed without granting it.

**Interaction and navigation:** Offer **Continue** to the OS permission request and **Not now** to the prior screen. If approximate access is sufficient for a basic view, continue with a visible lower-precision state; ask for precise access only when a feature materially needs it. A denied permission yields a non-blocking map/list alternative and a link to system settings only after the user chooses.

**Tracking-specific treatment:** Before a user starts an active route-recording session, explain that recording remains active while the session is running. If the product requires tracking while the app is minimized, the implementation should use an appropriate, user-started location foreground service with a persistent notification and current Android requirements. Android 14 targets must declare the appropriate service type and permission; service startup has runtime prerequisites.[1][2] Background location is a separate decision, not an onboarding default. Google Play requires clear disclosure and review documentation when background location permission is used.[3]

**States:** Not asked, approximate granted, precise granted, denied, permanently denied, system location disabled, permission changed in Settings, tracking unavailable. Permission copy and implementation need revalidation against the target Android version and Play policy at implementation/release time.

### 6. Home dashboard

**Layout and content:** Greeting and date; small online/offline and sync-status indicators; prominent map preview; current session banner; one compact row of relevant daily totals; recent activity; and a persistent quick-action entry point. The map preview and session state outrank decorative statistics. No score, opportunity ranking or total is shown without valid source data and a defined calculation.

**Information display:** Session state is Idle, Recording, Paused, or Pending sync. Each total distinguishes measured values from derived summaries. A “Today” distance derived from GPS samples is labeled as recorded/calculated, not as a route-provider estimate. Values show an empty prompt rather than zero when there is no data.

**Interactions:** Tap map preview to Map; tap current session to Active session; quick actions start a session, record a visit, or locate me; recent activity opens Activity. Sync status opens an explanatory queue view or Activity filter.

**States:** First-use empty dashboard guides the user to Map or Start session; location unavailable; offline with local records; syncing; sync failure with retry; active session; and normal populated view.

**Permissions:** No prompt merely to view Home. Location is requested only after a location-dependent action.

### 7. Intelligence Map

**Layout and content:** Map canvas fills most of the screen. A top search entry, compact layer/filter controls, locate control, and session-status affordance remain reachable. A bottom sheet presents selected place, neighbourhood, route or observation. The map legend explains observed points, estimated overlays, neighbourhood boundaries, planned routes and recorded routes.

**Interactions:** Pan/zoom; choose a map selection; open place search; toggle available layers; centre on user location; open neighbourhood detail; choose a place as route destination; and enter a session. Layer availability reflects actual data: unavailable layers are disabled or explain that no source data is available. The app never depicts a heatmap as provider-wide coverage based on sparse observations.

**States:** Map loading, tiles unavailable, location pending, approximate location, precise location, permission denied, empty data, selected object, stale observations, offline map canvas unavailable, network error and retry. Previously synchronized records may still be shown if local cache and provider terms permit; offline map tiles are not promised.

**Permissions and dependencies:** Foreground location for current position. Persistent active tracking requires the approved session behavior described above. Google Maps styling depends on map ID/cloud configuration.[4] Map usage, cost, attribution and offline terms must be reviewed before implementation.

### 8. Place search and filters

**Layout and content:** Search field over a result list, filter button with active-filter count, optional map/list switch, and result rows. Each row shows name, type/category if supplied, distance from a known user location only when available, source and freshness/confidence. Saved places are clearly distinguished from external search results.

**Interactions:** Enter a query; apply supported category filters; save or unsave a place; open detail; set as route destination; clear filters. Search suggestions are not fabricated. Search provider and available categories are subject to data-provider approval.

**States:** Initial prompt; matching results; no results; searching; permission/location unavailable (distance omitted); provider unavailable; offline with saved places only; stale external results; and error/retry.

**Permissions:** Location is optional for text search. Request location only when using nearby/radius functions or distance-from-current-position features.

### 9. Neighbourhood information

**Layout and content:** A compact detail sheet or full-screen detail reached from a selected boundary. It presents neighbourhood name, boundary/source metadata where available, freshness, coverage/data-quality note, and personal activity within that area only where the user has records. Each metric includes a “what this means” explanation or source.

**Interaction and navigation:** Close/back to map, review related places, optionally set destination or route origin, and inspect personal visits. A future opportunity assessment may appear only when a scoring method, source data and confidence are approved; no speculative rating is used in V1 UI.

**States:** Boundary unavailable; no description/source; no recorded activity; stale or low-confidence dataset; loading; and data-source failure. Missing fields are explicitly omitted or marked unavailable.

**Permissions:** None for static data or personal synced records. Current-location overlays require foreground location.

**Approval dependency:** No neighbourhood boundary dataset, source, freshness contract or licensing has been identified. The UI must not suggest authoritative coverage until one is selected.

### 10. Route intelligence

**Layout and content:** Map preview with origin/destination; travel-mode control only for modes verified by the approved service; route summary card showing distance and estimated duration; route alternatives only when actually returned; a route legend; and a primary **Start field session** action. Planned route is continuous; recorded track is dashed.

**Interactions:** Choose origin and destination; inspect the available route; compare returned alternatives; return to Map; and begin an explicit session. Do not label one option “best” without a defined objective. A running session makes the session action open/resume rather than create a duplicate.

**States:** Missing origin/destination; current location not granted; computing; route unavailable; no alternatives; network failure; stale estimate; selected alternative; and start-session confirmation. Estimate age and provider are visible where available.

**Data distinction:** Route provider distance/duration is an estimate. A subsequently recorded GPS track is a separate observed source with accuracy gaps; actual route may differ from planned route. Do not convert estimates into actuals.

**Dependencies:** Google Routes API can return up to three alternatives in addition to a default route, but alternatives are optional, may be absent, and are only available under documented request constraints, including no intermediate waypoints.[5] Pricing, data flow, travel modes, the appropriate Maps/Routes/Navigation products, and whether requests go through a trusted server path all require technical approval. The visual design does not promise turn-by-turn navigation.

### 11. Active field session

**Layout and content:** Clear live state label; elapsed timer; route/map preview; GPS quality/status and last sample time; measured distance with calculation/source label; visit count; sync indicator; and large **Pause/Resume**, **Record visit**, and **End session** controls. A session remains visibly active across app navigation. An operating-system notification is required if the approved design tracks while app is backgrounded using a location foreground service.

**Interactions:** Start from an explicit action; pause/resume capture; record a visit with session preselected; review a data-quality warning; end through a confirmation sheet; and revisit summary. Stop means location capture stops, with an explicit indication. No automatic session starts.

**States:** Starting, active with fresh location, active with degraded/stale GPS, paused, no permission, device location disabled, offline/local save, sync queued, sync failed, ending, and completed. Background tracking state is visible and system-notification affordance is explained.

**Permissions:** Foreground location for active tracking; optional background behavior only after technical and policy review. The runtime request should occur before starting the location service as required by current Android service rules.[1][2]

### 12. Visit recording

**Layout and content:** A short form optimized for standing use: selected place (optional), captured location and accuracy/time (if granted), visit start/end or recorded-at time as relevant, user-entered result/outcome, notes, and optional attachment entry. Keep the **Save visit** action sticky and reachable. An active session is linked automatically only with clear disclosure.

**Interactions:** Use current location or proceed without it; select an existing place; write notes; optionally attach a photo when the feature is approved; save locally immediately; edit unsynced records; and discard with confirmation. Saving is never contingent on network availability.

**States:** New blank form; location unavailable; no session; local saved/pending sync; syncing; sync failure; validation; and attachment permission/size failure if attachments are approved. Show record timestamp separately from the time the server receives it.

**Permissions:** Location only for geotagging. Camera/photos permission is not requested until an attachment action exists and is explicitly invoked; attachment requirements and storage policy remain implementation decisions.

**Open question:** The canonical V1 visit outcome vocabulary and whether visits have a duration/start-end interval need owner approval. The prototype uses neutral optional fields rather than imposing unapproved categories.

### 13. Activity

**Layout and content:** Chronological personal sessions and visits, with date grouping, status, duration/distance where available, local/pending/synced state and small location context. Search/sort/filter affordances should remain minimal until activity volume and user need are known.

**Interactions:** Open a session summary or visit; resume an active session; edit a locally pending record; retry synchronization; and jump to its map location when available.

**States:** First-use empty prompt; loading; populated timeline; no filter matches; offline records; partial sync; conflict requiring user review; and retry error. Avoid presenting an empty backend response as a zero-activity claim if sync is incomplete.

**Permissions:** None for stored records. Current-location actions remain subject to location permission.

### 14. Network intelligence

**Layout and content:** Top status for current connection type/provider where available; explicit **Measure now** action for active tests; latest supported readings; measurement timestamp; captured GPS accuracy; and history/map observations. Each metric such as signal strength, download, upload, latency or packet loss has its own observed/estimated/stale/unavailable label and unit. Unknown signal metric units must not be guessed.

**Interactions:** Read available OS-exposed connection measurements; opt in to a speed test; see estimated mobile-data and battery implications before a test; open historical observation; and review captured location/time. Historical map pins show measured observations only; an interpolation, if later approved, has a separate “Estimated coverage” legend and confidence treatment.

**States:** Not measured; unsupported by OS/carrier; permission or platform restriction; test in progress; cancelled; low connectivity; stale reading; sample too sparse; and data source error. Missing speed values remain unavailable, not 0 Mbps. Small sample sizes never imply complete provider coverage.

**Permissions and dependencies:** Network state and telephony information available to the app vary by Android version, carrier, device and approved APIs. A speed test is optional and may consume mobile data/battery. Required permissions, privacy disclosures, measurement provider, test limits and freshness rules need technical review. The timestamp and GPS accuracy refinements are mandatory whenever those values are available.

### 15. Analytics

**Layout and content:** A time-range selector and a small set of useful personal measures: field time, recorded distance, sessions, visits, and available comparisons. A low-density chart or timeline is preferred over a decorative KPI wall. Each derived metric exposes its time window and calculation definition. Comparisons by neighbourhood are shown only when data volume and method support a meaningful comparison.

**Interactions:** Change date range; inspect a metric's source/calculation note; open related activity; and filter by a recorded period. Time and distance calculations are deterministic and must not be delegated to AI.

**States:** No records; partial local/sync data; insufficient samples for a comparison; loading; error; and normal populated state. A zero count is used only when the data is complete for the selected range.

**Permissions:** None to view historical analytics. Live location is not silently captured from Analytics.

### 16. Profile and settings

**Layout and content:** Account identity; light/dark/system theme; map preference if available; location and tracking controls; measurement preferences; synchronization status; privacy policy; export/delete-account entry points; and sign-out. Keep sensitive actions separated from routine preferences.

**Interactions:** Change appearance and sampling preferences; review permissions and notification status; open OS settings; initiate data export or deletion through approved flows; sign out with unsynced-data warning. Provide confirmation for account deletion and explain unsynchronized local records before sign-out.

**States:** Profile loading; account error; offline settings; permission revoked; pending synchronization warning; export pending; deletion confirmation/in-progress/error; and signed out.

**Permissions:** No permissions on entry. Deep links to system settings are user-initiated.

**Open questions:** Data retention, export format, deletion cascade, unsynced-record behavior, and exact location-sampling controls require implementation/privacy approval.

## Main end-to-end user journey

1. **Registration:** User opens FIELD EYE, reviews the short product/privacy introduction, creates an individual account, and signs in. Authentication provider details and verification behavior depend on the approved Supabase Auth configuration.
2. **First location-dependent action:** From Home or Map, the user taps Locate me or chooses a feature that needs current location. The app explains purpose and timing, asks for foreground access, and continues with a visibly approximate/degraded state if only approximate access is allowed.[1]
3. **Dashboard:** Home shows session state, sync state, a map preview and meaningful personal activity. Empty data remains an invitation to begin, not a fabricated metric.
4. **Map and search:** User opens Map, searches a place or views available neighbourhood boundaries, and inspects source/freshness before relying on data.
5. **Choose destination:** User opens a result or neighbourhood detail and selects a destination. If no route data source is available, the UI says so; it does not draw a fake route.
6. **View route:** Route Intelligence presents provider-computed distance and duration as estimates, available alternatives only when returned, and planned route styling. The route service, mode and cost have been approved before production integration.
7. **Start a session:** User taps Start field session. The app explains that location sampling is active, checks permissions and system location, then shows the active state. If background continuation is part of the approved use case, a compliant location foreground service and persistent system notification provide transparency; background location is not pre-granted by onboarding.[1][2][3]
8. **Record visits:** From Active session or the quick action, user captures an optional geotagged visit, outcome and notes. The record is saved locally first and queued for synchronization when offline.
9. **End session:** User chooses End session and confirms. Capture stops. The review screen distinguishes recorded GPS-track summaries from the earlier route estimate and makes pending synchronization visible.
10. **Review analytics:** User opens Activity or Analytics to review session and visit history, with data completeness, timestamps and calculations identified. No recommendation is shown unless its method and evidence have been approved.

At every step, back navigation preserves form/map context where safe. A denied permission or failed network request offers a viable fallback instead of dead-ending the core visit-recording workflow.

## Reusable component inventory

- **App shell:** five-tab bottom navigation, system inset handling, page title, back action and optional online/sync indicator.
- **Quick field action:** Start/Resume session, Record visit and Locate me; context-aware availability and large touch targets.
- **Session status banner:** Idle/Active/Paused/Completed state, elapsed time where relevant, recording indicator and sync state.
- **Map canvas and legend:** map provider surface, location marker, place marker, neighbourhood polygon, observed network marker, route strokes and layer controls.
- **Evidence badge:** Observed, Estimated, Stale or Unavailable text plus accessible shape/color treatment.
- **Measurement row:** value, unit, source, timestamp, location accuracy and freshness.
- **Location quality indicator:** approximate/precise access state, reported accuracy, latest sample time, and permission/disabled fallback.
- **Route summary:** provider, planned label, estimated distance and duration, alternatives if returned, selection and data age.
- **Neighbourhood/place sheet:** source and freshness, available personal activity, save/destination actions.
- **Search field and filter sheet:** query, loading, selected-filter count, clear and zero-results states.
- **Visit form:** place association, optional location, timestamp, outcome, note, optional attachment and local-save state.
- **Sync status row/banner:** Local only, queued, syncing, synced, retry required and conflict review; never claims success before confirmation.
- **Activity timeline row:** event type/time, session linkage, record completeness and sync badge.
- **Analytics metric card/chart:** deterministic definition, date range, source and sample/data-quality note.
- **Permission education panel:** feature-specific rationale, Continue, Not now, approximate/precise consequence and settings recovery.
- **Empty, loading, offline, stale and error treatments:** concise explanation, user action and no fabricated placeholder measurements.
- **Confirmation sheets:** end session, discard unsaved visit, sign out with pending records and account deletion.

## Accessibility and outdoor-use requirements

Use Android font scaling and layout reflow rather than clipping. Keep contrast high under bright outdoor viewing conditions. Important actions should be reachable at the lower half of the screen and remain at least 48 dp in touch dimension. Do not make the map the only route to a record; offer a list/detail alternative. Label controls, map features and charts for TalkBack. Provide text or shape differences in addition to color. Avoid long-press-only interactions, tiny drag handles and motion that cannot be reduced. On a route or session screen, status should remain understandable at a glance without relying on a rapid animation.

The prototype is an interaction aid, not a physical outdoor usability test. Glare, glove use, font scaling, device widths and reachability still need review on actual Android devices before implementation approval.

## Data, privacy and service assumptions

- All V1 records belong to the individual signed-in user, matching the approved personal-account model. No team, supervisor, shared dashboard or organization UI is introduced.
- Room/local-first session and visit capture is represented in the UX; synchronization remains visible, retryable and idempotent in the eventual implementation. No server tables or policies are created by this specification.
- Location permissions are contextual. One-time/foreground use is distinct from continuous session tracking. Current Android guidance defines foreground location access and approximate/precise accuracy separately; requests must be justified by the feature.[1]
- If tracking continues while minimized, the user sees a persistent system notification for an active location foreground service. Android 14 target behavior has location service type/permission and runtime prerequisites.[2] Whether FIELD EYE needs ACCESS_BACKGROUND_LOCATION remains a separate product/policy decision, with Play disclosure/review implications.[3]
- Google Maps SDK supports map display, markers, polygons and overlays. Cloud styling requires a configured map ID and Google Cloud setup; use, billing, attribution and data terms need review.[4][6]
- Route calculation and navigation are separate service decisions. Google Routes API alternatives are not guaranteed for every request.[5]
- No full offline map tiles, offline routing, speed-test vendor, proprietary neighbourhood dataset, opportunity score, AI recommendation or background-location behavior is promised.
- Precise coordinates, notes, photos, network identifiers and measurement history are sensitive. Actual retention, export, deletion and upload behavior must align with the separate security/privacy design and approved database policies.

## Prototype usage and limits

Open [FIELD EYE V1 interactive prototype](../prototype/FIELD_EYE_UI_UX_PROTOTYPE_V1.html) in a modern browser. Use the reviewer-only screen switcher to jump to any screen, the in-app bottom navigation to explore the five tabs, and the theme and state controls to inspect variations. Prototype controls that merely simulate permission, synchronization, a route response, or a network measurement are labeled as demonstrations. No credentials, map SDK, server, API request, real location or persistence are used. Measurements and places shown are demonstration-only; they are not product claims or research data.

## Decisions requiring approval before implementation

### Product and UX

1. Confirm the proposed token values and the text-only identity treatment pending official logo assets.
2. Decide whether a session must continue recording after the app is minimized or the screen is locked. This determines foreground-service behavior, notification copy and whether any background permission is justified.
3. Decide when precise location is essential and whether approximate location can support all non-tracking map/search actions.
4. Approve the canonical visit outcome vocabulary and whether visit timing is a timestamp or a start/end interval.
5. Define stale thresholds separately for GPS, route estimates, network measurements, saved-place data and neighbourhood datasets.
6. Decide the minimum-data rules for neighbourhood comparisons and whether any opportunity assessment appears in V1. No scoring UI should ship without a deterministic scoring method, factors, confidence and provenance.
7. Confirm if optional photo attachments are in the V1 visit flow and their capture/import and retention expectations.

### Technical and data dependencies

8. Select a licensed neighbourhood boundary and place-data source, and approve attribution, refresh cadence and coverage disclosures.
9. Select route computation/navigation products, travel modes, request path, estimate refresh behavior and provider-cost controls. Confirm whether alternate routes are in V1.
10. Approve Google Maps Platform setup, map ID ownership, map styles, billing controls, attribution and privacy review. Confirm if a map-list fallback is required when tiles cannot load.
11. Confirm the supported network measurements, Android/carrier/device limitations, any speed-test provider, measurement units, opt-in copy, mobile-data limits and battery policy.
12. Define offline expectations: which previously synchronized places/boundaries may be cached, how long they remain, and whether user-facing “offline map” wording is prohibited in V1.
13. Set local-data retention and sign-out behavior for unsynchronized personal records, plus export/deletion flows and attachment retention.
14. Approve authentication details (email verification, recovery and any additional sign-in method) without presuming an unselected provider.

These are review questions, not hidden design decisions. If unanswered, affected features should remain unavailable, clearly labeled as pending, or be omitted from implementation.

## Potential risks and mitigations

- **Location-permission friction or rejection:** ask only in context; begin with foreground needs; retain an approximate-mode fallback; and validate any background use against target Android and Play policies before release.[1][2][3]
- **False confidence in sparse data:** display source, timestamp, accuracy and sample context; distinguish observed points from any future estimate; and show unavailable states honestly.
- **Map/route provider cost and policy exposure:** treat live mapping, routes and any speed testing as external dependencies with billing, key restriction, attribution and privacy review before integration.[4][5][6]
- **Offline expectation mismatch:** distinguish offline record capture from offline basemap/routing capability. Do not promise cached tiles until provider terms and technical feasibility are verified.
- **Battery and data consumption:** user-started session, adaptive sampling, visible tracking state, optional speed tests, and explicit stop/pause behavior.
- **Unclear calculations:** display the source and deterministic definition for distance/time; keep route provider estimates separate from device-recorded samples.
- **Brand identity still pending:** prototype uses only temporary text and proposed tokens. Replace neither with a logo nor final brand system without supplied/approved assets.
- **Prototype fidelity limit:** schematic map, illustrative data, and desktop-browser interactions do not validate native Compose, GPS, policy, accessibility on device, or visual behavior outdoors.

## Recommended next milestone after approval

1. Review and approve this specification, the prototype, the open product decisions and provider dependencies.
2. Resolve the highest-impact items first: session behavior in background, location accuracy expectations, neighbourhood/place sources, routing provider and offline promise.
3. Produce an implementation-ready acceptance checklist and an approved data/source semantics note for observed, estimated, stale and unavailable states.
4. Only after the project owner approves M1 completion, plan the separate database/security and Android milestones. No implementation is authorized by this design document alone.

## References

[1]: https://developer.android.com/develop/sensors-and-location/location/permissions "Request location permissions | Android Developers"  
[2]: https://developer.android.com/about/versions/14/changes/fgs-types-required "Foreground service types are required | Android Developers"  
[3]: https://support.google.com/googleplay/android-developer/answer/9799150?hl=en "Background location permissions policy | Google Play Console Help"  
[4]: https://developers.google.com/maps/documentation/android-sdk/cloud-customization "Cloud-based maps styling overview | Maps SDK for Android"  
[5]: https://developers.google.com/maps/documentation/routes/alternative-routes "Get alternative routes | Routes API"  
[6]: https://developers.google.com/maps/documentation/android-sdk/overview "Maps SDK for Android overview"