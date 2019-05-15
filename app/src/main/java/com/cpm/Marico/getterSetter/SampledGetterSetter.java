package com.cpm.Marico.getterSetter;

import java.util.ArrayList;

/**
 * Created by upendra on 20-02-2019.
 */

public class SampledGetterSetter {
    String category="";
    String category_cd="";
    String sku="";
    String mobile="";
    String name="";
    String SAMPLE_CD="";
    String SAMPLE="";
    String FEEDBACK="";
    boolean isExists;

    ArrayList<SamplingChecklist> samplingChecklistData = new ArrayList<>();

    public ArrayList<SamplingChecklist> getSamplingChecklistData() {
        return samplingChecklistData;
    }

    public void setSamplingChecklistData(ArrayList<SamplingChecklist> samplingChecklistData) {
        this.samplingChecklistData = samplingChecklistData;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    String remark="";

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFEEDBACK() {
        return FEEDBACK;
    }

    public void setFEEDBACK(String FEEDBACK) {
        this.FEEDBACK = FEEDBACK;
    }

    public String getSAMPLE_CD() {
        return SAMPLE_CD;
    }

    public void setSAMPLE_CD(String SAMPLE_CD) {
        this.SAMPLE_CD = SAMPLE_CD;
    }

    public String getSAMPLE() {
        return SAMPLE;
    }

    public void setSAMPLE(String SAMPLE) {
        this.SAMPLE = SAMPLE;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    String key_id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_cd() {
        return category_cd;
    }

    public void setCategory_cd(String category_cd) {
        this.category_cd = category_cd;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku_cd() {
        return sku_cd;
    }

    public void setSku_cd(String sku_cd) {
        this.sku_cd = sku_cd;
    }

    public String getSampled() {
        return sampled;
    }

    public void setSampled(String sampled) {
        this.sampled = sampled;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSampled_img() {
        return sampled_img;
    }

    public void setSampled_img(String sampled_img) {
        this.sampled_img = sampled_img;
    }

    String sku_cd="";
    String sampled="";
    String feedback="";
    String sampled_img="";
    String email= "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
