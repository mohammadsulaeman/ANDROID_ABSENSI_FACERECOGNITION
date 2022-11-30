package com.example.absensirtj.activity.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.absensirtj.R;
import com.example.absensirtj.databinding.ActivityUsersSupportBinding;
import com.example.absensirtj.item.users.UsersSupportItem;
import com.example.absensirtj.json.GetSupportResponseJson;
import com.example.absensirtj.models.SupportModel;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersSupportActivity extends AppCompatActivity {
    ActivityUsersSupportBinding binding;
    UsersSupportItem supportItem;
    List<SupportModel> supportModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityUsersSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.supportListDataUsers.setHasFixedSize(true);
        binding.supportListDataUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dataSupportList();
    }

    private void dataSupportList() {
        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,"admin","12345");
        service.getsupport().enqueue(new Callback<GetSupportResponseJson>() {
            @Override
            public void onResponse(Call<GetSupportResponseJson> call, Response<GetSupportResponseJson> response) {
                if (response.isSuccessful()){
                    supportModels = response.body().getSupportData();
                    supportItem = new UsersSupportItem(getApplicationContext(),supportModels,R.layout.layout_support_row_users);
                    binding.supportListDataUsers.setAdapter(supportItem);
                }
            }

            @Override
            public void onFailure(Call<GetSupportResponseJson> call, Throwable t) {

            }
        });
    }
}