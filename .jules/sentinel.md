## 2025-05-15 - Resource Leaks in JSON Serialization
**Vulnerability:** Use of `kotlinx.serialization`'s `decodeFromStream` and `encodeToStream` without closing the underlying `InputStream` or `OutputStream`.
**Learning:** `kotlinx.serialization` intentionally does not close streams, leaving it to the caller. In a long-running IDE process, this leads to file descriptor exhaustion.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use {}` blocks when passing them to serialization methods.
