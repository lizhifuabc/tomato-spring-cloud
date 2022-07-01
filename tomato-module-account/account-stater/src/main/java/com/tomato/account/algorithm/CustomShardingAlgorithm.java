package com.tomato.account.algorithm;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 自定义分库分表算法
 *
 * @author lizhifu
 * @date 2022/6/30
 */
@Slf4j
public class CustomShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    private static final String SHARDING_COUNT_KEY = "sharding-count";
    private static final String START_OFFSET_INDEX_KEY = "start-offset";
    private static final Pattern DATA_NODE_SUFFIX_PATTERN = Pattern.compile("\\d+$");
    private static final char DEFAULT_PADDING_CHAR = '0';
    private static final String STOP_OFFSET_INDEX_KEY = "stop-offset";
    private static final String ZERO_PADDING_KEY = "zero-padding";
    @Getter
    private Properties props;
    private int maxPaddingSize;
    private int shardingCount;
    private boolean zeroPadding;
    private int startOffset;

    private int stopOffset;
    @Override
    public void init(final Properties props) {
        this.props = props;
        shardingCount = getShardingCount(props);
        maxPaddingSize = calculateMaxPaddingSize();
        zeroPadding = isZeroPadding(props);
        startOffset = getStartOffset(props);
        stopOffset = getStopOffset(props);
    }
    private int getShardingCount(final Properties props) {
        Preconditions.checkArgument(props.containsKey(SHARDING_COUNT_KEY), "Sharding count cannot be null.");
        return Integer.parseInt(props.getProperty(SHARDING_COUNT_KEY));
    }
    private int getStartOffset(final Properties props) {
        return Integer.parseInt(String.valueOf(props.getProperty(START_OFFSET_INDEX_KEY, "0")));
    }

    private int getStopOffset(final Properties props) {
        return Integer.parseInt(String.valueOf(props.getProperty(STOP_OFFSET_INDEX_KEY, "0")));
    }
    @Override
    public String getType() {
        return "CUSTOM";
    }
    private DataNodeInfo dataNodeInfo(final Collection<String> actualDataNodes) {
        String prefix = DATA_NODE_SUFFIX_PATTERN.matcher(actualDataNodes.iterator().next()).replaceAll("");
        int suffixMinLength = actualDataNodes.stream().map(each -> each.length() - prefix.length()).min(Comparator.comparing(Integer::intValue)).orElse(1);
        return new DataNodeInfo(prefix, suffixMinLength, DEFAULT_PADDING_CHAR);
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
        // 循环遍历分片列的值，比较分片列的值是否在分片规则中
        String sign = "";
        for (Map.Entry<String, Collection<Comparable<?>>> entry : shardingValuesMap.entrySet()) {
            Collection<Comparable<?>> shardingValues = entry.getValue();
            if (shardingValues != null && !shardingValues.isEmpty()) {
                String val = (String) shardingValues.toArray()[0];
                sign = val.substring(val.length() - 4);
                // 跳出循环，只要有一个分片列的值在分片规则中，就返回分片列的值对应的分片名称
                break;
            }
        }
        String suffix = getShardingResultSuffix(cutShardingValue(sign).mod(new BigInteger(String.valueOf(shardingCount))).toString());

        DataNodeInfo dataNodeInfo = dataNodeInfo(availableTargetNames);
        String result = dataNodeInfo.getPrefix() + Strings.padStart(suffix, dataNodeInfo.getSuffixMinLength(), dataNodeInfo.getPaddingChar());

        return Collections.singletonList(result);
    }
    private BigInteger cutShardingValue(final Comparable<?> shardingValue) {
        checkOffsetArgument(shardingValue);
        return 0 == startOffset && 0 == stopOffset ? getBigInteger(shardingValue) : new BigInteger(shardingValue.toString().substring(startOffset, shardingValue.toString().length() - stopOffset));
    }
    private BigInteger getBigInteger(final Comparable<?> value) {
        return value instanceof Number ? BigInteger.valueOf(((Number) value).longValue()) : new BigInteger(value.toString());
    }
    private void checkOffsetArgument(final Comparable<?> shardingValue) {
        Preconditions.checkArgument(startOffset >= 0, "Start offset can not be less than 0.");
        Preconditions.checkArgument(stopOffset >= 0, "Stop offset can not be less than 0.");
        Preconditions.checkArgument(shardingValue.toString().length() - stopOffset > startOffset, "Sharding value subtract stop offset can not be less than start offset.");
    }
    private boolean isZeroPadding(final Properties props) {
        return Boolean.parseBoolean(String.valueOf(props.getProperty(ZERO_PADDING_KEY, Boolean.FALSE.toString())));
    }
    private String getShardingResultSuffix(final String shardingResultSuffix) {
        return zeroPadding ? fillZero(shardingResultSuffix) : shardingResultSuffix;
    }
    private String fillZero(final String value) {
        return String.format("%0" + maxPaddingSize + "d", Integer.parseInt(value));
    }
    private int calculateMaxPaddingSize() {
        int result = 0;
        int calculatingShardingCount = shardingCount - 1;
        while (calculatingShardingCount != 0) {
            result++;
            calculatingShardingCount = calculatingShardingCount / 10;
        }
        return Math.max(result, 1);
    }
}
