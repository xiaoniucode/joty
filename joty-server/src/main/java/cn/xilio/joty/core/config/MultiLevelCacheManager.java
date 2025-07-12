package cn.xilio.joty.core.config;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class MultiLevelCacheManager implements CacheManager {
    @Autowired
    private Cache<String, Object> caffeineCache; // 本地缓存
    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // 远程缓存

    private static final long REDIS_TTL_MINUTES = 5; // Redis 缓存过期时间

    @Override
    public <T> T get(String key, Function<String, T> loader) {
        // 1. 查本地缓存 (Caffeine)
        T value = (T) caffeineCache.getIfPresent(key);
        if (value != null) {
            System.out.println("Hit Caffeine cache for key: " + key);
            return value;
        }

        // 2. 查远程缓存 (Redis)
        value = (T) redisTemplate.opsForValue().get(key);
        if (value != null) {
            System.out.println("Hit Redis cache for key: " + key);
            // 回填本地缓存
            caffeineCache.put(key, value);
            return value;
        }

        // 3. 缓存未命中，调用 loader 加载数据
        value = loader.apply(key);
        if (value != null) {
            System.out.println("Fetched from loader for key: " + key);
            // 存入 Redis 和 Caffeine
            put(key, value);
        }

        return value;
    }

    @Override
    public void put(String key, Object value) {
        // 存入本地缓存 (Caffeine)
        caffeineCache.put(key, value);
        // 存入远程缓存 (Redis)，设置过期时间
        redisTemplate.opsForValue().set(key, value, REDIS_TTL_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public void delete(String key) {
        // 清理本地缓存
        caffeineCache.invalidate(key);
        // 清理远程缓存
        redisTemplate.delete(key);
        System.out.println("Evicted cache for key: " + key);
    }

    @Override
    public <T> T getHash(String key, String hKey, Function<String, T> loader) {
        // 1. 构造 Caffeine 缓存的键（key:hKey）
        String cacheKey = key + ":" + hKey;
        T value = (T) caffeineCache.getIfPresent(cacheKey);
        if (value != null) {
            System.out.println("Hit Caffeine cache for key: " + cacheKey);
            return value;
        }

        // 2. 查远程缓存 (Redis Hash)
        value = (T) redisTemplate.opsForHash().get(key, hKey);
        if (value != null) {
            System.out.println("Hit Redis Hash cache for key: " + key + ", hKey: " + hKey);
            // 回填本地缓存
            caffeineCache.put(cacheKey, value);
            return value;
        }

        // 3. 缓存未命中，调用 loader 加载数据
        value = loader.apply(cacheKey);
        if (value != null) {
            System.out.println("Fetched from loader for key: " + cacheKey);
            putHash(key, hKey, value);
        }

        return value;
    }

    @Override
    public void putHash(String key, String hKey, Object value) {
        // 存入本地缓存 (Caffeine)
        String cacheKey = key + ":" + hKey;
        caffeineCache.put(cacheKey, value);
        // 存入远程缓存 (Redis Hash) todo 需要加入过期时间
        redisTemplate.opsForHash().put(key, hKey, value);
        // 设置 Hash 键的过期时间
        redisTemplate.expire(key, REDIS_TTL_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public void evictHash(String key, String hKey) {
        // 清理本地缓存
        String cacheKey = key + ":" + hKey;
        caffeineCache.invalidate(cacheKey);
        // 清理远程缓存 (Redis Hash 的指定 hKey)
        redisTemplate.opsForHash().delete(key, hKey);
        System.out.println("Evicted Hash cache for key: " + key + ", hKey: " + hKey);
    }
    @Override
    public void deleteHash(String key, String... hKeys) {
        if (hKeys == null || hKeys.length == 0) {
            return;
        }
        // 删除本地缓存 (Caffeine)
        for (String hKey : hKeys) {
            String cacheKey = key + ":" + hKey;
            caffeineCache.invalidate(cacheKey);
        }
        // 删除远程缓存 (Redis Hash 的多个 hKey)
        redisTemplate.opsForHash().delete(key, (Object[]) hKeys);
        System.out.println("Deleted Hash cache for key: " + key + ", hKeys: " + String.join(",", hKeys));
    }
}
