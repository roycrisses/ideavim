## 2025-05-14 - Resource Leak and Info Leakage in JSON Providers
**Vulnerability:** Resource leaks due to unclosed Input/Output streams when using kotlinx.serialization's decodeFromStream/encodeToStream, and information leakage via printStackTrace().
**Learning:** kotlinx.serialization does not automatically close streams. Combined with printStackTrace(), this can lead to DoS and internal data exposure.
**Prevention:** Always wrap Closeable resources in .use {} blocks and use VimLogger instead of printStackTrace().
