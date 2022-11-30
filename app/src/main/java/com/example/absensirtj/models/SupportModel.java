package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SupportModel  implements Serializable
{
    @Expose
    @SerializedName("nama")
    private String names;

    @Expose
    @SerializedName("alamat")
    private String alamet;

    @Expose
    @SerializedName("phone")
    private String tlp;

    @Expose
    @SerializedName("email")
    private String mail;

    @Expose
    @SerializedName("pendidikan")
    private String sekolah;

    @Expose
    @SerializedName("instansi")
    private String asalsekolah;

    @Expose
    @SerializedName("dob")
    private String tangallahir;

    @Expose
    @SerializedName("website")
    private String web;

    @Expose
    @SerializedName("photo")
    private String foto;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlamet() {
        return alamet;
    }

    public void setAlamet(String alamet) {
        this.alamet = alamet;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

    public String getAsalsekolah() {
        return asalsekolah;
    }

    public void setAsalsekolah(String asalsekolah) {
        this.asalsekolah = asalsekolah;
    }

    public String getTangallahir() {
        return tangallahir;
    }

    public void setTangallahir(String tangallahir) {
        this.tangallahir = tangallahir;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
