package com.tomato.account.controller;

import com.tomato.account.service.AccountCheckService;
import com.tomato.account.component.AccountComponent;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.data.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 账户
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountComponent accountComponent;

    public AccountController(AccountComponent accountComponent, AccountCheckService accountCheckService) {
        this.accountComponent = accountComponent;
        this.accountCheckService = accountCheckService;
    }

    private final AccountCheckService accountCheckService;
    @PostMapping("/receive")
    public Response receive(@Valid @RequestBody AccountReceiveReq accountReceiveReq){
        AccountDO accountDO = accountCheckService.checkReceive(accountReceiveReq);
        accountComponent.receive(accountReceiveReq,accountDO);
        return Response.buildSuccess();
    }
}
