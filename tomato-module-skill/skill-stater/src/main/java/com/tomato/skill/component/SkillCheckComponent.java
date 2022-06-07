package com.tomato.skill.component;

import com.tomato.skill.database.dataobject.SkillActivityDO;
import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import com.tomato.skill.dto.SkillReq;
import com.tomato.skill.pojo.SkillMultiInfo;
import com.tomato.skill.service.SkillCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取秒杀资格
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Component
@Slf4j
public class SkillCheckComponent {
    private final SkillCheckService skillCheckService;
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    public SkillCheckComponent(SkillCheckService skillCheckService) {
        this.skillCheckService = skillCheckService;
    }

    /**
     * 获取秒杀资格校验 TODO 异常处理
     * @param skillReq
     * @return
     */
    public SkillMultiInfo checkSkill(SkillReq skillReq) {
        log.info("获取秒杀资格skillReq:{}", skillReq);
        SkillMultiInfo skillMultiInfo = new SkillMultiInfo();
        CompletableFuture<SkillActivityDO> skillActivityFuture = CompletableFuture.supplyAsync(() -> {
            SkillActivityDO skillActivityDO = skillCheckService.checkSkillActivity(skillReq.getActivityId());
            log.info("获取秒杀资格skillActivityDO:{}", skillActivityDO);
            skillMultiInfo.setSkillActivityDO(skillActivityDO);
            return skillActivityDO;
        }, threadPool);

        CompletableFuture<SkillActivityRelationDO> skillActivityRelationFuture = CompletableFuture.supplyAsync(() -> {
            SkillActivityRelationDO skillActivityRelationDO = skillCheckService.checkSkillActivityRelation(skillReq.getActivityRelationId());
            log.info("获取秒杀资格skillActivityUserDO:{}", skillActivityRelationDO);
            skillMultiInfo.setSkillActivityRelationDO(skillActivityRelationDO);
            return skillActivityRelationDO;
        }, threadPool);

        CompletableFuture<SkillActivityUserDO> skillActivityUserFuture = skillActivityRelationFuture.thenApplyAsync(skillActivityRelationDO -> {
            SkillActivityUserDO skillActivityUserDO = skillCheckService.checkSkillActivityUser(skillReq.getActivityRelationId(), skillReq.getUserId(), skillActivityRelationDO.getSkillLimit());
            log.info("获取秒杀资格skillActivityUserDO:{}", skillActivityUserDO);
            skillMultiInfo.setSkillActivityUserDO(skillActivityUserDO);
            return skillActivityUserDO;
        }, threadPool);

        CompletableFuture.allOf(skillActivityFuture, skillActivityUserFuture).join();
        log.info("获取秒杀资格skillCO:{}", skillMultiInfo);
        return skillMultiInfo;
    }
}
