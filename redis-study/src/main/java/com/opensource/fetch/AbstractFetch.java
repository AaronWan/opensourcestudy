package com.opensource.fetch;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public abstract class AbstractFetch {
    private RestClient client = new RestClient();
    ExecutorService executor = Executors.newCachedThreadPool();
    abstract void execute();

    String getContent(String url, Map<String, String> headers,String charset) throws URISyntaxException {

        return client.execute(url, headers, charset);
    }

    Document getPage(String url, Map<String, String> headers,String charset) {
        String content= null;
        try {
            content = getContent(url,headers,charset);
            Document page = Jsoup.parse(content);
            return page;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new Document(url);
    }
}
