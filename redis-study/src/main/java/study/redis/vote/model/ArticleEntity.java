package study.redis.vote.model;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class ArticleEntity {
    private String id;
    private String title;
    private String content;

    public ArticleEntity(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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
}
