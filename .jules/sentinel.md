## 2025-05-15 - Resource Leaks in JSON Stream Serialization
**Vulnerability:** Multiple instances of `InputStream` and `OutputStream` were not closed after being used by `Json.decodeFromStream` and `Json.encodeToStream` in `thinapi` and `vim-engine` modules.
**Learning:** In `kotlinx.serialization`, `decodeFromStream` and `encodeToStream` do not automatically close the underlying stream. In long-running applications like IntelliJ IDEA, this can lead to file descriptor exhaustion.
**Prevention:** Always wrap stream-based serialization calls in `.use { ... }` blocks to ensure resources are released. Additionally, avoid using `printStackTrace()` as it can leak sensitive information; prefer the established `VimLogger` via `logger.error("message", exception)`.
