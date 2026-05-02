## 2025-05-15 - Resource leaks in JSON stream operations
**Vulnerability:** Resource exhaustion (DoS) via unclosed Input/Output streams when using kotlinx.serialization.
**Learning:** Contrary to some other serialization libraries, kotlinx.serialization's `decodeFromStream` and `encodeToStream` do not manage the lifecycle of the provided stream. Several providers in IdeaVim were found to be leaking file and resource handles.
**Prevention:** Always wrap streams in Kotlin's `.use { ... }` block to ensure they are closed regardless of success or failure.
