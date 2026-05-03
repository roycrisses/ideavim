## 2025-05-15 - Resource Leaks in JSON Stream Operations
**Vulnerability:** Multiple instances of `Json.decodeFromStream` and `Json.encodeToStream` were used without closing the underlying `InputStream` or `OutputStream`. This can lead to file descriptor exhaustion and potential Denial of Service (DoS).
**Learning:** `kotlinx.serialization`'s stream-based API does not automatically close the provided streams. In a multi-module project like IdeaVim, these patterns can propagate across different JSON providers (extensions, commands, functions).
**Prevention:** Always wrap stream-producing calls (like `getResourceAsStream` or `targetFile.outputStream()`) in Kotlin's `.use { ... }` block when passing them to serialization functions to ensure they are closed regardless of success or failure.
