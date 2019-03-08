package com.oeynet.dev.mockserver.trade;

import com.oeynet.dev.mockserver.domain.models.Client;
import com.oeynet.dev.mockserver.utils.ConfigUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ConfigTest {

    @Test
    public void configTest() throws IOException {
        Map<String, Client> trades = ConfigUtil.getTrades();
        trades.forEach((key, trade) -> {
            System.out.println("账号：" + trade.getAccountNo());
        });

    }
}
