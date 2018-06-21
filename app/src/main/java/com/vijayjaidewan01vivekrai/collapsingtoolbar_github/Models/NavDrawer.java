package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NavDrawer {

    @SerializedName("header_layout")
        Menu_header header_layout;
    @SerializedName("menu_items")
        ArrayList<Menu_items> menu_items;
    @SerializedName("nav_drawer_bg_color")
        String nav_drawer_bg_color;

    public void setHeader_layout(Menu_header header_layout) {
        this.header_layout = header_layout;
    }

    public void setMenu_items(ArrayList<Menu_items> menu_items) {
        this.menu_items = menu_items;
    }

    public void setNav_drawer_bg_color(String nav_drawer_bg_color) {
        this.nav_drawer_bg_color = nav_drawer_bg_color;
    }

    public Menu_header getHeader_layout() {
        return header_layout;
    }

    public ArrayList<Menu_items> getMenu_items() {
        return menu_items;
    }

    public String getNav_drawer_bg_color() {
        return nav_drawer_bg_color;
    }
}
