package study.redis.vote.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BaseDao {
    private static final JedisPool jedisPool = new JedisPool("localhost",6379);
    protected Jedis getJedis() {
        return jedisPool.getResource();
    }

}
