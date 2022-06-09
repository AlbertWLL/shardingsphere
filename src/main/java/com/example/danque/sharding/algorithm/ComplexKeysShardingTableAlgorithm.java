package com.example.danque.sharding.algorithm;

import com.example.danque.common.constants.DbConstants;
import com.example.danque.util.DbRouteContextUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Collections;

/**
 * Sharding-JDBC 复合分片策略(分表策略)
 * @author danque
 * @@date 2022-05-11
 */
public class ComplexKeysShardingTableAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    /**
     * @param availableTargetNames  分表集合（tb_vehicle_1  tb_vehicle_2）
     * @param shardingValue  分片值（co_id）
     * @return 返回物理分表
     */
    @SneakyThrows
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        Collection<String> tableNos = shardingValue.getColumnNameAndShardingValuesMap().get(DbConstants.TABLE_ROUTE_COLUMN);
        Collection<String> coIds = shardingValue.getColumnNameAndShardingValuesMap().get("co_id");
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            throw new UnsupportedOperationException("tb no configuration");
        }

        if (CollectionUtils.isNotEmpty(tableNos) && tableNos.stream().findFirst().isPresent()) {
            Long tableNoParam = Long.parseLong(tableNos.stream().findFirst().get());
            String tbNo = "_" + tableNoParam;
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(tbNo)) {
                    return Collections.singleton(tableName);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(coIds) && coIds.stream().findFirst().isPresent()) {
            Object coIdObj = coIds.stream().findFirst().get();
            Long coId = null;
            if (coIdObj instanceof Long) {
                coId = (Long) coIdObj;
            } else if (coIdObj instanceof String) {
                coId = Long.parseLong(coIdObj.toString());
            }
            Long tbNo = DbRouteContextUtil.getTableNo(coId);
            String tableNo = "_" + tbNo;
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(tableNo)) {
                    return Collections.singleton(tableName);
                }
            }
        }
        throw new UnsupportedOperationException("no table route found");
    }
}
