# Sentinel's Security Journal

## 2025-05-15 - Resource Leaks in JSON Streaming
**Vulnerability:** Resource exhaustion via unclosed `InputStream` and `OutputStream` when using `kotlinx.serialization.json.decodeFromStream` and `encodeToStream`.
**Learning:** Unlike some other serialization libraries, `kotlinx.serialization`'s stream-based functions do not automatically close the underlying stream. This led to multiple file descriptor leaks in core command and extension providers.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use { ... }` blocks when passing them to `Json.decodeFromStream` or `Json.encodeToStream`. Use structured logging instead of `printStackTrace()` to avoid information leakage during resource-related failures.
