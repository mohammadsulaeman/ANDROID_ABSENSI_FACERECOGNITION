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
import com.example.absensirtj.databinding.FragmentHistoryPulangBinding;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import info.androidhive.fontawesome.FontDrawable;


public class HistoryPulangFragment extends Fragment {

    FragmentHistoryPulangBinding binding;
    Context context;
    DatabaseReference pulangRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryPulangBinding.inflate(inflater,container,false);
        pulangRef = FirebaseDatabase.getInstance().getReference().child("Pulang");
        context = getContext();
        binding.backHomeHistoryPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.statuspulanghis.equals("Karyawan Tetap")){
                    Intent admin = new Intent(context, AdminActivity.class);
                    startActivity(admin);
                }else{
                    Intent user = new Intent(context, UsersActivity.class);
                    startActivity(user);
                }
            }
        });
        dataPulang();
        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistoryPulang.setImageDrawable(drawableBack);
        return binding.getRoot();
    }

    private void dataPulang() {
        pulangRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String imagehadir = dataSnapshot.child("image").getValue().toString();
                            String nama = dataSnapshot.child("nama").getValue().toString();
                            String tanggal = dataSnapshot.child("tanggalpulang").getValue().toString();
                            String status = dataSnapshot.child("status").getValue().toString();
                            String lokasi = dataSnapshot.child("lokasipulang").getValue().toString();
                            String waktu = dataSnapshot.child("waktupulang").getValue().toString();
                            PicassoTrustAll.getInstance(context)
                                    .load(imagehadir)
                                    .into(binding.profilepulangImagehist);
                            binding.namapulanghis.setText(nama);
                            binding.tanggalpulanghis.setText(tanggal);
                            binding.waktupulanghis.setText(waktu);
                            binding.statuspulanghis.setText(status);
                            binding.lokaspulanghis.setText(lokasi);
                        }else{
                            Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                        Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}