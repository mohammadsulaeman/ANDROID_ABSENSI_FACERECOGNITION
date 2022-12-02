package com.example.absensirtj.models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends RealmObject implements Serializable
{
    @PrimaryKey
    @SerializedName("karyawan_id")
    @Expose
    private String id;

    @SerializedName("karyawan_name")
    @Expose
    private String name;

    @SerializedName("karyawan_phone")
    @Expose
    private String noTelepon;

    @SerializedName("karyawan_status")
    @Expose
    private String status;

    @SerializedName("karyawan_gender")
    @Expose
    private String jekel;

    @SerializedName("karyawan_email")
    @Expose
    private String email;

    @SerializedName("karyawan_ktp")
    @Expose
    private String foto_ktp;

    @SerializedName("karyawan_photo")
    @Expose
    private String foto_karyawan;

    @SerializedName("karyawan_idcard")
    @Expose
    private String identitas;

    @SerializedName("karyawan_code")
    @Expose
    private String countrycode;

    @SerializedName("karyawan_alamat")
    @Expose
    private String alamat;

    @SerializedName("karyawan_latitude")
    @Expose
    private String latitude;

    @SerializedName("karyawan_longtitude")
    @Expose
    private String longitude;

    @SerializedName("karyawan_lahir")
    @Expose
    private String lahir;

    @SerializedName("karyawan_tempat")
    @Expose
    private String tempat;

    @SerializedName("karyawan_lokasi")
    @Expose
    private String lokasi;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJekel() {
        return jekel;
    }

    public void setJekel(String jekel) {
        this.jekel = jekel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public void setFoto_ktp(String foto_ktp) {
        this.foto_ktp = foto_ktp;
    }

    public String getFoto_karyawan() {
        return foto_karyawan;
    }

    public void setFoto_karyawan(String foto_karyawan) {
        this.foto_karyawan = foto_karyawan;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


}
