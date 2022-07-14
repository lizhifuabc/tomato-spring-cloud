package com.tomato.account;

import com.tomato.account.component.AccountComponent;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.data.enums.CommonStatusEnum;
import com.tomato.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * AccountComponent
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@SpringBootTest
public class AccountComponentTest {
    @Resource
    private AccountComponent accountComponent;
    @Resource
    private AccountMapper accountMapper;
    @Test
    public void receive() {
        for (int i = 0; i < 100; i++) {
            AccountDO accountDO = accountMapper.selectByMerchantNo("1656659426508", CommonStatusEnum.YES.getCode());
            AccountReceiveReq accountReceiveReq = new AccountReceiveReq();
            accountReceiveReq.setMerchantNo("1656659426508");
            accountReceiveReq.setAmount(new BigDecimal(RandomUtil.randomInt(500)));
            accountReceiveReq.setThirdNo(RandomUtil.randomString(36));
            accountReceiveReq.setAccountHisType("支付");
            accountComponent.receive(accountReceiveReq,accountDO);
        }
    }
}
