package study.redis.vote.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import study.redis.vote.model.ArticleEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VoteDao extends BaseDao {
    Gson gson = new GsonBuilder().create();

    public void saveArticle(ArticleEntity article) {
        Jedis redis = getJedis();
        redis.hmset(article.getId(), gson.fromJson(gson.toJson(article), Map.class));
        redis.zadd("article_post",System.currentTimeMillis(),article.getId());
    }

    public ArticleEntity getArticle(String id) {
        Map rst = getJedis().hgetAll(id);
        return gson.fromJson(gson.toJson(rst), ArticleEntity.class);
    }

    public List<ArticleEntity> query(int pageNumber, int pageSize){
        Jedis redis = getJedis();
        int start=(pageNumber-1)*pageSize;
        int stop=pageNumber*pageSize-1;
        Set<String> articleIds=redis.zrange("article_post",start,stop);
        return articleIds.stream().map(articleId->getArticle(articleId)).collect(Collectors.toList());
    }

}
