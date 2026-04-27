## 2025-05-14 - Resource Leaks in kotlinx.serialization
**Vulnerability:** Resource leaks (file descriptors) when using `Json.decodeFromStream` and `Json.encodeToStream`.
**Learning:** These functions do not automatically close the underlying `InputStream` or `OutputStream` provided to them.
**Prevention:** Always wrap the stream in a `.use { ... }` block to ensure it is closed after the serialization operation is complete.
