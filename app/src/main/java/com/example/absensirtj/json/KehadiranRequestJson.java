package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KehadiranRequestJson {
    @SerializedName("hadir_id")
    @Expose
    private String id;

    @SerializedName("hadir_nama")
    @Expose
    private String nama;

    @SerializedName("hadir_phone")
    @Expose
    private String phone;

    @SerializedName("hadir_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("hadir_waktu")
    @Expose
    private String waktu;

    @SerializedName("hadir_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("hadir_photo")
    @Expose
    private String photo;

    @SerializedName("hadir_status")
    @Expose
    private String status;

    @SerializedName("hadir_latitude")
    @Expose
    private String latitude;

    @SerializedName("hadir_longitude")
    @Expose
    private String longitude;

    @SerializedName("checked")
    @Expose
    private String checked;

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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
