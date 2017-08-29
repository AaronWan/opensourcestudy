package com.httpclient.study;

import com.google.common.util.concurrent.AtomicDouble;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class HttpClientTest {
    static PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(10000, TimeUnit.MINUTES);
    static CloseableHttpClient httpclient;

    static {
        pool.setDefaultMaxPerRoute(10);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(11000).setSoKeepAlive(false).build();
        pool.setDefaultSocketConfig(socketConfig);
        httpclient = HttpClients.custom().setConnectionManager(pool).build();
    }

    @Test
    public void muiltThreadTest() {
        ExecutorService pool = Executors.newCachedThreadPool();
        int n = 10;
        final AtomicDouble time = new AtomicDouble();
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.submit(() -> {
                long start = System.currentTimeMillis();
                haveConnectionPool();
                time.addAndGet((System.currentTimeMillis() - start) / 1000.0);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(time.get() / n);
    }

    //10.0114
    public void haveConnectionPool() {
        // 创建默认的httpClient实例.
        // 创建httppost
        HttpGet httppost = new HttpGet("http://localhost:8080/hello/HelloServlet");
        System.out.println("executing request " + httppost.getURI());
        try (CloseableHttpResponse response = httpclient.execute(httppost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("--------------------------------------");
                System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }
    }

    //10.1024
    public void noConnectionPool() {
        // 创建httppost
        HttpGet httppost = new HttpGet("http://localhost:8080/hello/HelloServlet");
        System.out.println("executing request " + httppost.getURI());
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(httppost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("--------------------------------------");
                System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        List<Result> res = new ArrayList<>();
        Result result = JUnitCore.runClasses(HttpClientTest.class);
        res.add(result);

        System.out.println("共执行本用例的次数为：" + res.size());
    }


}
