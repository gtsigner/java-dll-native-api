package com.oeynet.dev.mockserver.controller;

import com.oeynet.dev.mockserver.coms.Trade;
import com.oeynet.dev.mockserver.domain.models.TradeRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/")
@RestController
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Trade trade;


    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String order(@RequestParam Map<String, String> map) {
        int Category = Integer.valueOf(map.get("Category"));
        String PriceType = map.get("PriceType");
        String Gddm = map.get("Gddm");
        String Zqdm = map.get("Zqdm");
        String Price = map.get("Price");
        String Quantity = map.get("Quantity");

        //发送订单信息

        TradeRet ret = trade.getTradeApi().sendOrder(Category, PriceType, Gddm, Zqdm, Price, Quantity);
        String info = String.format("发送订单-> 股东代码:%s  证券代码:%s  价格:%s  数量:%s 订单结果:%s", Gddm, Zqdm, Price, Quantity, ret.getErrInfo() + ret.getResult());
        logger.info(info);
        return ret.getResult() + ret.getErrInfo();
    }


    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancel(@RequestParam Map<String, String> map) {
        String Gddm = map.get("Gddm");
        String Hth = map.get("Hth");
        TradeRet ret = trade.getTradeApi().cancelOrder(Gddm, Hth);
        String info = String.format("取消订单-> 股东代码:%s  委托编号:%s  结果:%s", Gddm, Hth, ret.getErrInfo() + ret.getResult());
        logger.info(info);
        return ret.getResult() + ret.getErrInfo();
    }
}
