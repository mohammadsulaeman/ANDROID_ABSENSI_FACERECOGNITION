package com.example.absensirtj.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequestJson
{
    @SerializedName("karyawan_id")
    @Expose
    private int id;


    @SerializedName("karyawan_name")
    @Expose
    private String name;

    @SerializedName("karyawan_phone")
    @Expose
    private String noTelepon;

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


    @SerializedName("karyawan_email")
    @Expose
    private String email;



    @SerializedName("karyawan_photo")
    @Expose
    private String foto_karyawan;

    @SerializedName("karyawan_code")
    @Expose
    private String countrycode;

    @SerializedName("password")
    @Expose
    private String pass;

    @SerializedName("checked")
    @Expose
    private String checked;


    @SerializedName("karyawan_status")
    @Expose
    private String status;

    @SerializedName("karyawan_idcard")
    @Expose
    private String idcard;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto_karyawan() {
        return foto_karyawan;
    }

    public void setFoto_karyawan(String foto_karyawan) {
        this.foto_karyawan = foto_karyawan;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
