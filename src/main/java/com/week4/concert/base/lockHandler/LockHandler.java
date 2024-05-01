package com.week4.concert.base.lockHandler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LockHandler {

    private RedisTemplate<String,String> redisTemplate;


    public LockHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(String key,long ttl){
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key,"lock", Duration.ofSeconds(ttl));
    }

    public Boolean unlock(String key){
        return redisTemplate.delete(key);
    }

}