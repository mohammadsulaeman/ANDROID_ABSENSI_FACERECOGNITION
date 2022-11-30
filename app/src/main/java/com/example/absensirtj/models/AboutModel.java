package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AboutModel implements Serializable {
    @Expose
    @SerializedName("tagline")
    private String moto;

    @Expose
    @SerializedName("email")
    private String mail;

    @Expose
    @SerializedName("telepon")
    private String phone;

    @Expose
    @SerializedName("alamat")
    private String adress;

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
