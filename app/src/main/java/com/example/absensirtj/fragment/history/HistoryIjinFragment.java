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
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.databinding.FragmentHistoryIjinBinding;
import com.example.absensirtj.json.HIstoryIjinRequestJson;
import com.example.absensirtj.json.ResponseJson;
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
        binding.backHomeHistoryIjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.statusijinhis.equals("Karyawan Tetap")){
                    Intent admin = new Intent(context, AdminActivity.class);
                    startActivity(admin);
                }else{
                    Intent user = new Intent(context, UsersActivity.class);
                    startActivity(user);
                }
            }
        });
        ijinRef = FirebaseDatabase.getInstance().getReference().child("Perijinan");
        dataPerijinan();

        FontDrawable drawableBack = new FontDrawable(context,R.string.fa_arrow_left_solid,true,false);
        drawableBack.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.backHomeHistoryIjin.setImageDrawable(drawableBack);
        return binding.getRoot();
    }
    private void dataPerijinan() {
        ijinRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String nama = dataSnapshot.child("namaijin").getValue().toString();
                            String keterangan = dataSnapshot.child("keteranganijin").getValue().toString();
                            String status = dataSnapshot.child("statusijin").getValue().toString();
                            String tanggal = dataSnapshot.child("tanggalijin").getValue().toString();
                            String waktu = dataSnapshot.child("waktuijin").getValue().toString();
                            String lokasi = dataSnapshot.child("lokasiijin").getValue().toString();
                            String imageijin = dataSnapshot.child("imageijin").getValue().toString();
                            PicassoTrustAll.getInstance(context)
                                    .load( imageijin)
                                    .into(binding.profileImagehistijin);
                            binding.namaijinhis.setText( nama);
                            binding.keteranganijinhis.setText(keterangan);
                            binding.statusijinhis.setText(status);
                            binding.tanggalijinhis.setText(tanggal);
                            binding.waktuijinhis.setText(waktu);
                            binding.lokasijinhis.setText(lokasi);
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