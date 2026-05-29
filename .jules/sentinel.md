## 2025-05-15 - Unclosed Streams in JSON Providers and System Command Execution
**Vulnerability:** Resource exhaustion (DoS) through file descriptor leaks and information disclosure via stack traces.
**Learning:** `Json.decodeFromStream` and `Json.encodeToStream` from `kotlinx.serialization` do not automatically close the underlying streams. Similarly, `CharStreams.toString` from Guava leaves the reader open.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use { ... }` blocks when performing one-off read/write operations. Use structured logging (`logger.error`) instead of `printStackTrace()` to avoid leaking internal details.
