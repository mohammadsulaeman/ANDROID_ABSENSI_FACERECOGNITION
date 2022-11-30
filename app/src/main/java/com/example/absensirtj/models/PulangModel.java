package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PulangModel implements Serializable
{
    @SerializedName("pulang_id")
    @Expose
    private String id;

    @SerializedName("pulang_nama")
    @Expose
    private String nama;

    @SerializedName("pulang_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("pulang_status")
    @Expose
    private String status;

    @SerializedName("pulang_waktu")
    @Expose
    private String waktu;

    @SerializedName("pulang_bukti")
    @Expose
    private String photo;

    @SerializedName("pulang_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("pulang_latitude")
    @Expose
    private String latitude;

    @SerializedName("pulang_longitude")
    @Expose
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
}
