package cn.xilio.joty.core.config;

import java.util.function.Function;

public interface CacheManager {

    // 获取缓存，miss 时通过 loader 加载数据
    <T> T get(String key, Function<String, T> loader);

    // 存入缓存
    void put(String key, Object value);

    // 清理缓存
    void delete(String key);

    <T> T getHash(String key, String hKey, Function<String, T> loader);

    // 存入 Hash 缓存
    void putHash(String key, String hKey, Object value);

    // 清理 Hash 缓存（指定 hKey 或整个 key）
    void evictHash(String key, String hKey);

    // 删除 Hash 中指定的 hKey
    void deleteHash(String key, String... hKeys);
}
