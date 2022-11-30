package com.example.absensirtj.json;

import com.example.absensirtj.models.AboutModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSettingResponseJson
{
    @Expose
    @SerializedName("setting")
    private List<AboutModel> settingdata = new ArrayList<>();

    public List<AboutModel> getSettingdata() {
        return settingdata;
    }

    public void setSettingdata(List<AboutModel> settingdata) {
        this.settingdata = settingdata;
    }
}