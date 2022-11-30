package com.example.absensirtj.json;

import com.example.absensirtj.models.PulangModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetPulangResponseJson {
    @Expose
    @SerializedName("keluarhome")
    private List<PulangModel> datapulang = new ArrayList<>();

    public List<PulangModel> getDatapulang() {
        return datapulang;
    }

    public void setDatapulang(List<PulangModel> datapulang) {
        this.datapulang = datapulang;
    }
}
