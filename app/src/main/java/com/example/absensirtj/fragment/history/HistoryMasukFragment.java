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
import com.example.absensirtj.databinding.FragmentHistoryMasukBinding;
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
    DatabaseReference masukRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryMasukBinding.inflate(inflater,container,false);
        context = getContext();
        binding.backHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.statushadirhis.equals("Karyawan Tetap")){
                    Intent admin = new Intent(context, AdminActivity.class);
                    startActivity(admin);
                }else{
                    Intent user = new Intent(context, UsersActivity.class);
                    startActivity(user);
                }
            }
        });
        masukRef = FirebaseDatabase.getInstance().getReference().child("Kehadiran");
        dataKehadiran();
        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistory.setImageDrawable(drawableBack);
        return binding.getRoot();
    }

    private void dataKehadiran() {
        masukRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String imagehadir = dataSnapshot.child("image").getValue().toString();
                            String nama = dataSnapshot.child("nama").getValue().toString();
                            String tanggal = dataSnapshot.child("tanggalhadir").getValue().toString();
                            String status = dataSnapshot.child("status").getValue().toString();
                            String lokasi = dataSnapshot.child("lokasihadir").getValue().toString();
                            String waktu = dataSnapshot.child("waktuhadir").getValue().toString();
                            PicassoTrustAll.getInstance(context)
                                    .load(imagehadir)
                                    .into(binding.profileImagehisthadir);
                            binding.namahadirhis.setText(nama);
                            binding.tanggalhadirhis.setText(tanggal);
                            binding.waktuhadirhis.setText(waktu);
                            binding.statushadirhis.setText(status);
                            binding.lokasihadirhis.setText(lokasi);
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