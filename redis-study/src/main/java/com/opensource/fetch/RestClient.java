package com.opensource.fetch;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RestClient {

    private CloseableHttpClientManager clientManager;

    public RestClient() {
        clientManager = new CloseableHttpClientManager();
    }

    public String execute(String url, Map<String, String> headers,String charset) throws URISyntaxException {
        CloseableHttpClient serviceClient = clientManager.getClient();
        HttpUriRequest request = new HttpGet(new URI(url));
        if (headers != null) {
            headers.forEach((key, value) -> {
                request.setHeader(key, value);
            });
        }
        try (CloseableHttpResponse response = serviceClient.execute(request)) {
            return IOUtils.toString(response.getEntity().getContent(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public byte[] getBytes(String url, Map<String, String> headers) throws URISyntaxException {
        CloseableHttpClient serviceClient = clientManager.getClient();
        HttpUriRequest request = new HttpPost(new URI(url));
        if (headers != null) {
            headers.forEach((key, value) -> {
                request.setHeader(key, value);
            });
        }
        try (CloseableHttpResponse response = serviceClient.execute(request)) {
            return IOUtils.toByteArray(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }
    private static class CloseableHttpClientManager {
        protected static final int DEFAULT_MAX_CONNECTION = 512;
        protected static final int DEFAULT_MAX_PER_ROUTE_CONNECTION = 50;
        protected static final int DEFAULT_SOCKET_TIMEOUT = 10000;
        protected static final int DEFAULT_CONNECTION_TIMEOUT = 2000;

        private PoolingHttpClientConnectionManager connectionManager;
        private CloseableHttpClient defaultHttpClient;
        private static ScheduledExecutorService closeExecutor = Executors.newScheduledThreadPool(10);

        {
            closeExecutor.scheduleAtFixedRate(() -> {
                if (connectionManager != null) {
                }
            }, 1, 30, TimeUnit.MINUTES);
        }

        public CloseableHttpClientManager() {
            resetDefaultClient();
        }

        private CloseableHttpClient resetDefaultClient() {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTION);
            connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTION);
            SocketConfig.Builder sb = SocketConfig.custom();
            sb.setSoKeepAlive(true);
            sb.setTcpNoDelay(true);
            connectionManager.setDefaultSocketConfig(sb.build());
            RequestConfig.Builder rb = RequestConfig.custom();
            rb.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
            rb.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
            RequestConfig defaultRequestConfig = rb.build();
            HttpClientBuilder hb = HttpClientBuilder.create();
            hb.setConnectionManager(connectionManager);
            hb.setDefaultRequestConfig(defaultRequestConfig);


            CloseableHttpClient oldClient = defaultHttpClient;
            defaultHttpClient = hb.build();
            closeSocketClient(oldClient);
            return defaultHttpClient;
        }

        public CloseableHttpClient getClient() {
            if (defaultHttpClient == null) {
                return resetDefaultClient();
            }
            return defaultHttpClient;
        }

        private void closeSocketClient(CloseableHttpClient client) {
            if (client == null) return;
            closeExecutor.schedule(() -> {
                try {
                    if (client != null) {
                        client.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 20, TimeUnit.SECONDS);
        }
    }

}
