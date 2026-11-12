# FUSE: FIELD EYE

**Project status: PRE-DEVELOPMENT — UI/UX REVIEW**

FIELD EYE is a proposed native Android field-intelligence application for independent field agents. The project is at the pre-development review stage. This repository contains the approved V1 UI/UX review material; it is not an Android application or deployed service.

## Available review materials

- [Product brief](docs/product-brief.md)
- [UI/UX Design Specification V1](docs/ui-ux-spec-v1.md)
- [Interactive HTML prototype](prototype/FIELD_EYE_UI_UX_PROTOTYPE_V1.html)
- [Architecture status](docs/architecture.md) — approved stack recorded; technical architecture intentionally deferred.
- [Decisions and open approvals](docs/decisions.md)
- [Review log](docs/review-log.md)

The `android/` and `supabase/` directories are reserved for later work and contain no application implementation, schema, or deployment artifacts.

## Review status and boundaries

The UI/UX specification and prototype are supplied for independent review and project-owner approval. No Android development, production database changes, Supabase deployment, live map integration, credential use, or paid API calls are part of this milestone. Do not begin technical architecture or implementation until the independent review and project-owner approval are complete.

The prototype is a self-contained browser-based interaction aid. It uses schematic map artwork and demo-only controls; it has no live account, location, map, network measurement, backend, or persistence. See the specification for its limitations and design decisions pending approval.

## Repository provenance

The existing public repository was empty when inspected on 12 November 2026: it had no files, branches, or commits. This initial commit adds the review materials and status scaffolding without replacing prior repository work.
