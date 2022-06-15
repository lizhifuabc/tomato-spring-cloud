package com.tomato.account.controller;

import com.tomato.account.component.AccountCheckComponent;
import com.tomato.account.component.AccountComponent;
import com.tomato.account.pojo.AccountReceiveReq;
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

    public AccountController(AccountComponent accountComponent, AccountCheckComponent accountCheckComponent) {
        this.accountComponent = accountComponent;
        this.accountCheckComponent = accountCheckComponent;
    }

    private final AccountCheckComponent accountCheckComponent;
    @PostMapping("/receive")
    public Response receive(@Valid @RequestBody AccountReceiveReq accountReceiveReq){
        accountCheckComponent.checkReceive(accountReceiveReq);
        accountComponent.receive(accountReceiveReq);
        return Response.buildSuccess();
    }
}
