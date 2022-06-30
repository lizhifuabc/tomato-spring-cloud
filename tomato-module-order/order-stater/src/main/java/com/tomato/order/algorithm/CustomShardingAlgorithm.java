package com.tomato.order.algorithm;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义分库分表算法
 *
 * @author lizhifu
 * @date 2022/6/30
 */
@Slf4j
public class CustomShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    private final String KEY_MERCHANT_NO = "merchant_no";
    private final String KEY_ORDER_NO = "order_no";
    private static final String SHARDING_COUNT_KEY = "sharding-count";
    @Getter
    private Properties props;

    private int shardingCount;

    @Override
    public void init(final Properties props) {
        this.props = props;
        shardingCount = getShardingCount(props);
    }
    private int getShardingCount(final Properties props) {
        Preconditions.checkArgument(props.containsKey(SHARDING_COUNT_KEY), "Sharding count cannot be null.");
        return Integer.parseInt(props.getProperty(SHARDING_COUNT_KEY));
    }
    @Override
    public String getType() {
        return "CUSTOM";
    }
    /**
     *
     * @param availableTargetNames 在加载配置文件时，会解析表分片规则。将结果存储到 collection中，doSharding（）参数使用
     * @param shardingValue SQL中对应的
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        Map<String, Collection<Comparable<?>>> shardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        Collection<Comparable<?>> merchantNos = shardingValuesMap.get(KEY_MERCHANT_NO);
        String sign = "";
        if (merchantNos != null && !merchantNos.isEmpty()) {
            String merchantNo = (String) merchantNos.toArray()[0];
            sign = merchantNo.substring(merchantNo.length() - 4);
        }
        Collection<Comparable<?>> orderNos = shardingValuesMap.get(KEY_ORDER_NO);
        if (orderNos != null && !orderNos.isEmpty()) {
            String orderNo = (String) orderNos.toArray()[0];
            sign = orderNo.substring(orderNo.length() - 4);
        }
        String shardingResultSuffix = new BigInteger(sign).mod(new BigInteger(String.valueOf(shardingCount))).toString();
        return Collections.singletonList(shardingResultSuffix);
    }
}
