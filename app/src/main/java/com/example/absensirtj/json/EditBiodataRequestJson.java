package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditBiodataRequestJson {
    @Expose
    @SerializedName("karyawan_id")
    private String id;

    @Expose
    @SerializedName("karyawan_phone")
    private String phone;



    @SerializedName("karyawan_gender")
    @Expose
    private String jekel;

    @SerializedName("karyawan_ktp")
    @Expose
    private String foto_ktp;

    @SerializedName("karyawan_alamat")
    @Expose
    private String alamat;


    @SerializedName("karyawan_lahir")
    @Expose
    private String lahir;

    @SerializedName("karyawan_tempat")
    @Expose
    private String tempat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getJekel() {
        return jekel;
    }

    public void setJekel(String jekel) {
        this.jekel = jekel;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public void setFoto_ktp(String foto_ktp) {
        this.foto_ktp = foto_ktp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
