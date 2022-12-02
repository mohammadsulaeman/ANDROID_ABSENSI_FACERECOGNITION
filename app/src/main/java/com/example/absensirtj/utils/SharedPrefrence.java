package com.example.absensirtj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefrence {
    public static SharedPreferences myPrefs;
    public static SharedPreferences.Editor prefsEditor;

    private static final String NIK_REGISTERED_KARYAWAN = "Nik_Karyawan";
    public static SharedPrefrence myObj;

    private SharedPrefrence() {

    }

    public static SharedPrefrence getInstance(Context ctx) {
        if (myObj == null) {
            myObj = new SharedPrefrence();
            myPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            prefsEditor = myPrefs.edit();
        }
        return myObj;
    }

    public void setNIKKaryawan(String nik) {
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString(NIK_REGISTERED_KARYAWAN,nik);
        editor.apply();
    }

    public  String getNIKKARYAWAN() {
        return myPrefs.getString(NIK_REGISTERED_KARYAWAN,"");
    }

}
