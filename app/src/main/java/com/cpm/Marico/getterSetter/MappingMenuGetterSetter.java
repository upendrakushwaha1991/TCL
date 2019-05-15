
package com.cpm.Marico.getterSetter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MappingMenuGetterSetter {

    @SerializedName("Mapping_Menu")
    @Expose
    private List<MappingMenu> mappingMenu = null;

    public List<MappingMenu> getMappingMenu() {
        return mappingMenu;
    }

    public void setMappingMenu(List<MappingMenu> mappingMenu) {
        this.mappingMenu = mappingMenu;
    }

}
