# Chirp — KMP Learning Project

## Goal
This project is for deepening KMP skills by following a video course. The priority is **understanding every change**, not just applying it. Never commit code the user hasn't reviewed and understood.

## My Role
- Apply code from tutorial branches accurately
- Explain what changed and why when asked
- Keep sessions productive without skipping learning
- **Never commit** — always leave changes staged for the user to review

## Workflow: Applying a Tutorial Branch

When the user provides a tutorial branch URL (e.g. `https://github.com/philipplackner/Chirp/tree/<branch>`):

1. Identify the branch name and infer the previous branch (sequential numbering, e.g. `4-theme-setup` follows `3-observing-one-time-events`)
2. Use `gh api` to diff only the changed files between the two branches
3. Fetch full file contents from the tutorial branch
4. Apply changes locally — create new files, update existing ones
5. Download binary assets (fonts, images) via `gh api` + base64 decode
6. Update `settings.gradle.kts` and module `build.gradle.kts` files if new modules are added
7. **Do not commit anything**

## Project Structure

```
Chirp/
├── build-logic/convention/     # Convention plugins (CmpApplication, KmpLibrary, CmpFeature, Room, etc.)
├── composeApp/                 # Compose Multiplatform app entry point (Android + iOS + Desktop)
├── core/
│   ├── domain/                 # Result<D,E>, base Error types — pure Kotlin, no platform deps
│   ├── data/                   # Network (Ktor), shared data utilities
│   ├── presentation/           # UiText, ObserveAsEvents — shared UI utilities
│   ├── designsystem/           # ChirpTheme, color palette, typography (PlusJakartaSans)
│   └── designcomponent/        # Reusable Compose components
└── feature/
    ├── auth/{domain,presentation}
    └── chat/{domain,data,database,presentation}
```

## Key Conventions

| Concern | Convention |
|---|---|
| Branch naming | `sp/<feature-name>` (user's branches), `project-wide-utility/N-name` (tutorial) |
| Error handling | `Result<D, E: Error>` sealed interface — never nullable returns |
| DI | Koin 4.x — modules registered per feature |
| Database | Room via `RoomConventionPlugin` with KSP across all targets |
| Build config secrets | `BuildKonfigConventionPlugin` reads from `local.properties` |
| Text abstraction | `UiText` sealed interface (dynamic string or `StringResource`) |
| One-time events | `ObserveAsEvents` — lifecycle-aware Flow collector |
| Extended theme colors | `MaterialTheme.colorScheme.extended` for colors beyond standard M3 slots |

## Architecture

Clean Architecture with feature modules:
- **Domain** — entities, use cases, `Result` wrapper (pure Kotlin)
- **Data** — repository impls, Ktor clients, Room DAOs
- **Presentation** — ViewModels (Jetbrains Lifecycle), Composable screens

## Rules

- Never auto-commit or push
- Never write code the user doesn't understand — explain on request
- Apply only the diff between the tutorial branch and its predecessor, not the full branch history
- CLAUDE.md itself should not be committed (it is in `.gitignore`)