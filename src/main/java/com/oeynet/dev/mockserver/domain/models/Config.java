package com.oeynet.dev.mockserver.domain.models;

import java.util.ArrayList;

public class Config {

    private ArrayList<Client> trades = new ArrayList<>();

    public ArrayList<Client> getTrades() {
        return trades;
    }

    public void setTrades(ArrayList<Client> trades) {
        this.trades = trades;
    }
}
