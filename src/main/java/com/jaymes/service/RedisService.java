package com.jaymes.service;

import com.jaymes.entity.redis.KeyPrefix;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author Jaymes Yao
 * @date 2020/12/29 19:16
 */
@Service
public class RedisService {

  @Autowired
  RedisTemplate redisTemplate;

  /**
   * 获取单个对象
   */
  public <T> T getReference(KeyPrefix prefix, String key) {
    ValueOperations operations = redisTemplate.opsForValue();
    String realKey = prefix.getPrefix() + key;
    return (T) operations.get(realKey);
  }

  /**
   * 获取单个对象
   */
  public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
    ValueOperations<String, T> operations = redisTemplate.opsForValue();
    String realKey = prefix.getPrefix() + key;
    return operations.get(realKey);
  }


  /**
   * 设置单个对象
   */
  public <T> boolean set(KeyPrefix prefix, String key, T value) {
    ValueOperations<String, T> operations = redisTemplate.opsForValue();
    String realKey = prefix.getPrefix() + key;
    int seconds = prefix.expireSeconds();
    if (seconds <= 0) {
      operations.set(realKey, value);
    } else {
      operations.set(realKey, value);
      redisTemplate.expire(realKey, seconds, TimeUnit.SECONDS);
    }
    return true;
  }

  /**
   * 删除对象
   */
  public <T> boolean delete(KeyPrefix prefix, String key) {
    String realKey = prefix.getPrefix() + key;
    return redisTemplate.delete(realKey);
  }
}
