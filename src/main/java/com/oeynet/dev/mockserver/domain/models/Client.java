package com.oeynet.dev.mockserver.domain.models;

public class Client {

    private String token;
    private String ip;
    private int port;
    private String version;
    private short yyId;
    private String accountNo;
    private String password;
    private String txPwd;
    private int clientId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public short getYyId() {
        return yyId;
    }

    public void setYyId(short yyId) {
        this.yyId = yyId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTxPwd() {
        return txPwd;
    }

    public void setTxPwd(String txPwd) {
        this.txPwd = txPwd;
    }
}
