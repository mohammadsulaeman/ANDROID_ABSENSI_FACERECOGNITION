package com.example.absensirtj.json;

import com.example.absensirtj.models.SupportModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSupportResponseJson {

    @Expose
    @SerializedName("support")
    private List<SupportModel> supportData = new ArrayList<>();

    public List<SupportModel> getSupportData() {
        return supportData;
    }

    public void setSupportData(List<SupportModel> supportData) {
        this.supportData = supportData;
    }
}
