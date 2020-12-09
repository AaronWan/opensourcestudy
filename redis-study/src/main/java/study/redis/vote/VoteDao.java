package study.redis.vote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import study.redis.vote.model.ArticleEntity;

import java.util.List;
import java.util.Map;

public class VoteDao {
    Gson gson=new GsonBuilder().create();
    JedisPool jedisPool ;
    {
        jedisPool = new JedisPool("localhost",6379);
    }
    public void saveArticle(ArticleEntity article){
        getJedis().hmset(article.getId(),gson.fromJson(gson.toJson(article), Map.class));
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public ArticleEntity getArticle(String id) {
        Map rst=getJedis().hgetAll(id);
        return gson.fromJson(gson.toJson(rst), ArticleEntity.class);
    }

}
