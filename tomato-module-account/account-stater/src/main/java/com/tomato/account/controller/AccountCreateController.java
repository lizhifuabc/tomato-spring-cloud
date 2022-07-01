package com.tomato.account.controller;

import com.tomato.account.dto.AccountCreateReq;
import com.tomato.account.service.AccountCreateService;
import com.tomato.data.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 账户创建
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@RestController
@RequestMapping("/account")
public class AccountCreateController {
    private final AccountCreateService accountCreateService;

    public AccountCreateController(AccountCreateService accountCreateService) {
        this.accountCreateService = accountCreateService;
    }

    @PostMapping("/create")
    public Response create(@Valid @RequestBody AccountCreateReq accountCreateReq) {
        accountCreateService.create(accountCreateReq);
        return Response.buildSuccess();
    }
}
