package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerijinanRequestJson {
    @SerializedName("perijinan_id")
    @Expose
    private String id;

    @SerializedName("perijinan_nama")
    @Expose
    private String nama;

    @SerializedName("perijinan_phone")
    @Expose
    private String phone;

    @SerializedName("perijinan_keterangan")
    @Expose
    private String keterangan;

    @SerializedName("perijinan_status")
    @Expose
    private String status;

    @SerializedName("perijinan_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("perijinan_bukti")
    @Expose
    private String photo;

    @SerializedName("perijinan_waktu")
    @Expose
    private String waktu;

    @SerializedName("perijinan_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("perijinan_latitude")
    @Expose
    private String latitude;

    @SerializedName("perijinan_longitude")
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
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
