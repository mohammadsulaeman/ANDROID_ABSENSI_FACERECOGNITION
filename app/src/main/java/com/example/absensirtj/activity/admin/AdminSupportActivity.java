package com.example.absensirtj.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.databinding.ActivityAdminSupportBinding;
import com.example.absensirtj.item.admin.AdminSupportItem;
import com.example.absensirtj.json.GetSupportResponseJson;
import com.example.absensirtj.models.SupportModel;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSupportActivity extends AppCompatActivity {
    ActivityAdminSupportBinding binding;
    AdminSupportItem adminSupportItem;
    List<SupportModel> supportModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityAdminSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.layoutListSupportAdmin.setHasFixedSize(true);
        binding.layoutListSupportAdmin.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dataSuportList();
    }

    private void dataSuportList() {
        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,"admin","12345");
        service.getsupport().enqueue(new Callback<GetSupportResponseJson>() {
            @Override
            public void onResponse(Call<GetSupportResponseJson> call, Response<GetSupportResponseJson> response) {
                if (response.isSuccessful()){
                    supportModels = response.body().getSupportData();
                    adminSupportItem = new AdminSupportItem(getApplicationContext(),supportModels,R.layout.layout_support_admin_row);
                    binding.layoutListSupportAdmin.setAdapter(adminSupportItem);
                }else{
                    Toast.makeText(AdminSupportActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSupportResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AdminSupportActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}