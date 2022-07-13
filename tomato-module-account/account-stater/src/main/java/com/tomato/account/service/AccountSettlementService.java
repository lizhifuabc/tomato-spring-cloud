package com.tomato.account.service;

import com.tomato.account.database.AccountDailyCollectMapper;
import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountDailyCollectDO;
import com.tomato.account.database.dataobject.AccountHisDailyCollectDO;
import com.tomato.account.database.dataobject.AccountHisDailyCollectRepDO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 结算
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@Service
public class AccountSettlementService {
    private final AccountDailyCollectMapper accountDailyCollectMapper;
    private final AccountMapper accountMapper;
    private final AccountHisMapper accountHisMapper;
    public AccountSettlementService(AccountDailyCollectMapper accountDailyCollectMapper, AccountMapper accountMapper, AccountHisMapper accountHisMapper) {
        this.accountDailyCollectMapper = accountDailyCollectMapper;
        this.accountMapper = accountMapper;
        this.accountHisMapper = accountHisMapper;
    }

    public void dailyCollect(String accountNo, LocalDate collectDate) {
        AccountDO accountDO = accountMapper.selectByAccountNo(accountNo);
        AccountDailyCollectDO accountDailyCollectDO = accountDailyCollectMapper.selectByAccountNoAndCollectDate(accountNo, collectDate);
        if (accountDailyCollectDO == null) {
            AccountHisDailyCollectDO accountHisDailyCollectDO = new AccountHisDailyCollectDO();
            accountHisDailyCollectDO.setAccountNo(accountNo);
            accountHisDailyCollectDO.setRiskDay(accountDO.getRiskDay());
            accountHisDailyCollectDO.setCollectDate(collectDate);

            AccountHisDailyCollectRepDO accountHisDailyCollectRepDO = accountHisMapper.dailyCollect(accountHisDailyCollectDO);

            accountDailyCollectDO = new AccountDailyCollectDO();
            accountDailyCollectDO.setAccountNo(accountNo);
            accountDailyCollectDO.setCollectDate(collectDate);
            accountDailyCollectDO.setTotalAmount(accountHisDailyCollectRepDO.getTotalAmount());
            accountDailyCollectDO.setTotalCount(accountHisDailyCollectRepDO.getTotalCount());
            accountDailyCollectDO.setRiskDay(accountDO.getRiskDay());

            accountDailyCollectMapper.insert(accountDailyCollectDO);
        }
    }
}
