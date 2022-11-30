package com.example.absensirtj.fragment.admin;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.absen.KehadiranFragment;
import com.example.absensirtj.absen.PerijinanFragment;
import com.example.absensirtj.absen.PulangFragment;
import com.example.absensirtj.absen.SakitFragment;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.databinding.FragmentAdminHomeBinding;
import com.example.absensirtj.face.admin.AdminFaceRecogActivity;
import com.example.absensirtj.fragment.history.HistoryIjinFragment;
import com.example.absensirtj.fragment.history.HistoryMasukFragment;
import com.example.absensirtj.fragment.history.HistoryPulangFragment;
import com.example.absensirtj.fragment.history.HistorySakitFragment;
import com.example.absensirtj.fragment.history.MoreFragment;
import com.example.absensirtj.fragment.users.UsersProfileFragment;
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


public class AdminHomeFragment extends Fragment {

    FragmentAdminHomeBinding binding;
    Context context;
    private DatabaseReference userRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminHomeBinding.inflate(inflater,container,false);
        context = getContext();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        DataKaryawan();
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.namaKaryawanHome.setText(user.getName());
        binding.statusKaryawan.setText(user.getStatus());
        //pengaturan waktu
        String[] parsedpagi = "04:00".split(":");
        String[] parsedsiang = "11:00".split(":");
        String[] parsedsore = "13:00".split(":");
        String[] parsedmalam = "18:00".split(":");
        int pagi = Integer.parseInt(parsedpagi[0]), menitPagi = Integer.parseInt(parsedpagi[1]);
        int siang = Integer.parseInt(parsedsiang[0]), menitSiang = Integer.parseInt(parsedsiang[1]);
        int sore = Integer.parseInt(parsedsore[0]), menitSore = Integer.parseInt(parsedsore[1]);
        int malam = Integer.parseInt(parsedmalam[0]), menitMalam = Integer.parseInt(parsedmalam[1]);
        int totalpagi = (pagi * 60) + menitPagi;
        int totalsiang = (siang * 60) + menitSiang;
        int totalsore = (sore * 60) + menitSore;
        int totalmalam = (malam * 60) + menitMalam;

        Calendar now = Calendar.getInstance();
        int totalMenitNow = (now.get(Calendar.HOUR_OF_DAY) * 60) + now.get(Calendar.MINUTE);

        if (totalMenitNow >= totalpagi && totalMenitNow <= totalsiang && totalMenitNow <= totalsore && totalMenitNow <= totalmalam ) {
            binding.nighttext.setText(getString(R.string.pagi));
        } else if (totalMenitNow >= totalpagi && totalMenitNow >= totalsiang && totalMenitNow <= totalsore && totalMenitNow <= totalmalam ) {
            binding.nighttext.setText(getString(R.string.siang));
        } else if (totalMenitNow >= totalpagi && totalMenitNow >= totalsiang && totalMenitNow >= totalsore && totalMenitNow <= totalmalam ) {
            binding.nighttext.setText(getString(R.string.sore));
        } else {
            binding.nighttext.setText(getString(R.string.malam));
        }

        LoadIcons();
        //absen
        binding.absenHadirHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clikcHadir();
            }
        });
        binding.absenIjinHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIjin();
            }
        });
        binding.absenSakitHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSakit();
            }
        });
        binding.absenPulangHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPulang();
            }
        });
        binding.moreMenuHOme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreFragment moreFragment = new MoreFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container, moreFragment)
                        .commit();
            }
        });
        return binding.getRoot();
    }

    private void LoadIcons() {
        FontDrawable drawableMasuk = new FontDrawable(getContext(),R.string.fa_sign_in_alt_solid,true,false);
        drawableMasuk.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        binding.absenHadirHome.setImageDrawable(drawableMasuk);
        FontDrawable drawableSakit = new FontDrawable(getContext(),R.string.fa_user_injured_solid,true,false);
        drawableSakit.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        binding.absenSakitHome.setImageDrawable(drawableSakit);
        FontDrawable drawableIjin = new FontDrawable(getContext(),R.string.fa_running_solid,true,false);
        drawableIjin.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        binding.absenIjinHome.setImageDrawable(drawableIjin);
        FontDrawable drawablePulang = new FontDrawable(getContext(),R.string.fa_sign_out_alt_solid,true,false);
        drawablePulang.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        binding.absenPulangHome.setImageDrawable(drawablePulang);
    }


    private void clickIjin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.DialogStyle);
        builder.setIcon(R.mipmap.ic_absenapp);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(getString(R.string.facedetect));
        builder.setPositiveButton(getString(R.string.sudah), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PerijinanFragment perijinanFragment = new PerijinanFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,perijinanFragment)
                        .commit();
            }
        }).setNegativeButton(getString(R.string.belum), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent face = new Intent(getContext(), AdminFaceRecogActivity.class);
                face.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(face);
                getActivity().finish();
            }
        });
        builder.show();
    }
    private void clikcHadir(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.DialogStyle);
        builder.setIcon(R.mipmap.ic_absenapp);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(getString(R.string.facedetect));
        builder.setPositiveButton(getString(R.string.sudah), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                KehadiranFragment kehadiranFragment = new KehadiranFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,kehadiranFragment)
                        .commit();
            }
        }).setNegativeButton(getString(R.string.belum), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent face = new Intent(getContext(), AdminFaceRecogActivity.class);
                face.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(face);
                getActivity().finish();
            }
        });
        builder.show();
    }
    private void clickSakit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.DialogStyle);
        builder.setIcon(R.mipmap.ic_absenapp);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(getString(R.string.facedetect));
        builder.setPositiveButton(getString(R.string.sudah), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SakitFragment sakitFragment = new SakitFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,sakitFragment)
                        .commit();
            }
        }).setNegativeButton(getString(R.string.belum), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent face = new Intent(getContext(), AdminFaceRecogActivity.class);
                face.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(face);
                getActivity().finish();
            }
        });
        builder.show();
    }
    private void clickPulang(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.DialogStyle);
        builder.setIcon(R.mipmap.ic_absenapp);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(getString(R.string.facedetect));
        builder.setPositiveButton(getString(R.string.sudah), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PulangFragment pulangFragment = new PulangFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,pulangFragment)
                        .commit();
            }
        }).setNegativeButton(getString(R.string.belum), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent face = new Intent(getContext(), AdminFaceRecogActivity.class);
                face.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(face);
                getActivity().finish();
            }
        });
        builder.show();
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
                                    .into(binding.profileKaryawan);
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