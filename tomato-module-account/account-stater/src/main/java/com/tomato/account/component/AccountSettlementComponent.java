package com.tomato.account.component;

import com.tomato.account.service.AccountSettlementService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 账户结算相关
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Component
public class AccountSettlementComponent {
    private final AccountSettlementService accountSettlementService;

    public AccountSettlementComponent(AccountSettlementService accountSettlementService) {
        this.accountSettlementService = accountSettlementService;
    }
    /**
     * 每日待结算汇总
     * @param accountNo
     * @param collectDate
     */
    public void dailyCollect(String accountNo, LocalDate collectDate) {
        accountSettlementService.dailyCollect(accountNo, collectDate);
    }
    /**
     * 发起结算
     * @param accountNo
     * @param collectDate
     */
    public void settlement(String accountNo, LocalDate collectDate) {
        try {
            accountSettlementService.settlement(accountNo, collectDate);
        }catch (Exception e) {
            accountSettlementService.updateRemark(accountNo, collectDate, e.getMessage());
        }
        // TODO 发起打款中间状态
        // TODO 发起打款

    }
}
