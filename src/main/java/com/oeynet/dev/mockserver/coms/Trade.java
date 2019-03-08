package com.oeynet.dev.mockserver.coms;

import com.oeynet.dev.mockserver.domain.models.Client;
import com.oeynet.dev.mockserver.domain.models.TradeRet;
import com.oeynet.dev.mockserver.service.TradeApi;
import com.oeynet.dev.mockserver.utils.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class Trade {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, TradeApi> trades;

    /**
     * 构建
     */
    public Trade() {
        this.trades = new HashMap<String, TradeApi>();
    }

    /**
     * 获取api
     *
     * @param token token
     * @return TradeApi
     */
    public TradeApi getTradeApi(String token) {
        return trades.get(token);
    }


    @PostConstruct
    public void init() throws IOException {
        Map<String, Client> trades = ConfigUtil.getTrades();
        trades.forEach((key, trade) -> {
            //登录
            String msg = String.format("正在登录客户端：%s  账号:%s  密码:%s  Token:%s", trade.getIp(), trade.getAccountNo(), trade.getPassword(), trade.getToken());
            logger.info(msg);
            TradeApi tradeApi = new TradeApi();

            //登录
            TradeRet ret = tradeApi.login(trade.getIp(), (short) trade.getPort(), trade.getVersion(), trade.getYyId(), trade.getAccountNo(), trade.getPassword(), trade.getTxPwd());
            if (!ret.isSuccess()) {
                logger.info("客户端:" + trade.getAccountNo() + "登录失败,错误" + ret.getErrInfo());
                return;
            }
            logger.info("客户端:" + trade.getAccountNo() + "登录成功,ClientID：" + tradeApi.getClientId());

            //设置客户端
            trade.setClientId(tradeApi.getClientId());
            //设置配置
            tradeApi.setConfig(trade);
            //配置
            this.trades.put(trade.getToken(), tradeApi);
        });
        //登录完成
        logger.info("客户端已经登录完毕，共:" + trades.size() + "个");
    }

    @PreDestroy
    public void uninit() {
        logger.debug("正在注销所有客户端....");
        this.trades.forEach((k, v) -> {
            v.logout();
        });
    }
}
