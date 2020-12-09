package com.opensource;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.UUID;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/11/24
 * @creat_time: 2:04 下午
 */
public class RedisTest {

    public static void main(String[] args) {
/*        new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                getJedis().set(i+ UUID.randomUUID().toString(),UUID.randomUUID().toString()+"_"+UUID.randomUUID().toString());
            }
        }).start();*/
        getAndSet();
    }

    public static void getAndSet(){
        while (true) {
            try {
                Thread.sleep(2000);
                Jedis jedisResource = getJedis();
                jedisResource.set("ab"+System.currentTimeMillis(), "aa");
                System.out.println(jedisResource.get("ab")+"--------->"+jedisResource.getClient().getPort());
            } catch (Exception e) {
                System.out.println("ERROR"+e.getMessage());
            }
        }
    }

    public static Jedis getJedis() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", Sets.newHashSet(Splitter.on(";").split("127.0.0.1:26379;127.0.0.1:26380").iterator()), config);
        Jedis jedisResource = jedisSentinelPool.getResource();
        return jedisResource;
    }
}
