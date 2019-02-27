package com.oeynet.dev.mockserver.interfaces;

import com.sun.jna.Library;

public interface TradeLibrary extends Library {
    /**
     * 注销
     *
     * @param IP
     * @param Port
     * @param Version
     * @param YybID
     * @param AccountNo
     * @param JyPassword
     * @param TxPassword
     * @param ErrInfo
     * @return
     */
    public int Logon(String IP, short Port, String Version, short YybID, String AccountNo, String JyPassword, String TxPassword, byte[] ErrInfo);

    public void Logoff(int ClientID);

    public void GetQuote(int ClientID, String Zqdm, byte[] Result, byte[] ErrInfo);

    public void QueryDataByPage(int ClientID, int Category, byte[] PageID, byte[] Result, byte[] ErrInfo);

    public void QueryHistoryDataByPage(int ClientID, int Category, String StartDate, String EndDate, byte[] PageID, byte[] Result, byte[] ErrInfo);

    public void SendOrder(int ClientID, int Category, String PriceType, String Gddm, String Zqdm, String Price, String Quantity, byte[] Result, byte[] ErrInfo);

    public void CancelOrder(int ClientID, String Gddm, String hth, byte[] Result, byte[] ErrInfo);

    public void Repay(int ClientID, String Amount, byte[] Result, byte[] ErrInfo);
}
