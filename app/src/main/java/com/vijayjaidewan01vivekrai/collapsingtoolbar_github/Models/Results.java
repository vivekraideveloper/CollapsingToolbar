package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Results {
    @SerializedName("view_type")
    String view_type;
     @SerializedName("top_image")
    String top_image;
     @SerializedName("top_heading")
    String top_heading;
     @SerializedName("top_image_bg")
    String top_image_bg;
     @SerializedName("top_image_fg")
    String top_image_fg;
     @SerializedName("is_back")
    String is_back;
     @SerializedName("back_url")
    String back_url;
     @SerializedName("data")
    ArrayList<Data> data;

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public void setTop_image(String top_image) {
        this.top_image = top_image;
    }

    public void setTop_heading(String top_heading) {
        this.top_heading = top_heading;
    }

    public void setTop_image_bg(String top_image_bg) {
        this.top_image_bg = top_image_bg;
    }

    public void setTop_image_fg(String top_image_fg) {
        this.top_image_fg = top_image_fg;
    }

    public void setIs_back(String is_back) {
        this.is_back = is_back;
    }

    public void setBack_url(String back_url) {
        this.back_url = back_url;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getView_type() {
        return view_type;
    }

    public String getTop_image() {
        return top_image;
    }

    public String getTop_heading() {
        return top_heading;
    }

    public String getTop_image_bg() {
        return top_image_bg;
    }

    public String getTop_image_fg() {
        return top_image_fg;
    }

    public String getIs_back() {
        return is_back;
    }

    public String getBack_url() {
        return back_url;
    }

    public ArrayList<Data> getData() {
        return data;
    }
}
