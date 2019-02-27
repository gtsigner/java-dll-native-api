package com.oeynet.dev.mockserver.service;

import com.oeynet.dev.mockserver.domain.models.TradeRet;
import com.oeynet.dev.mockserver.interfaces.TradeLibrary;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class TradeApi {

    private static TradeLibrary tdxApi = Native.loadLibrary("static/trade", TradeLibrary.class);
    private int clientId = -1;


    private String errInfo = "";
    private String result = "";
    private static final String DLL_CHARSET = "GBK";

    /**
     * 客户端进行登录
     *
     * @param ip
     * @param port
     * @param version
     * @param yyId
     * @param accountNo
     * @param pwd
     * @param txPwd
     * @return TradeRet
     */
    public TradeRet login(String ip, short port, String version, short yyId, String accountNo, String pwd, String txPwd) {
        TradeRet ret = new TradeRet();
        byte[] errInfo = new byte[256];
        this.clientId = tdxApi.Logon(ip, port, version, yyId, accountNo, pwd, txPwd, errInfo);
        if (this.clientId != -1) {
            ret.setData(clientId);
            ret.setSuccess(true);
        }
        ret.setErrInfo(Native.toString(errInfo, DLL_CHARSET));
        return ret;
    }

    /**
     * 发送订单信息
     *
     * @param category
     * @param priceType
     * @param zqdm
     * @param price
     * @param quantity
     * @return
     */
    public TradeRet sendOrder(int category, String priceType, String gddm, String zqdm, String price, String quantity) {
        TradeRet ret = new TradeRet();
        byte[] errInfo = new byte[256];
        byte[] result = new byte[1024 * 1024];
        tdxApi.SendOrder(clientId, category, priceType, gddm, zqdm, price, quantity, result, errInfo);
        ret.setErrInfo(Native.toString(errInfo, DLL_CHARSET));
        ret.setResult(Native.toString(result, DLL_CHARSET));
        if (!ret.getResult().equals("")) {
            ret.setSuccess(true);
        }
        return ret;
    }

    /**
     * 取消订单
     *
     * @param gddm
     * @param hth
     * @return
     */
    public TradeRet cancelOrder(String gddm, String hth) {
        TradeRet ret = new TradeRet();
        byte[] errInfo = new byte[256];
        byte[] result = new byte[1024 * 1024];
        tdxApi.CancelOrder(clientId, gddm, hth, result, errInfo);
        ret.setErrInfo(Native.toString(errInfo, DLL_CHARSET));
        ret.setResult(Native.toString(result, DLL_CHARSET));
        if (!ret.getResult().equals("")) {
            ret.setSuccess(true);
        }
        return ret;
    }

    /**
     * 注销登录
     */
    public void logout() {
        tdxApi.Logoff(this.clientId);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

//
//    //DLL是32位的,因此必须使用jdk32位开发,才能调用DLL;
//    //必须把Trade.dll复制到java工程目录下;
//    //java工程必须添加引用 jna.jar, 在 https://github.com/twall/jna 下载 jna.jar
//    //无论用什么语言编程，都必须仔细阅读VC版内的关于DLL导出函数的功能和参数含义说明，不仔细阅读完就提出问题者因时间精力所限，恕不解答。
//
//
//
//    //登录
//
//    //登录第二个帐号
//    //int ClientID2=TdxLibrary1.Logon("111.111.111.111", (short)7708, "7.06", (short)0, "2222222222", "222222", "111", ErrInfo);
//            if(ClientID ==-1)
//
//    {
//        System.out.println(Native.toString(ErrInfo, "GBK"));
//        return;
//    }
//
//            System.out.println("登录成功");
//
//            TdxLibrary1.GetQuote(ClientID,"000001",Result,ErrInfo);
//            System.out.println(Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//
//    byte[] PageID = Arrays.copyOf(Native.toByteArray("P", "GBK"), 256);
//    //此时PageID传入值为P,表示查询第一页,函数执行完毕后PageID值发生改变,变为下一页的ID,如没有下一页则PageID值为P或空
//            TdxLibrary1.QueryDataByPage(ClientID,2,PageID,Result,ErrInfo);
//            System.out.println(Native.toString(PageID,"GBK")+Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//    //此时PageID传入值为第二页的ID,表示查询第二页,函数执行完毕后PageID值发生改变,变为下一页的ID,如没有下一页则PageID值为P或空
//            TdxLibrary1.QueryDataByPage(ClientID,2,PageID,Result,ErrInfo);
//            System.out.println(Native.toString(PageID,"GBK")+Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//
//
//    PageID =Arrays.copyOf(Native.toByteArray("P","GBK"),256);
//            TdxLibrary1.QueryHistoryDataByPage(ClientID,0,"20180101","20180901",PageID,Result,ErrInfo);
//            System.out.println(Native.toString(PageID,"GBK")+Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//
//
//            TdxLibrary1.SendOrder(ClientID,0,"0","A11111111","600000","17.00","100",Result,ErrInfo);
//            System.out.println(Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//            TdxLibrary1.CancelOrder(ClientID,"A11111111","64400",Result,ErrInfo);
//            System.out.println(Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));
//            TdxLibrary1.Repay(ClientID,"1000000",Result,ErrInfo);
//            System.out.println(Native.toString(Result,"GBK")+Native.toString(ErrInfo,"GBK"));

}
