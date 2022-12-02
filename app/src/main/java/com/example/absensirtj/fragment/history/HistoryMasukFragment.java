package com.example.absensirtj.fragment.history;

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
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.activity.users.UsersActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.databinding.FragmentHistoryMasukBinding;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import info.androidhive.fontawesome.FontDrawable;


public class HistoryMasukFragment extends Fragment {
    FragmentHistoryMasukBinding binding;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryMasukBinding.inflate(inflater,container,false);
        context = getContext();
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.backHomeHistory.setOnClickListener(new View.OnClickListener() {
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
        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistory.setImageDrawable(drawableBack);
        return binding.getRoot();
    }


}