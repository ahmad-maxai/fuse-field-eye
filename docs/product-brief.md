# FIELD EYE — Product Brief

**Status:** Pre-development; UI/UX review. This brief summarizes the project owner's supplied master instruction and does not approve implementation.

## Product vision

FIELD EYE is a field-operations and geospatial decision-support application for independent field agents. It is intended to help an agent determine where to visit, which neighbourhoods may offer opportunities, which route to follow, how far and how long a journey may take, where useful places and landmarks are, how connectivity varies across locations, and how recorded activity can inform personal productivity.

FIELD EYE is a field-intelligence platform, not simply a map application.

## V1 user and account model

V1 is designed for individual agents. Each user has an individual account and private personal records, field sessions, location history, saved places, visits, routes, and personal analytics. Organizations, teams, and supervisor accounts are not required in V1; future extensibility should not introduce unnecessary team complexity now.

## Intended product modules

The eventual product may include authentication and profiles; location intelligence and interactive maps; neighbourhood visualization; place search and saved locations; route planning and actual route tracking; field sessions and visit recording; time and distance analytics; network-connectivity intelligence; opportunity scoring; intelligent search and recommendations; and personal analytics. These modules are not to be implemented simultaneously.

## Approved technology direction

The supplied project instruction specifies native Android with Kotlin, Jetpack Compose, MVVM, Room, WorkManager, and Google Play services Location APIs. The backend direction is Supabase with PostgreSQL, PostGIS, Supabase Auth and Storage, Realtime only where necessary, and Edge Functions for trusted server-side operations. Google Maps Platform and Google AI/Gemini are named external-service directions for selective future use. No replacement or additional service is approved here.

## Product and engineering constraints

- Preserve GPS timestamps and accuracy; use adaptive sampling and user-controlled tracking; minimize battery use and visibly indicate active tracking.
- Support offline session and visit recording, queued/retried synchronization, stable UUIDs, and duplicate-safe sync.
- Keep raw measurements distinct from calculated results; do not fabricate missing information. Use deterministic calculations for distance, duration, and any approved scoring.
- Enforce user ownership and appropriate access controls. Never embed service-role or secret keys in Android.
- Respect Android location permissions and background-location policy. Request permissions contextually and explain tracking.
- Work in approved milestones, document changes, test relevant behavior, and obtain project-owner approval before technical architecture or implementation.

## Current milestone

Prepare and review the V1 UI/UX design specification and its interaction prototype. This repository does not authorize Android development, production database work, infrastructure deployment, or paid API use. See [UI/UX Design Specification V1](ui-ux-spec-v1.md) for the screens, user journey, design system, assumptions, risks, and approval questions.
