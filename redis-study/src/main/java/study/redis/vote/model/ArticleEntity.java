package study.redis.vote.model;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class ArticleEntity {
    private String id;
    private String title;
    private String content;
    private String createTime;

    public ArticleEntity(String id, String title, String content,long createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime=createTime+"";
    }

    public String getId() {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
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
