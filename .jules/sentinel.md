## 2025-05-14 - Resource leak in kotlinx.serialization JSON providers
**Vulnerability:** Resource leak (file descriptors) when using `Json.decodeFromStream` and `Json.encodeToStream`.
**Learning:** Unlike some other serialization libraries, `kotlinx.serialization` does not automatically close the underlying `InputStream` or `OutputStream` when decoding/encoding from/to a stream.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use { ... }` blocks when passing them to `Json.decodeFromStream` or `Json.encodeToStream` to ensure they are closed even if an exception occurs.
