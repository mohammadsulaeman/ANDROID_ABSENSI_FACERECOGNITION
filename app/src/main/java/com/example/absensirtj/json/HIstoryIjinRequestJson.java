package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HIstoryIjinRequestJson {
    @Expose
    @SerializedName("perijinan_phone")
    private String phone;

    @Expose
    @SerializedName("keyword")
    private String keyword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
