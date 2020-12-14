package study.redis.vote.model;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class ArticleEntity {
    private String id;
    private String title;
    private String content;
    private String author;
    private String createTime;
    private String votes;

    public ArticleEntity(String id, String title, String content,String author,long createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author=author;
        this.createTime=createTime+"";
    }

    public String getId() {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public Long getVotes() {
        return Long.valueOf(votes);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreateTime() {
        return Long.valueOf(createTime);
    }
}
