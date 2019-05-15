
package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BrandMaster implements Serializable {

    @SerializedName("Brand_Id")
    @Expose
    private Integer brandId;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("Sub_Category_Id")
    @Expose
    private Integer subcategoryId;
    @SerializedName("Brand_Sequence")
    @Expose
    private Integer brandSequence;

    public Integer getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(Integer company_Id) {
        this.company_Id = company_Id;
    }

    @SerializedName("Company_Id")
    @Expose
    private Integer company_Id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getBrandSequence() {
        return brandSequence;
    }

    public void setBrandSequence(Integer brandSequence) {
        this.brandSequence = brandSequence;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String quantity;

    //---------------

    String img_close_up = "";
    String img_long_shot = "";

    String answered;
    int answered_id = -1;

    int nonExecutionReasonId = -1;

    public int getKey_Id() {
        return key_Id;
    }

    public void setKey_Id(int key_Id) {
        this.key_Id = key_Id;
    }

    int key_Id;

    public String getImg_close_up() {
        return img_close_up;
    }

    public void setImg_close_up(String img_close_up) {
        this.img_close_up = img_close_up;
    }

    public String getImg_long_shot() {
        return img_long_shot;
    }

    public void setImg_long_shot(String img_long_shot) {
        this.img_long_shot = img_long_shot;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public int getAnswered_id() {
        return answered_id;
    }

    public void setAnswered_id(int answered_id) {
        this.answered_id = answered_id;
    }

    public int getNonExecutionReasonId() {
        return nonExecutionReasonId;
    }

    public void setNonExecutionReasonId(int nonExecutionReasonId) {
        this.nonExecutionReasonId = nonExecutionReasonId;
    }
    private String store_id;
    private String display;
    private String display_Id;
    private String image1="",image2="";
    private String present= "";
    int reasonId;
    String reason;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay_Id() {
        return display_Id;
    }

    public void setDisplay_Id(String display_Id) {
        this.display_Id = display_Id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
