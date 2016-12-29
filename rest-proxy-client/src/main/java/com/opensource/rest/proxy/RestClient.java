package com.opensource.rest.proxy;


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensource.rest.proxy.exception.RestInvokeException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 */
public class RestClient {
    public static final Gson gson=new GsonBuilder().create();
    private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    protected static final int DEFAULT_MAX_CONNECTION = 512;
    protected static final int DEFAULT_MAX_PER_ROUTE_CONNECTION = 50;

    protected static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    protected static final int DEFAULT_CONNECTION_TIMEOUT = 2000;

    private CloseableHttpClient defaultHttpClient;

    private Map<String, CloseableHttpClient> httpClientMap = Maps.newConcurrentMap();

    public RestClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTION);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        rb.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);

        hb.setDefaultRequestConfig(rb.build());

        defaultHttpClient = hb.build();
    }

    public void createHttpClientForService(String serviceKey, RestServiceProxyFactory.RestServiceConfig serviceConfig) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(serviceConfig.getSocketTimeOut() == 0 ? DEFAULT_SOCKET_TIMEOUT
                : serviceConfig.getSocketTimeOut());
        rb.setConnectTimeout(serviceConfig.getConnectionTimeOut() == 0 ? DEFAULT_CONNECTION_TIMEOUT
                : serviceConfig.getConnectionTimeOut());

        hb.setDefaultRequestConfig(rb.build());

        try {
            CloseableHttpClient oldClient = httpClientMap.get(serviceKey);
            if (oldClient != null) {
                oldClient.close();
            }
        } catch (Exception e) {
            LOG.error("Close httpclient error,serviceKey:{}", serviceKey, e);
        }

        httpClientMap.put(serviceKey, hb.build());
    }


    public <A, R> R post(String serviceKey, String uri, Map<String, String> headers, A arg, Class<R> clazz) {
        DefaultRestResponseHandler<R> handler = new DefaultRestResponseHandler<>(uri, clazz);
        try {
            return post(serviceKey, uri, headers, arg, handler);
        } catch (IOException e) {
            throw new RestInvokeException(e.getMessage(),e);
        }
    }

    public <A, R> R post(String serviceKey, String uri, Map<String, String> headers, A arg, ResponseHandler<R> handler)
            throws IOException {

        RequestBuilder requestBuilder = RequestBuilder.post();
        requestBuilder.setUri(uri);

        for (Map.Entry<String, String> e : headers.entrySet()) {
            requestBuilder.addHeader(e.getKey(), e.getValue());
        }

        BasicHttpEntity entity = new BasicHttpEntity();

        byte[] bytes = toProto(arg);
        entity.setContentLength(bytes.length);
        entity.setContent(new ByteArrayInputStream(bytes));
        requestBuilder.setEntity(entity);

        CloseableHttpClient serviceClient = httpClientMap.get(serviceKey);
        if (serviceClient == null) {
            serviceClient = defaultHttpClient;
        }
        try (CloseableHttpResponse response = serviceClient.execute(requestBuilder.build())) {
            return handler.handleResponse(response);
        }
    }
    public Object get(String serviceName, String s, Map<String, String> headers, Class<?> returnType) {
        /**
         * @TODO Rest get process Handler impl
         */
        return null;
    }

    public static final class DefaultRestResponseHandler<T> implements ResponseHandler<T> {

        private Class<T> clazz;
        private String uri;

        public DefaultRestResponseHandler(String uri, Class<T> clazz) {
            this.uri = uri;
            this.clazz = clazz;
        }

        @Override
        public T handleResponse(HttpResponse response) throws IOException {
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                throw new RestInvokeException("StatusCode:" + statusCode +",url:" + this.uri);
            }


            switch (statusCode) {
                case HttpStatus.SC_OK:
                    return fromProto(EntityUtils.toByteArray(response.getEntity()), clazz);
                default:
                    throw new RestInvokeException("RestStatus:" + statusCode + ",url:" + this.uri);
            }
        }
    }

    private static <T> byte[] toProto(T obj) {
        return gson.toJson(obj).getBytes();
    }

    private static <T> T fromProto(byte[] bytes, Class<T> clazz) {
        return gson.fromJson(new String(bytes),clazz);
    }

}
