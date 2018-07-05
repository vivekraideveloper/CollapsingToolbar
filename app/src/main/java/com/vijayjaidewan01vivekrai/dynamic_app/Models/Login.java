package com.vijayjaidewan01vivekrai.dynamic_app.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Login implements Serializable{

    @SerializedName("activity_bg_color")
        String activity_bg_color;
    @SerializedName("card_bg_color")
        String card_bg_color;
    @SerializedName("background_image")
        String background_image;
    @SerializedName("profile_image")
        String profile_image;
    @SerializedName("button_text")
        String button_text;
    @SerializedName("button_text_color")
        String button_text_color;
    @SerializedName("button_bg_color")
        String button_bg_color;
    @SerializedName("edit_text_bg")
        String edit_text_bg;
    @SerializedName("input_box1")
        String input_box1;
    @SerializedName("input_box2")
        String input_box2;
    @SerializedName("login_url")
        String login_url;
    @SerializedName("alpha")
        String alpha;

    public String getActivity_bg_color() {
        return activity_bg_color;
    }

    public void setActivity_bg_color(String activity_bg_color) {
        this.activity_bg_color = activity_bg_color;
    }

    public String getCard_bg_color() {
        return card_bg_color;
    }

    public void setCard_bg_color(String card_bg_color) {
        this.card_bg_color = card_bg_color;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getButton_text() {
        return button_text;
    }

    public void setButton_text(String button_text) {
        this.button_text = button_text;
    }

    public String getButton_text_color() {
        return button_text_color;
    }

    public void setButton_text_color(String button_text_color) {
        this.button_text_color = button_text_color;
    }

    public String getButton_bg_color() {
        return button_bg_color;
    }

    public void setButton_bg_color(String button_bg_color) {
        this.button_bg_color = button_bg_color;
    }

    public String getEdit_text_bg() {
        return edit_text_bg;
    }

    public void setEdit_text_bg(String edit_text_bg) {
        this.edit_text_bg = edit_text_bg;
    }

    public String getInput_box1() {
        return input_box1;
    }

    public void setInput_box1(String input_box1) {
        this.input_box1 = input_box1;
    }

    public String getInput_box2() {
        return input_box2;
    }

    public void setInput_box2(String input_box2) {
        this.input_box2 = input_box2;
    }

    public String getLogin_url() {
        return login_url;
    }

    public void setLogin_url(String login_url) {
        this.login_url = login_url;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }
}
