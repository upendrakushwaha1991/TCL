package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoterSkuwiseSaleGetterSetter {

    @SerializedName("Promoter_Skuwise_Sale")
    @Expose
    private List<PromoterSkuwiseSale> promoterSkuwiseSale = null;

    public List<PromoterSkuwiseSale> getPromoterSkuwiseSale() {
        return promoterSkuwiseSale;
    }

    public void setPromoterSkuwiseSale(List<PromoterSkuwiseSale> promoterSkuwiseSale) {
        this.promoterSkuwiseSale = promoterSkuwiseSale;
    }
}
