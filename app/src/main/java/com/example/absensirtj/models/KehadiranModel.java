package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.annotations.PrimaryKey;

public class KehadiranModel  implements Serializable {
    @SerializedName("hadir_id")
    @Expose
    private String id;

    @SerializedName("hadir_nama")
    @Expose
    private String nama;

    @SerializedName("hadir_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("hadir_waktu")
    @Expose
    private String waktu;

    @SerializedName("hadir_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("hadir_status")
    @Expose
    private String status;

    @SerializedName("hadir_photo")
    @Expose
    private String foto;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
