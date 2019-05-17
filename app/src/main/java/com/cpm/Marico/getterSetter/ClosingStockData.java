package com.cpm.Marico.getterSetter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ClosingStockData implements Serializable {

    HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> hashMapData;
    List<StockNewGetterSetter> stockList;

    public HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> getHashMapData() {
        return hashMapData;
    }

    public void setHashMapData(HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> hashMapData) {
        this.hashMapData = hashMapData;
    }

    public List<StockNewGetterSetter> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockNewGetterSetter> stockList) {
        this.stockList = stockList;
    }
}
