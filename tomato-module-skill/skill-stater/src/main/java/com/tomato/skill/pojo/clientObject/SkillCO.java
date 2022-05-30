package com.tomato.skill.pojo.clientObject;

import com.tomato.skill.database.dataobject.SkillActivityDO;
import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import lombok.Data;

/**
 * 秒杀信息
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@Data
public class SkillCO {
    private SkillActivityDO skillActivityDO;
    private SkillActivityRelationDO skillActivityRelationDO;
    private SkillActivityUserDO skillActivityUserDO;
}
