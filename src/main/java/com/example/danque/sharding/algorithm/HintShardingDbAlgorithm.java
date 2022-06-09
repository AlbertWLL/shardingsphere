package com.example.danque.sharding.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Collections;

public class HintShardingDbAlgorithm implements HintShardingAlgorithm<String> {
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
		if (CollectionUtils.isEmpty(availableTargetNames)) {
			throw new UnsupportedOperationException("db no configuration");
		}
		Collection<Comparable<?>> dbNoList = HintManager.getDatabaseShardingValues();
		String databaseNoParam = CollectionUtils.isNotEmpty(dbNoList) ? dbNoList.stream().findFirst().get().toString() : null;
		if (StringUtils.isNotEmpty(databaseNoParam)) {
			String databaseNo = "-" + databaseNoParam;
			for (String eachDatabase : availableTargetNames) {
				if (eachDatabase.endsWith(databaseNo)) {
					return Collections.singleton(eachDatabase);
				}
			}
		}
		throw new UnsupportedOperationException("no db route found");
	}
}
