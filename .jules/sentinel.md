# Sentinel Security Journal

## 2025-05-15 - Hardening JSON Provisioning and Resource Management
**Vulnerability:** Resource leaks (unclosed Input/OutputStreams) and information disclosure via `printStackTrace()`.
**Learning:** `Json.decodeFromStream` and `Json.encodeToStream` from `kotlinx.serialization` do not automatically close the underlying streams. Failing to close these can lead to resource exhaustion and file lock issues, especially in long-running applications like an IDE plugin. Additionally, using `printStackTrace()` in an IDE plugin can leak internal paths and stack details to the user's console/logs without proper management by the application's logging framework.
**Prevention:** Always wrap stream-based operations in `.use { ... }` blocks in Kotlin to ensure automatic resource closure. Use the project's `VimLogger` (via `vimLogger<T>()`) to report errors instead of `printStackTrace()` to maintain security and auditability.
