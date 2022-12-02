package com.example.absensirtj.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.databinding.ActivityForgetPassBinding;
import com.example.absensirtj.json.ForgotRequestJson;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.NetworkUtils;
import com.example.absensirtj.utils.SharedPrefrence;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;

import info.androidhive.fontawesome.FontDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassActivity extends AppCompatActivity {
    ActivityForgetPassBinding binding;
    SharedPrefrence prefrence;
    String idcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefrence = SharedPrefrence.getInstance(getApplicationContext());
        idcard = prefrence.getNIKKARYAWAN();
        System.out.println("IDCard = "+idcard);
        binding.backBtnLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
            }
        });
        FontDrawable drawableback = new FontDrawable(this,R.string.fa_arrow_left_solid,true,false);
        drawableback.setTextColor(ContextCompat.getColor(this,R.color.black));
        binding.backBtnLupa.setImageDrawable(drawableback);
        binding.buttonLupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = binding.editTextSandiBaru.getText().toString();
                String password2 = binding.editTextSandiKonfirmasi.getText().toString();
                if (TextUtils.isEmpty(binding.editTextSandiBaru.getText().toString())){
                    notif(getString(R.string.sandiempty));
                }else if (!password2.matches(password1)){
                    notif(getString(R.string.sandinotfound));
                }else if (TextUtils.isEmpty(binding.editTextSandiKonfirmasi.getText().toString())){
                    notif(getString(R.string.sandiempty));
                }else{
                    if (NetworkUtils.isConnected(ForgetPassActivity.this)){
                        upload();
                    }else{
                        notif(getString(R.string.text_noInternet));
                    }
                }
            }
        });
    }

    private void upload() {
        ForgotRequestJson request = new ForgotRequestJson();
        request.setIdcardkaryawan(idcard);
        request.setPassword(binding.editTextSandiBaru.getText().toString());
        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,"admin","12345");
        service.forgot(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                if (response.isSuccessful()){
                    Intent back = new Intent(ForgetPassActivity.this,LoginActivity.class);
                    startActivity(back);
                    Toast.makeText(ForgetPassActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(ForgetPassActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ForgetPassActivity.this, "error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notif(String text) {
        binding.rlnotif.setVisibility(View.VISIBLE);
        binding.textnotif.setText(text);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                binding.rlnotif.setVisibility(View.GONE);
            }
        }, 3000);
    }
}