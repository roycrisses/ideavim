## 2025-05-15 - [Resource Leak in JSON Serialization]
**Vulnerability:** Resource leak (Potential DoS) due to unclosed Input/Output streams when using `kotlinx.serialization`.
**Learning:** `Json.decodeFromStream` and `Json.encodeToStream` do not automatically close the provided streams.
**Prevention:** Always wrap `InputStream` and `OutputStream` in a `.use {}` block when using `kotlinx.serialization` to ensure they are properly closed.

## 2025-05-15 - [Sensitive Information Disclosure]
**Vulnerability:** Leaking stack traces to console via `e.printStackTrace()`.
**Learning:** Stack traces can expose internal class names, paths, and logic that may aid an attacker.
**Prevention:** Use `VimLogger` (e.g., `logger.error("message", e)`) to log errors securely according to project configuration.
