package com.example.absensirtj.fragment.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.activity.users.UsersActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.databinding.FragmentHistoryIjinBinding;
import com.example.absensirtj.json.HIstoryIjinRequestJson;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import info.androidhive.fontawesome.FontDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryIjinFragment extends Fragment {

    FragmentHistoryIjinBinding binding;
    Context context;
    DatabaseReference ijinRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryIjinBinding.inflate(inflater,container,false);
        context = getContext();
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.backHomeHistoryIjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getStatus().equals("Karyawan Tetap")){
                    Intent admin = new Intent(context, AdminActivity.class);
                    startActivity(admin);
                }else{
                    Intent user = new Intent(context, UsersActivity.class);
                    startActivity(user);
                }
            }
        });
        ijinRef = FirebaseDatabase.getInstance().getReference().child("Perijinan");


        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistoryIjin.setImageDrawable(drawableBack);
        return binding.getRoot();
    }
}