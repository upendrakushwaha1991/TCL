package com.cpm.Marico.getterSetter;



public class StoreProfileGetterSetter {

    String store_type="";
    String store_type_cd;
    String StoreCd;
    String Store_name;
    String Store_addres;
    String Store_city;
    String Store_visit_date;

    public String getStore_name() {
        return Store_name;
    }

    public void setStore_name(String store_name) {
        Store_name = store_name;
    }

    public String getStore_addres() {
        return Store_addres;
    }

    public void setStore_addres(String store_addres) {
        Store_addres = store_addres;
    }

    public String getStore_city() {
        return Store_city;
    }

    public void setStore_city(String store_city) {
        Store_city = store_city;
    }

    public String getStore_visit_date() {
        return Store_visit_date;
    }

    public void setStore_visit_date(String store_visit_date) {
        Store_visit_date = store_visit_date;
    }

    public String getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type = store_type;
    }

    public String getStore_type_cd() {
        return store_type_cd;
    }

    public void setStore_type_cd(String store_type_cd) {
        this.store_type_cd = store_type_cd;
    }

    public String getStoreCd() {
        return StoreCd;
    }

    public void setStoreCd(String storeCd) {
        StoreCd = storeCd;
    }
}
