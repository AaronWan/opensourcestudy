package study.redis.vote.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class BaseDao {
    private final JedisSentinelPool pool;

    {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("localhost:26380");
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisSentinelPool("mymaster", sentinels, config);
    }


    protected Jedis getJedis() {
        return pool.getResource();
    }
}
