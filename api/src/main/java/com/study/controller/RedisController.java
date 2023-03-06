package com.study.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-03-06 11:53
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public void set(String key, String value){
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/get")
    public String set(String key){
        return  stringRedisTemplate.opsForValue().get(key);
    }
}
