package com.vijayjaidewan01vivekrai.dynamic_app.Models;

import com.google.gson.annotations.SerializedName;

public class ToolBar {

    @SerializedName("top_image")
        String top_image;
    @SerializedName("extended_top_title")
        String extended_top_title;
    @SerializedName("collapsed_top_title")
        String collapsed_top_title;
    @SerializedName("toolbar_bg")
        String toolbar_bg;
    @SerializedName("extended_top_title_color")
        String extended_top_title_color;
    @SerializedName("collapsed_top_title_color")
        String collapsed_top_title_color;
    @SerializedName("is_back")
        String is_back;
    @SerializedName("back_url")
        String back_url;
    @SerializedName("top_image_bg")
        String top_image_bg;
    @SerializedName("top_image_fg")
        String top_image_fg;

    public void setTop_image(String top_image) {
        this.top_image = top_image;
    }

    public void setExtended_top_title(String extended_top_title) {
        this.extended_top_title = extended_top_title;
    }

    public void setCollapsed_top_title(String collapsed_top_title) {
        this.collapsed_top_title = collapsed_top_title;
    }

    public void setToolbar_bg(String toolbar_bg) {
        this.toolbar_bg = toolbar_bg;
    }

    public void setExtended_top_title_color(String extended_top_title_color) {
        this.extended_top_title_color = extended_top_title_color;
    }

    public void setCollapsed_top_title_color(String collapsed_top_title_color) {
        this.collapsed_top_title_color = collapsed_top_title_color;
    }

    public void setIs_back(String is_back) {
        this.is_back = is_back;
    }

    public void setBack_url(String back_url) {
        this.back_url = back_url;
    }

    public void setTop_image_bg(String top_image_bg) {
        this.top_image_bg = top_image_bg;
    }

    public void setTop_image_fg(String top_image_fg) {
        this.top_image_fg = top_image_fg;
    }

    public String getTop_image() {

        return top_image;
    }

    public String getExtended_top_title() {
        return extended_top_title;
    }

    public String getCollapsed_top_title() {
        return collapsed_top_title;
    }

    public String getToolbar_bg() {
        return toolbar_bg;
    }

    public String getExtended_top_title_color() {
        return extended_top_title_color;
    }

    public String getCollapsed_top_title_color() {
        return collapsed_top_title_color;
    }

    public String getIs_back() {
        return is_back;
    }

    public String getTop_image_bg() {
        return top_image_bg;
    }

    public String getTop_image_fg() {
        return top_image_fg;
    }

    public String getBack_url() {
        return back_url;
    }
}
