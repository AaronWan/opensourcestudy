package com.opensource;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.*;
import study.redis.vote.VoteDao;
import study.redis.vote.model.ArticleEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/11/24
 * @creat_time: 2:04 下午
 */
public class RedisTest {

    public static void main(String[] args) throws IOException {
        Jedis jedisResource = getJedis();
        Pipeline pip = jedisResource.pipelined();
        for (int i = 0; i <20000 ; i++) {
            pip.set("a~"+i,"~"+i);
            System.out.println(""+i);
        }
        Response<String> response = pip.multi();
        pip.close();
        System.out.println(response.get());
    }


    public static Jedis getSentinelJedis() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("test", Sets.newHashSet(Splitter.on(";").split("10.112.7.1:30001;10.112.7.2:30001;10.112.7.3:30001").iterator()), config);
        Jedis jedisResource = jedisSentinelPool.getResource();
        return jedisResource;
    }
    public static Jedis getJedis() {
        JedisPool jedisPool = new JedisPool("localhost",6379);
        return jedisPool.getResource();
    }

    @Test
    public void testArticle(){
        VoteDao dao = new VoteDao();
        ArticleEntity article = new ArticleEntity(UUID.randomUUID().toString(), "平凡的世界", "一个朴素的故事");
        dao.saveArticle(article);
        article=dao.getArticle(article.getId());
        System.out.printf(article.getContent());
    }
}
