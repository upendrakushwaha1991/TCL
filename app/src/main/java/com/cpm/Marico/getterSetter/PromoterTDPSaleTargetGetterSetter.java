package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoterTDPSaleTargetGetterSetter {
    @SerializedName("Promoter_TDP_SaleTarget")
    @Expose
    private List<PromoterTDPSaleTarget> promoterTDPSaleTarget = null;

    public List<PromoterTDPSaleTarget> getPromoterTDPSaleTarget() {
        return promoterTDPSaleTarget;
    }

    public void setPromoterTDPSaleTarget(List<PromoterTDPSaleTarget> promoterTDPSaleTarget) {
        this.promoterTDPSaleTarget = promoterTDPSaleTarget;
    }
}
