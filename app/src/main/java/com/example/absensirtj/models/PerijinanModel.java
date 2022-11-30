package com.example.absensirtj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PerijinanModel implements Serializable
{
    @SerializedName("perijinan_id")
    @Expose
    private int id;

    @SerializedName("perijinan_nama")
    @Expose
    private String nama;

    @SerializedName("perijinan_keterangan")
    @Expose
    private String keterangan;

    @SerializedName("perijinan_status")
    @Expose
    private String status;

    @SerializedName("perijinan_tanggal")
    @Expose
    private String tanggal;

    @SerializedName("perijinan_waktu")
    @Expose
    private String waktu;

    @SerializedName("perijinan_lokasi")
    @Expose
    private String lokasi;

    @SerializedName("perijinan_bukti")
    @Expose
    private String foto;

    @SerializedName("reponse_kepala")
    @Expose
    private String userrespon;

    @SerializedName("nama_kepala")
    @Expose
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUserrespon() {
        return userrespon;
    }

    public void setUserrespon(String userrespon) {
        this.userrespon = userrespon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
