package com.tomato.account.component;

import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.service.AccountHisService;
import com.tomato.account.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 账户系统
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Component
public class AccountComponent {
    private final AccountService accountService;
    private final AccountHisService accountHisService;
    public AccountComponent(AccountService accountService, AccountHisService accountHisService) {
        this.accountService = accountService;
        this.accountHisService = accountHisService;
    }

    public void receive(AccountReceiveReq accountReceiveReq, AccountDO accountDO) {
        AccountHisDO accountHisDO = new AccountHisDO();
        // TODO ID 生成策略
        BeanUtils.copyProperties(accountReceiveReq, accountHisDO);
        accountHisDO.setAccountNo(accountDO.getAccountNo());
        accountHisService.create(accountHisDO);
    }
    public void exe(String accountNo,Long accountHisId){
        accountService.exe(accountHisId,accountNo);
    }
}
