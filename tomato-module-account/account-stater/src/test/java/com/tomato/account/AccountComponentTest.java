package com.tomato.account;

import com.tomato.account.component.AccountComponent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * AccountComponent
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@SpringBootTest
public class AccountComponentTest {
    @Resource
    AccountComponent accountComponent;

    @Test
    public void receive() {
    }
}
