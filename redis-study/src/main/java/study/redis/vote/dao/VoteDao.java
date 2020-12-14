package study.redis.vote.dao;

import redis.clients.jedis.Jedis;

public class VoteDao extends BaseDao {
    public void vote(String articleId,String userId){
        Jedis connection = getJedis();
        try{
            if(connection.sadd("voted|"+articleId,userId)==1){
                connection.zincrby("score:",432,articleId);
                connection.hincrBy(articleId,articleId,1);
            }
        }finally {
            connection.close();
        }
    }
}
