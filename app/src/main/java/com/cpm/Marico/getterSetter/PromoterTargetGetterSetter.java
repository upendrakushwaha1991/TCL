package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoterTargetGetterSetter {

    @SerializedName("Promoter_Target")
    @Expose
    private List<PromoterTarget> promoterTarget = null;

    public List<PromoterTarget> getPromoterTarget() {
        return promoterTarget;
    }

    public void setPromoterTarget(List<PromoterTarget> promoterTarget) {
        this.promoterTarget = promoterTarget;
    }
}
