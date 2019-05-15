
package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MappingPaidVisibility {

    @SerializedName("Store_id")
    @Expose
    private Integer storeId;
    @SerializedName("Brand_Id")
    @Expose
    private Integer brandId;
    @SerializedName("Display_Id")
    @Expose
    private Integer displayId;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getDisplayId() {
        return displayId;
    }

    public void setDisplayId(Integer displayId) {
        this.displayId = displayId;
    }

}
