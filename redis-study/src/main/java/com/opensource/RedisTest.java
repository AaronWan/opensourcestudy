package com.opensource;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.*;
import study.redis.vote.dao.ArticleDao;
import study.redis.vote.model.ArticleEntity;
import study.redis.vote.model.PageResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static Jedis getSentinelJedis() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", Sets.newHashSet(Splitter.on(";").split("127.0.0.1:26379;127.0.0.1:26380").iterator()), config);
        Jedis jedisResource = jedisSentinelPool.getResource();
        return jedisResource;
    }
    public static Jedis getJedis() {
        JedisPool jedisPool = new JedisPool("localhost",6379);
        return jedisPool.getResource();
    }

    @Test
    public void testArticle(){
        ArticleDao dao = new ArticleDao();
//        for (int i = 0; i <100000 ; i++) {
//            ArticleEntity article=new ArticleEntity(UUID.randomUUID().toString(),"神雕侠侣"+i,"神雕侠侣"+i,System.currentTimeMillis());
//            dao.saveArticle(article);
//        }
        PageResult<ArticleEntity> pageData = dao.query(1, 2);
        for (int i = 1; i <= pageData.getTotalPage(); i++) {
            System.out.println("第"+(i)+"页:\t"+dao.query(i,2).getData().stream().map(item->item.getTitle()+"["+(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date(item.getCreateTime())))).collect(Collectors.joining("],")));
        }
        System.out.println("....");
    }
}
