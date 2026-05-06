## 2025-02-14 - Resource Leaks and Info Disclosure in JSON Provisioning
**Vulnerability:** Resource leaks (unclosed InputStreams) and information disclosure (printStackTrace) in JSON parsing logic.
**Learning:** kotlinx.serialization's `decodeFromStream` and `encodeToStream` do not automatically close the underlying streams. Furthermore, using `e.printStackTrace()` in production code can leak sensitive stack trace information.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use { ... }` blocks when passing them to serialization functions. Use the project's `VimLogger` for error reporting instead of `printStackTrace()`.
