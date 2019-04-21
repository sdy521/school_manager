package com.study.study_manager.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static RedisTemplate<String,Object> redisTemplate;

    //在类被加载的时候执行且仅会被执行一次，一般用来初始化静态变量和调用静态方法
    static {
        redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate");
    }

    /***
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static boolean SET(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object GET(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
