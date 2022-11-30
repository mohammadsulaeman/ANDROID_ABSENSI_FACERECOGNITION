package com.example.absensirtj.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.example.absensirtj.models.User;

import java.util.ArrayList;
import java.util.List;



public class LoginResponseJson {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<User> data = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
