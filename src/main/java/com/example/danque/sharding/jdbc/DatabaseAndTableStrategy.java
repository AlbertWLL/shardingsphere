package com.example.danque.sharding.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.example.danque.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 分库分表策略
 *
 * @author 孔岩
 * @date 2021年9月9日11:43:52
 */
@Component
@Slf4j
public class DatabaseAndTableStrategy {

    /**
     * 获取tableTeamId
     *
     * @param coId 商户ID
     * @return TableTeamId
     */
    public Long queryTableTeamId(Long coId) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("coId",coId);
            log.info("http请求获取商家信息请求参数为：{}", JSONObject.toJSONString(param));
            String responseData = HttpUtil.sendSimpleGetRequest("http://localhost:6666/sharding/getShardingIngo", param);
            log.info("http请求获取商家信息返回数据为：{}",responseData);
        } catch (Exception e) {
            log.error("http请求获取分表编码异常:{}",e);
        }
        Long tableTeamId = 10000001l;
        return tableTeamId;
    }

}
