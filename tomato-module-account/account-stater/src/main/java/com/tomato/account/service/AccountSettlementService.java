package com.tomato.account.service;

import com.tomato.account.database.AccountDailyCollectMapper;
import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountDailyCollectDO;
import com.tomato.account.database.dataobject.AccountHisDailyCollectDO;
import com.tomato.account.database.dataobject.AccountHisDailyCollectRepDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import com.tomato.data.enums.CommonStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 结算
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@Service
@Slf4j
public class AccountSettlementService {
    private final AccountDailyCollectMapper accountDailyCollectMapper;
    private final AccountMapper accountMapper;
    private final AccountHisMapper accountHisMapper;
    private final AccountBalanceDirectService accountBalanceDirectService;
    public AccountSettlementService(AccountDailyCollectMapper accountDailyCollectMapper, AccountMapper accountMapper, AccountHisMapper accountHisMapper, AccountBalanceDirectService accountBalanceDirectService) {
        this.accountDailyCollectMapper = accountDailyCollectMapper;
        this.accountMapper = accountMapper;
        this.accountHisMapper = accountHisMapper;
        this.accountBalanceDirectService = accountBalanceDirectService;
    }

    /**
     * 每日待结算汇总
     * @param accountNo
     * @param collectDate
     */
    @Transactional(rollbackFor = Exception.class)
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

            // 更新日汇总账户待结算历史记录
            int res = accountHisMapper.updateDailyCollect(accountHisDailyCollectDO);
            // 更新条数 汇总条数 必须一致
            if (res != accountDailyCollectDO.getTotalCount()){
                throw new AccountException(AccountResponseCode.ACCOUNT_DAILY_COLLECT_FAIL);
            }
        }
    }

    /**
     * 发起结算
     * @param accountNo
     * @param collectDate
     */
    @Transactional(rollbackFor = Exception.class)
    public void settlement(String accountNo, LocalDate collectDate) {
        log.info("发起结算 accountNo={}, collectDate={}", accountNo, collectDate);
        AccountDailyCollectDO accountDailyCollectDO = accountDailyCollectMapper.selectByAccountNoAndCollectDate(accountNo, collectDate);
        if (accountDailyCollectDO == null) {
            log.error("结算失败,日汇总账户待结算记录为空 accountNo={}, collectDate={}", accountNo, collectDate);
            return;
        }
        log.info("发起结算 accountNo={}, collectDate={}, accountDailyCollectDO={}", accountNo, collectDate, accountDailyCollectDO);
        if (accountDailyCollectDO.getSettStatus().equals(CommonStatusEnum.YES.getCode())) {
            return;
        }
        if (accountDailyCollectDO.getTotalCount() == 0 || accountDailyCollectDO.getTotalAmount().compareTo(new BigDecimal(0)) == 0) {
            // 日汇总账户待结算记录为空 或者 日汇总账户待结算记录总金额为0,不需要结算，更新状态即可
            // 不用判断乐观锁
            accountDailyCollectMapper.updateSettStatus(accountNo, collectDate, accountDailyCollectDO.getVersion());
            return;
        }
        // 更新账户余额
        AccountDO accountDO = accountMapper.selectByAccountNo(accountNo);

        AccountReceiveReq accountReceiveReq = new AccountReceiveReq();
        accountReceiveReq.setMerchantNo(accountDO.getMerchantNo());
        accountReceiveReq.setAccountHisType("结算扣款");
        accountReceiveReq.setAmount(accountDailyCollectDO.getTotalAmount().negate());
        accountReceiveReq.setThirdNo(accountDailyCollectDO.getAccountDailyCollectId().toString());

        accountBalanceDirectService.balanceDirect(accountReceiveReq);

        // 更新结算状态
        int i = accountDailyCollectMapper.updateSettStatus(accountNo, collectDate, accountDailyCollectDO.getVersion());
        if (i != 1) {
            throw new AccountException(AccountResponseCode.ACCOUNT_DAILY_COLLECT_UPDATE_FAIL);
        }
    }

    public void updateRemark(String accountNo, LocalDate collectDate, String remark) {
        try {
            accountDailyCollectMapper.updateRemark(accountNo, collectDate, remark);
        }catch (Exception e){
            // 忽略异常
            log.error("更新备注失败 accountNo={}, collectDate={}, remark={}", accountNo, collectDate, remark);
        }
    }
}
