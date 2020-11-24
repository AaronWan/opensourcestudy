package com.opensource;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/11/24
 * @creat_time: 2:04 下午
 */
public class RedisTest {

    public static void main(String[] args) {
/*        redis.servers=10.112.7.1:30001;10.112.7.2:30001;10.112.7.3:30001
        redis.password=
                redis.dbIndex=0
        redis.sentinel=true
        redis.masterName=test
        startup=true*/
        Jedis jedisResource = getJedis();
        jedisResource.set("ab","aa","nx","ex",10);
        System.out.println(jedisResource.get("ab"));
    }


    public static Jedis getJedis() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("test", Sets.newHashSet(Splitter.on(";").split("10.112.7.1:30001;10.112.7.2:30001;10.112.7.3:30001").iterator()), config);
        Jedis jedisResource = jedisSentinelPool.getResource();
        return jedisResource;
    }
}
