## 2025-01-24 - Resource Leak Pattern in JSON Provisioning
**Vulnerability:** Resource leaks (unclosed Input/Output streams) when using `kotlinx.serialization` and Guava's `CharStreams`.
**Learning:** `Json.decodeFromStream`, `Json.encodeToStream`, and `CharStreams.toString(Readable)` do not automatically close the underlying streams. In IdeaVim's extension and command provisioning system, multiple providers were opening resource streams but failing to close them, potentially leading to file descriptor exhaustion.
**Prevention:** Always wrap `InputStream` and `OutputStream` in `.use { ... }` blocks when passing them to serialization or utility methods. Avoid `e.printStackTrace()` and use `VimLogger` to prevent information disclosure in logs.
