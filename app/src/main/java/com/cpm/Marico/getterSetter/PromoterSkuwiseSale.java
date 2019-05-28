package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoterSkuwiseSale {
    @SerializedName("Store_Id")
    @Expose
    private Integer storeId;
    @SerializedName("Sku")
    @Expose
    private String sku;
    @SerializedName("Volume_Sale")
    @Expose
    private Integer volumeSale;
    @SerializedName("Value_Sale")
    @Expose
    private Integer valueSale;

    private String store_name;

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getVolumeSale() {
        return volumeSale;
    }

    public void setVolumeSale(Integer volumeSale) {
        this.volumeSale = volumeSale;
    }

    public Integer getValueSale() {
        return valueSale;
    }

    public void setValueSale(Integer valueSale) {
        this.valueSale = valueSale;
    }
}
