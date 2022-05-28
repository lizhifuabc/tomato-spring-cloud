//package com.tomato.skill.preheat;
//
//import com.tomato.redis.util.RedisUtils;
//import com.tomato.skill.database.dataobject.SkillSkuRelationDO;
//import org.springframework.stereotype.Component;
//
///**
// * 活动预热组件
// *
// * @author lizhifu
// * @date 2022/5/28
// */
//@Component
//public class PreheatComponent {
//    private static final String PREHEAT_KEY = "preheat:";
//    /**
//     * 单个插槽数据
//     */
//    private static final Integer SLOT_SIZE = 200;
//    private final RedisUtils redisUtils;
//
//    public PreheatComponent(RedisUtils redisUtils) {
//        this.redisUtils = redisUtils;
//    }
//
//    /**
//     * 活动预热
//     * 原来的商品的id为10001，库存为1000件，在Redis中的存储为(10001, 1000)，我们将原有的库存分割为5份，则每份的库存
//     * 为200件，此时，我们在Redia中存储的信息为(10001_0, 200)，(10001_1, 200)，(10001_2, 200)，(10001_3, 200)，(10001_4,
//     * 200)。
//     * 此时，我们将库存进行分割后，每个分割后的库存使用商品id加上一个数字标识来存储，这样，在对存储商品库存的每个Key进行
//     * Hash运算时，得出的Hash结果是不同的，这就说明，存储商品库存的Key有很大概率不在Redis的同一个槽位中，这就能够提升
//     * Redis处理请求的性能和并发量。
//     *
//     * 在Redis中存储一份商品id和分割库存后的Key的映射关系，此时映射关系的Key为商品的id，也就是
//     * 10001，Value为分割库存后存储库存信息的Key，也就是10001_0，10001_1，10001_2，10001_3，10001_4。在Redis中我们可
//     * 以使用List来存储这些值。
//     *
//     * 在真正处理库存信息时，我们可以先从Redis中查询出商品对应的分割库存后的所有Key，同时使用AtomicLong来记录当前的请求数
//     * 量，使用请求数量对从Redia中查询出的商品对应的分割库存后的所有Key的长度进行求模运算，得出的结果为0，1，2，3，4。再
//     * 在前面拼接上商品id就可以得出真正的库存缓存的Key。此时，就可以根据这个Key直接到Redis中获取相应的库存信息。
//     * 同时，我们可以将分隔的不同的库存数据分别存储到不同的Redis服务器中，进一步提升Redis的并发量。
//     *
//     * @param skillSkuRelationDO 秒杀信息
//     * @return 是否预热成功
//     */
//    public boolean preheat(SkillSkuRelationDO skillSkuRelationDO) {
//        String key = PREHEAT_KEY + skillSkuRelationDO.getSkuId();
//        redisUtils.del(key);
//        Integer seckillCount = skillSkuRelationDO.getSkillCount();
//        for (int i = 1; i <= seckillCount; i++) {
//            redisUtils.lSet(key, i);
//        }
//        return true;
//    }
//
//    /**
//     * size
//     *
//     * @param skillSkuRelationDO 秒杀信息
//     * @return size
//     */
//    public long isPreheat(SkillSkuRelationDO skillSkuRelationDO) {
//        long size = redisUtils.lGetListSize(PREHEAT_KEY + skillSkuRelationDO.getSkuId());
//        return size;
//    }
//}
