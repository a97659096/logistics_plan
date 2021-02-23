package com.logistics.plan.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    private static final Long SUCCESS = 1L;
    private long timeout = 9999;

    /**
     * 发布订阅
     * @param channel 通道
     * @param message 消息
     */
    public void convertAndSend(String channel, Object message){
        redisTemplate.convertAndSend(channel, message);
    }
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public String get(final String key) {
        String result = null;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    public Object hget(String name,String key){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(name,key);
    }

    public Map<Object,Object> hgetall(String name){
        return redisTemplate.opsForHash().entries(name);
    }

    public int hgetallnum(String name){
        return redisTemplate.opsForHash().entries(name).size();
    }

    public Cursor<Map.Entry<Object, Object>> hgetscan(String name,long start,long end){
        ScanOptions so = ScanOptions.scanOptions().count(2000).match("*").build();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(name,so);
        return cursor;
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,String v){
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<String> lRange(String k, long l, long l1){
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,String value){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 获取key集合
     * @param key
     * @return
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 删除hash中field这一对kv
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 加锁，无阻塞
     *
     * @param
     * @param
     * @return
     */
    public Boolean tryLock(String key, String value, long expireTime) {


        long start = System.currentTimeMillis();
        try{
            for(;;){
                //SET命令返回OK ，则证明获取锁成功
                Boolean ret = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
                if(ret){
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long end = System.currentTimeMillis() - start;
                if (end>=timeout) {
                    return false;
                }
            }
        }finally {

        }

    }


    /**
     * 解锁
     *
     * @param
     * @param
     * @return
     */
    public Boolean unlock(String key) {
//        Boolean delete = ;
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//
//        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
//        redisScript.setResultType(String.class);
//        Object result = redisTemplate.execute(redisScript, Collections.singletonList(key),value);
        return redisTemplate.delete(key);

    }

}
