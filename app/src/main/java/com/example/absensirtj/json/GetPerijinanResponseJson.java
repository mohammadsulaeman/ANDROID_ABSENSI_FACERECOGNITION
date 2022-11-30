package com.example.absensirtj.json;

import com.example.absensirtj.models.PerijinanModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetPerijinanResponseJson {
    @Expose
    @SerializedName("ijin")
    private List<PerijinanModel> perijinan = new ArrayList<>();

    public List<PerijinanModel> getPerijinan() {
        return perijinan;
    }

    public void setPerijinan(List<PerijinanModel> perijinan) {
        this.perijinan = perijinan;
    }
}