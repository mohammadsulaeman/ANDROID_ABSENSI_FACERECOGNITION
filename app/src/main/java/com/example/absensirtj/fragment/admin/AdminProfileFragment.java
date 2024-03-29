package com.example.absensirtj.fragment.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.admin.AdminAboutActivity;
import com.example.absensirtj.activity.admin.AdminSupportActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.databinding.FragmentAdminHomeBinding;
import com.example.absensirtj.databinding.FragmentAdminProfileBinding;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import info.androidhive.fontawesome.FontDrawable;


public class AdminProfileFragment extends Fragment {

   FragmentAdminProfileBinding binding;
   Context context;
   DatabaseReference userRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminProfileBinding.inflate(inflater,container,false);
        context = getContext();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.namaKaryawanHomeProfile.setText(user.getName());
        binding.statusKaryawanHomeProfile.setText(user.getStatus());
        PicassoTrustAll.getInstance(context)
                .load(Constant.IMAGESKARYAWAN + user.getFoto_karyawan())
                .into(binding.profileHomeadmin);

        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.black));
        binding.backProfile.setImageDrawable(drawableBack);

        binding.aboutKlikProfileAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminabout = new Intent(context,AdminAboutActivity.class);
                startActivity(adminabout);
            }
        });
        binding.supportKlikProfileAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminsupport = new Intent(context, AdminSupportActivity.class);
                startActivity(adminsupport);
            }
        });
        binding.aboutKlikEditBiodataAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return binding.getRoot();
    }

}