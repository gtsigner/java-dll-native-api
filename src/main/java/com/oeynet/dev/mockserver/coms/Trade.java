package com.oeynet.dev.mockserver.coms;

import com.oeynet.dev.mockserver.domain.models.TradeRet;
import com.oeynet.dev.mockserver.service.TradeApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Trade {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TradeApi tradeApi = new TradeApi();


    @PostConstruct
    public void init() {
        String ip = "113.105.125.27";
        String accountNo = "20500000103";
        String pwd = "265600";
        String msg = String.format("正在登录客户端：%s  账号:%s  密码:%s", ip, accountNo, pwd);
        logger.debug(msg);

        TradeRet ret = tradeApi.login(ip, (short) 7708, "6.00", (short) 1, "20500000103", "265600", "");
        System.out.println(ret.getErrInfo());
        if (ret.isSuccess()) {
            logger.warn("登录成功,客户端ID：" + tradeApi.getClientId());
        } else {
            logger.debug("登录失败：" + ret.getErrInfo());
            return;
        }
    }

    @PreDestroy
    public void uninit() {
        logger.debug("正在注销客户端....");
        tradeApi.logout();
    }

    public TradeApi getTradeApi() {
        return tradeApi;
    }
}
