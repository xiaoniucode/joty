package cn.xilio.leopard.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;

@Configuration
public class MultiLevelCacheInterceptor extends CacheInterceptor {

    @Autowired
    private CacheManager caffeineCacheManager;

    @Override
    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
        // 获取缓存值
        Cache.ValueWrapper result = super.doGet(cache, key);
        if (result == null) {
            return null;
        }

        // 如果从 Redis 获取，则回填到 Caffeine
        if (cache instanceof RedisCache) {
            Cache caffeineCache = caffeineCacheManager.getCache(cache.getName());
            if (caffeineCache != null) {
                caffeineCache.putIfAbsent(key, result.get());
            }
        }
        return result;
    }
}
