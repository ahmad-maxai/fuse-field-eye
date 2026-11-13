# FUSE: FIELD EYE

**Project status: PRE-DEVELOPMENT — TECHNICAL ARCHITECTURE REVIEW**

FIELD EYE is a proposed native Android field-intelligence application for independent field agents. The V1 UI/UX screenshots and prototype have been reviewed and approved by the project owner, according to the owner-provided architecture assignment. The V1 technical architecture is now submitted for review. This repository is not an Android application or deployed service.

## Review materials

- [Product brief](docs/product-brief.md)
- [UI/UX Design Specification V1](docs/ui-ux-spec-v1.md)
- [Interactive HTML prototype](prototype/FIELD_EYE_UI_UX_PROTOTYPE_V1.html)
- [Technical Architecture and Feasibility V1.1](docs/technical-architecture-v1.md) — draft for review; owner decisions, recommendations and unresolved choices are labelled separately.
- [Architecture status](docs/architecture.md)
- [Decisions and open approvals](docs/decisions.md)
- [Review log](docs/review-log.md)

The `android/` and `supabase/` directories are reserved for later work and contain no application implementation, schema, or deployment artifacts.

## Review status and boundaries

The UI/UX V1 has been approved for transition into technical architecture. The V1.1 architecture revision records the authoritative owner addendum on locked-screen session recording, multi-device accounts and V1 attachments; opportunity scoring remains explicitly undecided. The architecture is still a review draft and does not approve Android development, database migrations, Supabase project/resource changes, Google Cloud configuration, deployment, credential creation, or paid API use. Those steps require independent review and separate project-owner approval.

The prototype remains a self-contained browser-based review artifact. It uses schematic map artwork and demo-only controls; it has no live account, location, map, network measurement, backend, or persistence. See the specification and architecture draft for assumptions and open decisions.

## Repository provenance

The existing public repository was empty when inspected on 12 November 2026: it had no files, branches, or commits. The initial commit added the UI/UX review materials without replacing prior repository work. Architecture work is maintained on a separate review branch and is not merged to `main`.
