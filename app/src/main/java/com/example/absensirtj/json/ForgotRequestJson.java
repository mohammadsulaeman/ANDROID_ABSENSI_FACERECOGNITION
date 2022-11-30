package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotRequestJson {
    @SerializedName("password")
    @Expose
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
