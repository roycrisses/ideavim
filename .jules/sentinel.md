## 2026-04-19 - Resource Leaks in JSON Serialization
**Vulnerability:** Resource leaks (file descriptors) when using `kotlinx.serialization`'s `decodeFromStream` and `encodeToStream`.
**Learning:** Unlike some other serialization libraries, these methods do not automatically close the underlying `InputStream` or `OutputStream`.
**Prevention:** Always wrap the stream in a `.use { }` block when calling these methods to ensure resources are released.

## 2026-04-19 - Information Leakage via printStackTrace()
**Vulnerability:** Use of `e.printStackTrace()` leaks internal implementation details to standard output/error.
**Learning:** `printStackTrace()` bypasses the configured logging system and can expose sensitive stack trace information.
**Prevention:** Use `vimLogger<T>()` and call `logger.error("message", exception)` to handle exceptions securely through the project's logging infrastructure.
