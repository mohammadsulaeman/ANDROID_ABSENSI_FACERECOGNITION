package com.example.absensirtj.activity.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.databinding.ActivityUsersAboutBinding;
import com.example.absensirtj.item.users.UsersAboutItem;
import com.example.absensirtj.json.GetSettingResponseJson;
import com.example.absensirtj.models.AboutModel;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAboutActivity extends AppCompatActivity {
    ActivityUsersAboutBinding binding;
    UsersAboutItem aboutItem;
    List<AboutModel> aboutModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityUsersAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.aboutListDataUsers.setHasFixedSize(true);
        binding.aboutListDataUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dataAboutList();
    }

    private void dataAboutList() {
        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,"admin","12345");
        service.getsetting().enqueue(new Callback<GetSettingResponseJson>() {
            @Override
            public void onResponse(Call<GetSettingResponseJson> call, Response<GetSettingResponseJson> response) {
                if (response.isSuccessful()){
                    aboutModels = response.body().getSettingdata();
                    aboutItem = new UsersAboutItem(getApplicationContext(),aboutModels,R.layout.layout_about_row_users);
                    binding.aboutListDataUsers.setAdapter(aboutItem);
                }else{
                    Toast.makeText(UsersAboutActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSettingResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(UsersAboutActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}