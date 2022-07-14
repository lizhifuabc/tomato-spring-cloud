package com.tomato.account.service;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 账户历史服务
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Service
@Slf4j
public class AccountHisService {
    private final AccountHisMapper accountHisMapper;

    public AccountHisService(AccountHisMapper accountHisMapper) {
        this.accountHisMapper = accountHisMapper;
    }

    /**
     * 创建账户历史
     * @param accountHisDO
     */
    public void create (AccountHisDO accountHisDO){
        // TODO ID 生成策略
        // 账户系统属于内部服务，暂时不需要 redis 唯一性检查
        if (accountHisMapper.checkThirdNo(accountHisDO.getAccountNo(),accountHisDO.getThirdNo())){
            log.error("账户历史表已存在，不能重复插入:{}",accountHisDO);
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_EXIST);
        }
        accountHisMapper.insert(accountHisDO);
    }
}
