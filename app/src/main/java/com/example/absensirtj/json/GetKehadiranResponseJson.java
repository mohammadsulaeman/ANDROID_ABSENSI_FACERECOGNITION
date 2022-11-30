package com.example.absensirtj.json;

import com.example.absensirtj.models.KehadiranModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetKehadiranResponseJson
{


    @Expose
    @SerializedName("hadirhome")
    private List<KehadiranModel> kehadiran = new ArrayList<>();


    public List<KehadiranModel> getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(List<KehadiranModel> kehadiran) {
        this.kehadiran = kehadiran;
    }


}
