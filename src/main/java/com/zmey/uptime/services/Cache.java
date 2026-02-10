package com.zmey.uptime.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



@Service
public class Cache {
    
    @Autowired
    private RedisTemplate <String, Object> redisTemplate;

    public void setValue (String key, Object value, long ttl_seconds) {
        
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttl_seconds));
    }

    public Object getValue (String key) {
        return redisTemplate.opsForValue().get(key);
    } 

}
