package study.redis.vote.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import study.redis.vote.model.ArticleEntity;
import study.redis.vote.model.PageResult;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleDao extends BaseDao {

    public static final Gson gson = new GsonBuilder().create();

    public void saveArticle(ArticleEntity article) {
        Jedis connection = getJedis();
        Pipeline pipe = connection.pipelined();
        connection.hmset(article.getId(), gson.fromJson(gson.toJson(article), Map.class));
        connection.zadd("article_post", article.getCreateTime(), article.getId());
        pipe.multi();
        try {
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public ArticleEntity getArticle(String id) {
        Map rst = getJedis().hgetAll(id);
        return gson.fromJson(gson.toJson(rst), ArticleEntity.class);
    }

    public PageResult<ArticleEntity> query(int pageNumber, int pageSize) {
        PageResult<ArticleEntity> result = new PageResult<>();
        Jedis connection = getJedis();
        long start = (pageNumber - 1) * pageSize;
        long max = connection.zcount("article_post", 0, Long.MAX_VALUE);
        result.setTotalCount(max);
        result.setPage(pageNumber);
        result.setTotalPage(max % pageSize > 0 ? max / pageSize + 1 : max / pageSize);
        long stop = pageNumber * pageSize - 1;
        if (start > max) {
            return new PageResult<>();
        }
        if (stop > max) {
            stop = max;
        }
        Set<String> articleIds = connection.zrange("article_post", start, stop);
        result.setData(articleIds.stream().map(articleId -> gson.fromJson(gson.toJson(connection.hgetAll(articleId)), ArticleEntity.class)).collect(Collectors.toList()));
        connection.close();
        return result;
    }

}
