package com.example.danque.util;


import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description http请求工具类
 * @Date 2021/5/25 17:51
 * @Author wangLuLu
 * @Version 1.0
 */

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final String HTTP_CONTENT_CHARSET = "UTF-8";
    public static final Integer MAX_TIME_OUT = 10000;
    public static final Integer MAX_IDLE_TIME_OUT = 20000;
    public static final Long MAX_MANAGER_TIME = 1000L;
    public static final Integer MAX_CONN = 128;
    public static final Integer MAX_HOST_CONN = 32;
    public static final HttpClient httpClient = new HttpClient();

    public HttpUtil() {
    }

    public static String sendSimplePostRequest(String url, Map<String, Object> param) throws Exception {
        String result = null;
        PostMethod post = new PostMethod(url);

        String var17;
        try {
            post.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            if (param != null) {
                Iterator i$ = param.entrySet().iterator();

                while(i$.hasNext()) {
                    Entry<String, Object> entry = (Entry)i$.next();
                    if (entry.getValue() != null) {
                        post.addParameter((String)entry.getKey(), (String)entry.getValue());
                    }
                }
            }

            post.addRequestHeader(new Header("Connection", "close"));
            httpClient.executeMethod(post);
            if (post.getStatusCode() != 200) {
                result = "{\"code\":\"" + post.getStatusCode() + "\", \"msg\":\"" + post.getStatusText() + "\"}";
                post.abort();
                return result;
            }

            var17 = post.getResponseBodyAsString();
        } catch (ConnectTimeoutException var12) {
            result = "{\"code\":\"-1\", \"msg\":\"请求连接超时\"}";
            LOGGER.error("调用HttpsUtil.sendSimplePostRequest ConnectTimeoutException, url=" + url, var12.getMessage());
            var12.printStackTrace();
            throw var12;
        } catch (SocketTimeoutException var13) {
            result = "{\"code\":\"-1\", \"msg\":\"请求响应超时\"}";
            LOGGER.error("调用HttpUtil.sendSimplePostRequest SocketTimeoutException, url=" + url, var13.getMessage());
            var13.printStackTrace();
            throw var13;
        } catch (IOException var14) {
            result = "{\"code\":\"-1\", \"msg\":\"请求响应超时\"}";
            LOGGER.error("调用HttpUtil.sendSimplePostRequest IOException, url=" + url, var14.getMessage());
            var14.printStackTrace();
            throw var14;
        } catch (Exception var15) {
            result = "{\"code\":\"-1\", \"msg\":\"服务开小差，系统努力修复中\"}";
            LOGGER.error("调用HttpUtil.sendSimplePostRequest Exception, url=" + url, var15.getMessage());
            var15.printStackTrace();
            throw var15;
        } finally {
            post.releaseConnection();
        }

        return var17;
    }

    public static String sendSimpleGetRequest(String url, Map<String, Object> param) throws Exception {
        String result = null;
        StringBuilder sb = new StringBuilder();
        GetMethod get = null;

        String var15;
        try {
            String paramStr = "";
            if (param != null) {
                Iterator i$ = param.entrySet().iterator();

                while(i$.hasNext()) {
                    Entry<String, Object> entry = (Entry)i$.next();
                    if (entry.getValue() != null) {
                        sb.append((String)entry.getKey()).append("=").append(URLEncoder.encode((String)entry.getValue(), "utf-8")).append("&");
                    }
                }

                paramStr = sb.toString();
                paramStr = paramStr.substring(0, paramStr.length() - 1);
            }

            get = new GetMethod(url + "?" + paramStr);
            get.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            get.addRequestHeader(new Header("Connection", "close"));
            httpClient.executeMethod(get);
            if (get.getStatusCode() != 200) {
                get.abort();
                return result;
            }

            var15 = get.getResponseBodyAsString();
        } catch (IOException var12) {
            result = "{\"code\":\"-1\", \"msg\":\"请求响应超时\"}";
            LOGGER.error("HttpUtil.sendSimpleGetRequest响应超时, url=" + url, var12);
            throw var12;
        } catch (Exception var13) {
            result = "{\"code\":\"-1\", \"msg\":\"服务开小差，系统努力修复中\"}";
            LOGGER.error("调用HttpUtil.sendSimpleGetRequest Exception, url=" + url, var13);
            throw var13;
        } finally {
            if (get != null) {
                get.releaseConnection();
            }

        }

        return var15;
    }

    static {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.closeIdleConnections((long)MAX_IDLE_TIME_OUT);
        connectionManager.getParams().setParameter("http.connection-manager.max-total", MAX_CONN);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(MAX_HOST_CONN);
        httpClient.setHttpConnectionManager(connectionManager);
        httpClient.getParams().setIntParameter("http.socket.timeout", MAX_TIME_OUT);
        httpClient.getParams().setIntParameter("http.connection.timeout", MAX_TIME_OUT);
        httpClient.getParams().setLongParameter("http.conn-manager.timeout", MAX_MANAGER_TIME);
    }
}

