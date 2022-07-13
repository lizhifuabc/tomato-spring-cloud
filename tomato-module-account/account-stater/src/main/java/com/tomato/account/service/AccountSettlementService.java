package com.tomato.account.service;

import com.tomato.account.database.AccountDailyCollectMapper;
import com.tomato.account.database.dataobject.AccountDailyCollectDO;
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

    public AccountSettlementService(AccountDailyCollectMapper accountDailyCollectMapper) {
        this.accountDailyCollectMapper = accountDailyCollectMapper;
    }

    public void dailyCollect(String accountNo, LocalDate collectDate) {
        // TODO
        AccountDailyCollectDO accountDailyCollectDO = accountDailyCollectMapper.selectByAccountNoAndCollectDate(accountNo, collectDate);

    }
}
