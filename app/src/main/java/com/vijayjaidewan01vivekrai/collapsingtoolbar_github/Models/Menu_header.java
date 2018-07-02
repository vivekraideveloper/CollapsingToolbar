package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models;

import com.google.gson.annotations.SerializedName;

public class Menu_header {

    @SerializedName("text")
    String text;

    @SerializedName("image")
    String image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
