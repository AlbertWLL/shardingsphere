package com.example.danque.util;

import com.alibaba.fastjson.JSONObject;
import com.example.danque.common.constants.DefinitionsConstant;
import com.example.danque.sharding.jdbc.DatabaseAndTableStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DbRouteContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    private static DatabaseAndTableStrategy databaseAndTableStrategy;

    public static Long getDbNo(Long coId) throws Exception {
        //取模
        return getTableTeamId(coId) / DefinitionsConstant.NUMBER_10000;
    }

    public static Long getTableNo(Long coId) throws Exception {
        //取余
        return getTableTeamId(coId) % DefinitionsConstant.NUMBER_10000;
    }

    /**
     * 获取tableTeamId
     * @param coId
     * @return
     */
    public static Long getTableTeamId(Long coId) throws Exception {

        try {
            Map<String, Object> param = new HashMap<>();
            param.put("coId",String.valueOf(coId));
            log.info("http请求获取商家信息请求参数为：{}", JSONObject.toJSONString(param));
            String responseData = HttpUtil.sendSimpleGetRequest("http://localhost:6666/sharding/getShardingInfo", param);
            log.info("http请求获取商家信息返回数据为：{}",responseData);
            Long tableTeamId = Long.valueOf(responseData);
            return tableTeamId;
        } catch (Exception e) {
            log.error("http请求获取分表编码异常:{}",e);
            throw new Exception("获取分表编码失败");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
}
