
package com.cpm.Marico.getterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MenuMaster implements Serializable {

    @SerializedName("Menu_Id")
    @Expose
    private Integer menuId;
    @SerializedName("Menu_Name")
    @Expose
    private String menuName;
    @SerializedName("Normal_Icon")
    @Expose
    private String normalIcon;
    @SerializedName("Tick_Icon")
    @Expose
    private String tickIcon;
    @SerializedName("Grey_Icon")
    @Expose
    private String greyIcon;
    @SerializedName("Menu_Path")
    @Expose
    private String menuPath;
    @SerializedName("Menu_Sequence")
    @Expose
    private Integer menuSequence;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getNormalIcon() {
        return normalIcon;
    }

    public void setNormalIcon(String normalIcon) {
        this.normalIcon = normalIcon;
    }

    public String getTickIcon() {
        return tickIcon;
    }

    public void setTickIcon(String tickIcon) {
        this.tickIcon = tickIcon;
    }

    public String getGreyIcon() {
        return greyIcon;
    }

    public void setGreyIcon(String greyIcon) {
        this.greyIcon = greyIcon;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Integer getMenuSequence() {
        return menuSequence;
    }

    public void setMenuSequence(Integer menuSequence) {
        this.menuSequence = menuSequence;
    }

}
