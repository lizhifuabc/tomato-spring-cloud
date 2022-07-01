package com.tomato.account.component;

import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisInsertDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.service.AccountService;
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
    public AccountComponent(AccountService accountService) {
        this.accountService = accountService;
    }

    public void receive(AccountReceiveReq accountReceiveReq, AccountDO accountDO) {
        AccountHisInsertDO accountHisInsertDO = new AccountHisInsertDO();
        // TODO ID 生成策略
//        accountHisInsertDO.setAccountHisId(System.currentTimeMillis());
        accountHisInsertDO.setAccountNo(accountDO.getAccountNo());
        accountHisInsertDO.setAmount(accountReceiveReq.getAmount());
        accountHisInsertDO.setThirdNo(accountReceiveReq.getThirdNo());
        accountHisInsertDO.setAccountHisType(accountReceiveReq.getAccountHisType());
        accountService.receive(accountHisInsertDO);
    }
    public void exe(String accountNo,Long accountHisId){
        accountService.exe(accountHisId);
    }
}
