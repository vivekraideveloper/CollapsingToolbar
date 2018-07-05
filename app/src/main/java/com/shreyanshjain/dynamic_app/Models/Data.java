package com.shreyanshjain.dynamic_app.Models;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("heading")
    String text1;
    @SerializedName("sub_heading")
    String text2;
    @SerializedName("description")
    String text3;
    @SerializedName("text_header_color")
    String text_header_color;
    @SerializedName("tex_subheader_color")
    String text_subheader_color;
    @SerializedName("text_description_color")
    String text_description_color;
    @SerializedName("text_header_size")
    String text_header_size;
    @SerializedName("tex_subheader_size")
    String text_subheader_size;
    @SerializedName("text_description_size")
    String text_description_size;
    @SerializedName("bg_color")
    String bg_color;
    @SerializedName("id")
    int id;
    @SerializedName("image")
    String image;
    @SerializedName("url")
    String url;

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText_header_color(String text_header_color) {
        this.text_header_color = text_header_color;
    }

    public void setText_subheader_color(String tex_subheader_color) {
        this.text_subheader_color = tex_subheader_color;
    }

    public void setText_description_color(String text_description_color) {
        this.text_description_color = text_description_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public void setText_header_size(String text_header_size) {
        this.text_header_size = text_header_size;
    }

    public void setText_subheader_size(String tex_subheader_size) {
        this.text_subheader_size = tex_subheader_size;
    }

    public void setText_description_size(String text_description_size) {
        this.text_description_size = text_description_size;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public String getText_header_color() {
        return text_header_color;
    }

    public String getText_subheader_color() {
        return text_subheader_color;
    }

    public String getText_description_color() {
        return text_description_color;
    }

    public String getBg_color() {
        return bg_color;
    }

    public String getText_header_size() {
        return text_header_size;
    }

    public String getText_subheader_size() {
        return text_subheader_size;
    }

    public String getText_description_size() {
        return text_description_size;
    }
}
