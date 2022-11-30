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
import com.example.absensirtj.databinding.FragmentHistorySakitBinding;
import com.example.absensirtj.utils.PicassoTrustAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import info.androidhive.fontawesome.FontDrawable;


public class HistorySakitFragment extends Fragment {
    FragmentHistorySakitBinding binding;
    Context context;
    DatabaseReference sakitRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistorySakitBinding.inflate(inflater,container,false);
        context = getContext();
        binding.backHomeHistorySakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.statussakithis.equals("Karyawan Tetap")){
                    Intent admin = new Intent(context, AdminActivity.class);
                    startActivity(admin);
                }else{
                    Intent user = new Intent(context, UsersActivity.class);
                    startActivity(user);
                }
            }
        });
        sakitRef = FirebaseDatabase.getInstance().getReference().child("Sakit");
        dataSakit();
        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistorySakit.setImageDrawable(drawableBack);
        return binding.getRoot();
    }

    private void dataSakit() {
        sakitRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String nama = dataSnapshot.child("namasakit").getValue().toString();
                            String keterangan = dataSnapshot.child("keterangan").getValue().toString();
                            String status = dataSnapshot.child("statussakit").getValue().toString();
                            String tanggal = dataSnapshot.child("tanggalsakit").getValue().toString();
                            String waktu = dataSnapshot.child("waktusakit").getValue().toString();
                            String lokasi = dataSnapshot.child("lokasisakit").getValue().toString();
                            String imageijin = dataSnapshot.child("imagesakit").getValue().toString();
                            PicassoTrustAll.getInstance(context)
                                    .load( imageijin)
                                    .into(binding.profileImagehistsakit);
                            binding.namasakithis.setText(nama);
                            binding.keterangansakithis.setText(keterangan);
                            binding.statussakithis.setText(status);
                            binding.tanggalsakithis.setText(tanggal);
                            binding.waktusakithis.setText(waktu);
                            binding.lokassakithis.setText(lokasi);
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