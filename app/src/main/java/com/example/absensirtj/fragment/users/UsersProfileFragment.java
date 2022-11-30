package com.example.absensirtj.fragment.users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.admin.AdminAboutActivity;
import com.example.absensirtj.activity.admin.AdminSupportActivity;
import com.example.absensirtj.activity.users.UsersAboutActivity;
import com.example.absensirtj.activity.users.UsersSupportActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.databinding.FragmentUsersProfileBinding;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class UsersProfileFragment extends Fragment {
    FragmentUsersProfileBinding binding;
    Context context;
    DatabaseReference userRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersProfileBinding.inflate(inflater,container,false);
        context = getContext();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        DataKaryawan();
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.namaKaryawanHomeProfileuser.setText(user.getName());
        binding.statusKaryawanHomeProfileuser.setText(user.getStatus());

        binding.aboutKlikProfileuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminabout = new Intent(context, UsersAboutActivity.class);
                startActivity(adminabout);
            }
        });
        binding.supportKlikProfileuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminsupport = new Intent(context, UsersSupportActivity.class);
                startActivity(adminsupport);
            }
        });
        return binding.getRoot();
    }
    private void DataKaryawan() {
        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {

                        if ((dataSnapshot.exists())){
                            String namaImage = dataSnapshot.child("image").getValue().toString();
                            PicassoTrustAll.getInstance(context)
                                    .load(namaImage)
                                    .into(binding.profileHomeuser);
                        }else
                        {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                        databaseError.getMessage();
                        Toast.makeText(context, "error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}