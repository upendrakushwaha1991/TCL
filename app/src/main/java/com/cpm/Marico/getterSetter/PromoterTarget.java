package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoterTarget {
    @SerializedName("Store_Id")
    @Expose
    private Integer storeId;
    @SerializedName("MTD_Volume_Target")
    @Expose
    private Integer mTDVolumeTarget;
    @SerializedName("MTD_Value_Target")
    @Expose
    private Integer mTDValueTarget;
    @SerializedName("Daily_Volume_Target")
    @Expose
    private Integer dailyVolumeTarget;
    @SerializedName("Daily_Value_Target")
    @Expose
    private Integer dailyValueTarget;

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

    public Integer getMTDVolumeTarget() {
        return mTDVolumeTarget;
    }

    public void setMTDVolumeTarget(Integer mTDVolumeTarget) {
        this.mTDVolumeTarget = mTDVolumeTarget;
    }

    public Integer getMTDValueTarget() {
        return mTDValueTarget;
    }

    public void setMTDValueTarget(Integer mTDValueTarget) {
        this.mTDValueTarget = mTDValueTarget;
    }

    public Integer getDailyVolumeTarget() {
        return dailyVolumeTarget;
    }

    public void setDailyVolumeTarget(Integer dailyVolumeTarget) {
        this.dailyVolumeTarget = dailyVolumeTarget;
    }

    public Integer getDailyValueTarget() {
        return dailyValueTarget;
    }

    public void setDailyValueTarget(Integer dailyValueTarget) {
        this.dailyValueTarget = dailyValueTarget;
    }
}
