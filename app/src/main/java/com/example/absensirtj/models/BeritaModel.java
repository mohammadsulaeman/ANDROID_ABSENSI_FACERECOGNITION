package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BeritaModel implements Serializable{
    @Expose
    @SerializedName("judul_news")
    private String judulnews;

    @Expose
    @SerializedName("deskripsi")
    private String decsnews;

    @Expose
    @SerializedName("gambar")
    private String fotonews;

    public String getJudulnews() {
        return judulnews;
    }

    public void setJudulnews(String judulnews) {
        this.judulnews = judulnews;
    }

    public String getDecsnews() {
        return decsnews;
    }

    public void setDecsnews(String decsnews) {
        this.decsnews = decsnews;
    }

    public String getFotonews() {
        return fotonews;
    }

    public void setFotonews(String fotonews) {
        this.fotonews = fotonews;
    }
}
