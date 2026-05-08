# Sentinel Security Journal

## 2025-05-15 - Resource Leak Prevention in JSON Providers
**Vulnerability:** Multiple instances where `InputStream` and `OutputStream` were not closed after JSON serialization/deserialization with `kotlinx.serialization`. Also found usage of `printStackTrace()` which can leak sensitive stack trace information.
**Learning:** `Json.decodeFromStream` and `Json.encodeToStream` do not automatically close the underlying streams. Failing to close these can lead to file descriptor exhaustion.
**Prevention:** Always use the `.use { ... }` extension function on `Closeable` resources in Kotlin to ensure they are closed even if an exception occurs. Use `VimLogger` for error reporting instead of `printStackTrace()`.
