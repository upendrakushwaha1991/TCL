package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoterTDPSaleTarget {
    @SerializedName("Store_Id")
    @Expose
    private Integer storeId;
    @SerializedName("Time_Period")
    @Expose
    private String timePeriod;
    @SerializedName("Volume_Target")
    @Expose
    private Integer volumeTarget;
    @SerializedName("Volume_Sale")
    @Expose
    private Integer volumeSale;

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

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Integer getVolumeTarget() {
        return volumeTarget;
    }

    public void setVolumeTarget(Integer volumeTarget) {
        this.volumeTarget = volumeTarget;
    }

    public Integer getVolumeSale() {
        return volumeSale;
    }

    public void setVolumeSale(Integer volumeSale) {
        this.volumeSale = volumeSale;
    }

}
