package com.opensource.fetch;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class StoryFetchImpl extends AbstractFetch {
    String host = "http://www.tonghua5.com";

    public static void main(String[] args) {
        StoryFetchImpl story = new StoryFetchImpl();
        story.execute();
    }
    @Override
    void execute() {
        Map<String, String> subStory = getSubStory();
        CountDownLatch countDownLatch=new CountDownLatch(subStory.size());
        subStory.forEach((link, name) -> {
            executor.execute(() -> {
                int count = 0;
                for (int i = 1; ; i++) {
                    int rst = fetchStory(link,name, i);
                    if (rst == 0 && count++ > 10) {
                        break;
                    }
                    if (rst > 0 && count != 0) {
                        count = 0;
                    }
                }
                countDownLatch.countDown();
            });
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getSubStory() {
        Document page = getPage(host, Maps.newHashMap(),"utf-8");
        Elements child = page.select("ul li a");
        Map<String, String> map = Maps.newHashMap();
        child.stream().forEach(element -> {
            if (!element.select("a").attr("href").contains("html")) {
                map.put(host + "" + element.select("a").attr("href"), element.select("a").get(0).text().replace("]", "").replace("[", ""));
            }
        });
        return map;
    }


    public int fetchStory(String link,String type, int i) {
        AtomicInteger rst = new AtomicInteger();
        try {
            Document page = getPage(link+"index_"+i+".html", getHeaders(),"utf-8");
            Elements links = page.select(".chnanel-title").select(".d2").select("a");
            links.forEach((content) -> {
                try {
                    String href = host+content.attr("href");
                    String name= content.text();
                    Document contentPage = getPage(href, getHeaders(),"utf-8");
                    String author=contentPage.select(".viewbox").select(".info").get(0).childNode(1).toString();
                    String con=contentPage.select(".viewbox").select(".content").text();
                    System.out.println(href+"\t "+name);
                    rst.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
        }
        return rst.get();
    }


    public Map<String, String> getHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Host", "www.tonghua5.com");
        headers.put("Pragma", "no-cache");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        headers.put("Cache-Control", "no-cache");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Cookie", "PHPSESSID=ivhc1lucl2e0p4b8sbgglj26l3; bdshare_firstime=1513091729051; UM_distinctid=1604b4b8725d12-0cd4f6ca627a58-17386d57-13c680-1604b4b8726db8; CNZZDATA1257371347=1399610683-1513086497-null%7C1513092096");
        return headers;
    }


}
