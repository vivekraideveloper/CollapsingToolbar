package com.shreyanshjain.dynamic_app.Models;

import com.google.gson.annotations.SerializedName;

public class TestResults {
    @SerializedName("code")
    String code;

    @SerializedName("message")
    String msg;

    @SerializedName("result")
    Results results;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Results getResults() {
        return results;
    }
}
