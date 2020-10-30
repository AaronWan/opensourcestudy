package com.httpclient.study;

import com.google.common.util.concurrent.AtomicDouble;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class HttpClientTest {
    static CloseableHttpClient httpclient;

    private static final PoolingHttpClientConnectionManager connectionManager;

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(2);
        connectionManager.setDefaultMaxPerRoute(1);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
//        rb.setConnectionRequestTimeout(100);
        rb.setSocketTimeout(2000);
        rb.setConnectTimeout(10);

        hb.setDefaultRequestConfig(rb.build());

        httpclient = hb.build();
    }
    private final static Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    @Test
    public void testSocketReadTimeout() throws IOException {
        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(1000);
        rb.setConnectTimeout(1);

//        rb.setProxy(HttpHost.create("http://localhost:8888"));//? todo

        rb.setCircularRedirectsAllowed(false);
        RequestConfig rc = rb.build();
        hb.setDefaultRequestConfig(rc);

        httpclient = hb.build();

        HttpGet request= new HttpGet("http://localhost:8080/hello/HelloServlet");
        request.setConfig(RequestConfig.copy(rc).setSocketTimeout(2000).setConnectTimeout(1000).build());
        System.out.println(request.getConfig().toString());

//        try(CloseableHttpResponse response = httpclient.execute(request)){
//            System.out.println(new String(EntityUtils.toByteArray(response.getEntity())));
//        }
         request= new HttpGet("http://www.baidu.com");
        request.setConfig(RequestConfig.copy(rc).setSocketTimeout(2000).setConnectTimeout(1000).build());
        System.out.println(request.getConfig().toString());

        try(CloseableHttpResponse response = httpclient.execute(request)){
            System.out.println(new String(EntityUtils.toByteArray(response.getEntity())));
        }
        System.out.println(connectionManager.getTotalStats());
    }

    @Test
    public void muiltThreadTest() {
        ExecutorService pool = Executors.newCachedThreadPool();
        int n = 10;
        final AtomicDouble time = new AtomicDouble();
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
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
    @Test
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
    @Test
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
