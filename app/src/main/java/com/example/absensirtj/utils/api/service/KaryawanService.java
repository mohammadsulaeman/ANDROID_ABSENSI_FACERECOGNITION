package com.example.absensirtj.utils.api.service;

import com.example.absensirtj.json.EditBiodataRequestJson;
import com.example.absensirtj.json.ForgotRequestJson;
import com.example.absensirtj.json.GetBeritaResponseJson;
import com.example.absensirtj.json.GetKehadiranResponseJson;
import com.example.absensirtj.json.GetPerijinanResponseJson;
import com.example.absensirtj.json.GetPulangResponseJson;
import com.example.absensirtj.json.GetSakitResponseJson;
import com.example.absensirtj.json.GetSettingResponseJson;
import com.example.absensirtj.json.GetSupportResponseJson;
import com.example.absensirtj.json.HIstoryIjinRequestJson;
import com.example.absensirtj.json.KehadiranRequestJson;
import com.example.absensirtj.json.LoginRequestJson;
import com.example.absensirtj.json.LoginResponseJson;
import com.example.absensirtj.json.PerijinanRequestJson;
import com.example.absensirtj.json.PulangRequestJson;
import com.example.absensirtj.json.RegisterRequestJson;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.json.SakitRequestJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KaryawanService {
    @GET("newsapi/newsdata")
    Call<GetBeritaResponseJson>  getberita();

    @GET("perijinanapi/ijindata")
    Call<GetPerijinanResponseJson>  getijin();

    @GET("kehadiranapi/hadirdata")
    Call<GetKehadiranResponseJson>  gethadir();

    @GET("supportapi/supportdata")
    Call<GetSupportResponseJson>  getsupport();

    @GET("settingapi/settingdata")
    Call<GetSettingResponseJson>  getsetting();

    @GET("pulangapi/pulangdata")
    Call<GetPulangResponseJson> getPulang();

    @GET("sakitapi/sakitdata")
    Call<GetSakitResponseJson> getSakit();

    @POST("kehadiranapi/register_kehadiran")
    Call<ResponseJson> hadir(@Body KehadiranRequestJson param);
    @POST("perijinanapi/register_perijinan")
    Call<ResponseJson> ijin(@Body PerijinanRequestJson param);
    @POST("pulangapi/register_pulang")
    Call<ResponseJson> pulang(@Body PulangRequestJson param);
    @POST("sakitapi/register_sakit")
    Call<ResponseJson> sakit(@Body SakitRequestJson param);
    @POST("karyawanapi/login")
    Call<LoginResponseJson> login(@Body LoginRequestJson param);

    @POST("karyawanapi/register_karyawan")
    Call<ResponseJson> register(@Body RegisterRequestJson param);

    @POST("karyawanapi/forgotpass")
    Call<ResponseJson> forgot(@Body ForgotRequestJson param);

    @POST("karyawanapi/edit_biodata")
    Call<ResponseJson> biodataedit(@Body EditBiodataRequestJson param);

    @POST("perijinanapi/serach_perijinan")
    Call<ResponseJson> cariijin(@Body HIstoryIjinRequestJson param);
}
