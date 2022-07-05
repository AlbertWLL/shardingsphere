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
 * Sharding-JDBC 复合分片策略(分库策略)
 * @author danque
 * @@date 2022-05-11
 */
public class ComplexKeysShardingDbAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    /**
     * @param availableTargetNames 数据库集合（sharding-1000，sharding-1001）
     * @param shardingValue logicTable(tb_vehicle)   coId
     * @return  返回物理分库
     */
    @SneakyThrows
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        Collection<String> databases = shardingValue.getColumnNameAndShardingValuesMap().get(DbConstants.DATABASE_ROUTE_COLUMN);
        Collection<String> coIds = shardingValue.getColumnNameAndShardingValuesMap().get("co_id");
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            throw new UnsupportedOperationException("db no configuration");
        }

        if (CollectionUtils.isNotEmpty(databases) && databases.stream().findFirst().isPresent()) {
            Long databaseNoParam = Long.parseLong(databases.stream().findFirst().get());
            String databaseNo = "-" + databaseNoParam;
            for (String eachDatabase : availableTargetNames) {
                if (eachDatabase.endsWith(databaseNo)) {
                    return Collections.singleton(eachDatabase);
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
            Long dbNo = DbRouteContextUtil.getDbNo(coId);
            String databaseNo = "-" + dbNo;
            for (String eachDatabase : availableTargetNames) {
                if (eachDatabase.endsWith(databaseNo)) {
                    return Collections.singleton(eachDatabase);
                }
            }
        }
        throw new UnsupportedOperationException("no db route found");
    }
}
