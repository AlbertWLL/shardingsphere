package com.example.danque.util;


import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * traceid 工具类
 * 每个线程自己负责生成traceid
 * 在调用入口进行初始化
 */
public class TraceLogUtils {

    /**
     * traceid 保存在 threadlocal 中
     */
    private static final ThreadLocal<String> traceIdLocal = new ThreadLocal<String>();

    /**
     * 获取当前的traceid
     *
     * @return
     */
    public static String getTraceId() {
        String traceId = traceIdLocal.get();

        return null == traceId ? "" : traceId;
    }

    /**
     * 当前没有trzceid 则生成新的
     */
    public static String genTracgId() {
        traceIdLocal.remove();
        String traceId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        traceIdLocal.set(traceId);
        return traceId;

    }

    /**
     * 当前没有trzceid 则生成新的
     */
    public static String genTracgIdIfNotExist() {
        if (!StringUtils.isEmpty(traceIdLocal.get())) {
            return traceIdLocal.get();
        }
        traceIdLocal.remove();
        String traceId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        traceIdLocal.set(traceId);
        return traceId;

    }

    /**
     * 清除traceid
     */
    public static void removeTraceId() {
        traceIdLocal.remove();

    }

    /**
     * 上游有traceid的情况
     *
     * @param traceId
     */
    public static void genTracgId(String traceId) {
        if (StringUtils.isEmpty(traceId)) {
            genTracgId();
            return;
        }
        traceIdLocal.remove();
        traceIdLocal.set(traceId);

    }


}
