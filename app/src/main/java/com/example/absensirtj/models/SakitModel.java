package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SakitModel   implements Serializable
{
    @SerializedName("sakit_id")
    @Expose
    private String id;

    @SerializedName("sakit_nama")
    @Expose
    private String nama;

    @SerializedName("sakit_status")
    @Expose
    private String status;

    @SerializedName("sakit_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("sakit_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("sakit_bukti")
    @Expose
    private String photo;

    @SerializedName("sakit_waktu")
    @Expose
    private String waktu;

    @SerializedName("sakit_keterangan")
    @Expose
    private String keterangan;

    @SerializedName("sakit_latitude")
    @Expose
    private String latitude;

    @SerializedName("sakit_longitude")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
