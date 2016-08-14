package com.facishare.warehouse.common.utils;

import com.github.autoconf.ConfigFactory;
import com.github.autoconf.api.IChangeListener;
import com.github.autoconf.api.IConfig;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aaron on 15/9/28.
 */
public class HTTPUtils {
    public static final int MAX_TOTAL = 512;
    public static final int DEFAULT_MAX_PER_ROUTE = 100;
    private static Logger logger= LoggerFactory.getLogger(HTTPUtils.class);
    private static final int SOCKET_TIMEOUT = 10000;
    private static final int CONNECTION_TIMEOUT = 3000;
    private static CloseableHttpClient httpClient;

    static {
        httpClient = createHttpClient(SOCKET_TIMEOUT,CONNECTION_TIMEOUT,MAX_TOTAL,DEFAULT_MAX_PER_ROUTE);
        logger.info("init HttpClient");
        ConfigFactory.getInstance().getConfig("fs-warehouse-image-fastcgi", new IChangeListener() {
            @Override
            public void changed(IConfig config) {
                int socketTimeout=config.getInt("socketTimeout",SOCKET_TIMEOUT);
                int connectionTimeout=config.getInt("connectionTimeout",CONNECTION_TIMEOUT);
                int maxTotal=config.getInt("maxTotal",MAX_TOTAL);
                int defaultMaxPerRoute=config.getInt("defaultMaxPerRoute",DEFAULT_MAX_PER_ROUTE);
                logger.info("loading HTTPUtils Config");
                logger.info("socketTimeout:{}",SOCKET_TIMEOUT);
                logger.info("connectionTimeout:{}",CONNECTION_TIMEOUT);
                logger.info("maxTotal:{}",MAX_TOTAL);
                logger.info("defaultMaxPerRoute:{}",DEFAULT_MAX_PER_ROUTE);
                httpClient = createHttpClient(socketTimeout,connectionTimeout,maxTotal,defaultMaxPerRoute);
                logger.info("loading HTTPUtils Config end");
            }
        });
    }



    private static CloseableHttpClient createHttpClient(int socketTimeout,int connectionTimeout,int maxTotal,int defaultMaxPerRoute){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(socketTimeout);
        rb.setConnectTimeout(connectionTimeout);

        hb.setDefaultRequestConfig(rb.build());


        return hb.build();
    }

    public static String sendHTTPRequest(String requestURL) throws Exception{
        String result = "";
        HttpGet httpgets = new HttpGet(requestURL);
        try (CloseableHttpResponse response = httpClient.execute(httpgets)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                result = IOUtils.toString(instreams, Charset.forName("utf-8"));
                httpgets.abort();

            }
        }
        return result;
    }

    public static HttpEntity get(String requestURL) throws Exception{
        HttpEntity result = null;
        HttpGet httpgets = new HttpGet(requestURL);
        try (CloseableHttpResponse response = httpClient.execute(httpgets)) {
            result = response.getEntity();
        }
        return result;
    }
    public static byte[] postHTTPRequest(String requestURL, byte[] content) {
        HttpPost post = new HttpPost(requestURL);
        post.setEntity(new ByteArrayEntity(content));
        byte[] result = null;
        try (CloseableHttpResponse response = httpClient.execute(post)) {
            result = IOUtils.toByteArray(response.getEntity().getContent());
        } catch (Exception e) {
            logger.error("postHTTPRequest",e);
            throw new RuntimeException(e);
        }
        return result;
    }
    public static byte[] postHTTPRequest(String requestURL, byte[] content,Map<String,String> headers) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(requestURL);
        Iterator it=headers.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            post.setHeader(key, headers.get(key));
        }
        post.setEntity(new ByteArrayEntity(content));
        byte[] result = null;
        try {
            HttpResponse response = httpclient.execute(post);
            result = IOUtils.toByteArray(response.getEntity().getContent());
        } catch (Exception e) {
            logger.error("postHTTPRequest",e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
