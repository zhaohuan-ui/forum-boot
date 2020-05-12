package com.forum.common.utils.redis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import static org.apache.logging.log4j.MarkerManager.exists;
import static sun.plugin2.util.PojoUtil.toJson;

/**
 * RedisUtils
 *
 * @author yubo
 * @date 2020/3/31 11:40
 * @Version 1.0.0
 * @MOdified By
 */
@Component
public class RedisUtils {

    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired(required = false)
    private ValueOperations<String, String> valueOperations;
    Gson gson = new Gson();

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 7200;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    public void set(String key, Object value, long expire) {
        try {
            valueOperations.set(key, toJson(value));
        } catch (Exception e0) {
            System.out.println(e0.getMessage());
        }
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        try {
            valueOperations.set(key, toJson(value));
        } catch (Exception e0) {
            System.out.println(e0.getMessage());
        }
    }

    public <T> T get(String key, Class<T> clazz, long expire) {

        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : gson.fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

}
