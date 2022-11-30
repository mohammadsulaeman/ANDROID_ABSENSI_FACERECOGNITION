package com.example.absensirtj.json;

import com.example.absensirtj.models.SakitModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSakitResponseJson {
    @Expose
    @SerializedName("sakithome")
    private List<SakitModel> datasakit = new ArrayList<>();

    public List<SakitModel> getDatasakit() {
        return datasakit;
    }

    public void setDatasakit(List<SakitModel> datasakit) {
        this.datasakit = datasakit;
    }
}
