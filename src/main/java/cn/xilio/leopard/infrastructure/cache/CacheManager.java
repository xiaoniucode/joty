package cn.xilio.leopard.infrastructure.cache;

import java.util.function.Function;

public interface CacheManager {

    // 获取缓存，miss 时通过 loader 加载数据
    <T> T get(String key, Function<String, T> loader);

    // 存入缓存
    void put(String key, Object value);

    // 清理缓存
    void evict(String key);
}
