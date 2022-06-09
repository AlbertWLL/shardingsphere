package com.example.danque.sharding.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Collections;

public class HintShardingTableAlgorithm implements HintShardingAlgorithm<String> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
		if (CollectionUtils.isEmpty(availableTargetNames)) {
			throw new UnsupportedOperationException("db no configuration");
		}
		Collection<Comparable<?>> tbNoList = HintManager.getTableShardingValues("");
		String tbNoStr = CollectionUtils.isNotEmpty(tbNoList) ? tbNoList.stream().findFirst().get().toString() : null;
		if (StringUtils.isNotEmpty(tbNoStr)) {
			String tbNo = "_" + tbNoStr;
			for (String tableName : availableTargetNames) {
				if (tableName.endsWith(tbNo)) {
					return Collections.singleton(tableName);
				}
			}
		}
		throw new UnsupportedOperationException("no table route found");
	}
}
