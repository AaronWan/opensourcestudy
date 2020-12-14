package com.opensource.fetch;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import study.redis.vote.dao.ArticleDao;
import study.redis.vote.model.ArticleEntity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class PoemFetchImpl extends AbstractFetch {
    public static void main(String[] args) {
        new PoemFetchImpl().execute();
    }
    @Override
    void execute() {
        int count = 0;
        for (int i = 1; ; i++) {
            int rst = fetchPoemByType(i);
            if (rst == 0 && count++ > 10) {
                break;
            }
            if (rst > 0 && count != 0) {
                count = 0;
            }
        }
    }

    private void fetchAuth(int i) {
        try {
            String ss = getContent("http://so.gushiwen.org/authors/Default.aspx?p=" + i, Maps.newHashMap(), "utf-8");
            Document page = Jsoup.parse(ss);
            Elements links = page.select(".left .cont");

            links.forEach((content) -> {
                try {
                    String name = content.select("b").text();
                    String title = content.select("p").get(1).text().split("►")[0];
                    String content1 = content.select("img").attr("src");
                    String href = content.select("p:nth-child(3) > a").attr("href");
                    String href2 = content.select("p:nth-child(2) > a").attr("href");
                    System.out.println(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ArticleDao articleDao = new ArticleDao();
    private int fetchPoemByType(int finalI) {
        AtomicInteger rst = new AtomicInteger();
        try {
            String ss = getContent("http://so.gushiwen.org/type.aspx?p=" + finalI, Maps.newHashMap(), "utf-8");
            Document page = Jsoup.parse(ss);
            Elements links = page.select("textarea");

            links.forEach((content) -> {
                try {
                    String[] contents = content.text().split("——");
                    String[] other = contents[1].split("》|《");
                    String author = other[0];
                    String comment = other[2];
                    String title = other[1];
                    String content1 = contents[0];
                    System.out.println(contents);
                    ArticleEntity article=new ArticleEntity(UUID.randomUUID().toString(),title,content1+"\n"+comment,author,System.currentTimeMillis());
                    articleDao.saveArticle(article);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst.get();
    }

}
