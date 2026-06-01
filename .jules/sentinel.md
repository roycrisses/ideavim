# Sentinel Security Journal

## 2025-05-15 - Resource Leaks and Insecure Logging in JSON Provisioning
**Vulnerability:** Resource leaks (unclosed Input/OutputStreams) in JSON extension and command providers, and insecure logging using `printStackTrace()`.
**Learning:** `kotlinx.serialization`'s `Json.decodeFromStream` and `Json.encodeToStream` do not automatically close the underlying streams. Additionally, Guava's `CharStreams.toString(Readable)` does not close the `Readable` or the underlying stream.
**Prevention:** Always wrap `InputStream`, `OutputStream`, `Reader`, and `Writer` in `.use { ... }` blocks when using them with serialization or stream utility libraries. Use structured logging (`logger.error`) instead of `printStackTrace()` to prevent information leakage and ensure better observability.
