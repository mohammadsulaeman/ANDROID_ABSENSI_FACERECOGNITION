package com.example.absensirtj.json;

import com.example.absensirtj.models.BeritaModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetBeritaResponseJson {
    @Expose
    @SerializedName("news")
    private List<BeritaModel> berita = new ArrayList<>();

    public List<BeritaModel> getBerita() {
        return berita;
    }

    public void setBerita(List<BeritaModel> berita) {
        this.berita = berita;
    }
}