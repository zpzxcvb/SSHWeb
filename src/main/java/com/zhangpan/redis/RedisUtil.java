package com.zhangpan.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Redis工具类
 * @author zhangpan
 * @date 2018年12月26日
 */
//@Component
public class RedisUtil {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 根据key获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    
    /**
     * 删除缓存
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    
    /**
     * 普通缓存放入
     * @param key
     * @param value
     * @return
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    /**
     * 将list放入缓存
     * @param key
     * @param value
     */
    public void set(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }
    
    /**
     * 将map放入缓存
     * @param key
     * @param value
     */
    public void set(String key, Map<String, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }
    
    /**
     * 将set放入缓存
     * @param key
     * @param value
     */
    public void set(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }
    
    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    
    /**
     * list获取
     * @param key
     * @return
     */
    public List<Object> lget(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }
    
    /**
     * map获取
     * @param key
     * @return
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    
    /**
     * 获取hashKey对应的所有键值
     * @param key
     * @return
     */
    public Map<Object, Object> mget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * set获取
     * @param key
     * @return
     */
    public Set<Object> sget(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
