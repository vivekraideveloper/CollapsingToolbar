package com.vijayjaidewan01vivekrai.dynamic_app.Models;

import com.google.gson.annotations.SerializedName;

public class Menu_items {

    @SerializedName("item")
    String item;

    @SerializedName("icon")
    String icon;

    @SerializedName("text_color")
    String text_color;

    @SerializedName("url")
    String url;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
