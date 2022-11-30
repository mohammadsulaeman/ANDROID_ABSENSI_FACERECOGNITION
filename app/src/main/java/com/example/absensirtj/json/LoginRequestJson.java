package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestJson
{
    @SerializedName("karyawan_token")
    @Expose
    private String regid;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("karyawan_phone")
    @Expose
    private String noTelepon;

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
}
