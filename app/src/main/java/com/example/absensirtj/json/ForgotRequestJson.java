package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotRequestJson {

    @SerializedName("karyawan_idcard")
    @Expose
    private String idcardkaryawan;

    @SerializedName("password")
    @Expose
    private String password;


    public String getIdcardkaryawan() {
        return idcardkaryawan;
    }

    public void setIdcardkaryawan(String idcardkaryawan) {
        this.idcardkaryawan = idcardkaryawan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
