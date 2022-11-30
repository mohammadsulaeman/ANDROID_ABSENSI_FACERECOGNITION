package com.example.absensirtj.fragment.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.absensirtj.R;
import com.example.absensirtj.absen.KehadiranFragment;
import com.example.absensirtj.absen.PerijinanFragment;
import com.example.absensirtj.absen.PulangFragment;
import com.example.absensirtj.absen.SakitFragment;
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.databinding.FragmentMoreBinding;
import com.example.absensirtj.fragment.admin.AdminProfileFragment;
import com.example.absensirtj.fragment.users.UsersProfileFragment;
import com.example.absensirtj.models.User;

import info.androidhive.fontawesome.FontDrawable;


public class MoreFragment extends Fragment {

    FragmentMoreBinding binding;
    Context context;
    private FragmentManager fragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMoreBinding.inflate(inflater,container,false);
        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(context, AdminActivity.class);
                startActivity(admin);
            }
        });
        context = getContext();
        binding.absenMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KehadiranFragment kehadiranFragment = new KehadiranFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container, kehadiranFragment)
                        .commit();
            }
        });
        binding.absenIjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerijinanFragment perijinanFragment = new PerijinanFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container, perijinanFragment)
                        .commit();
            }
        });
        binding.absenPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PulangFragment pulangFragment = new PulangFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,pulangFragment)
                        .commit();
            }
        });
        binding.absenSakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SakitFragment sakitFragment = new SakitFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,sakitFragment)
                        .commit();
            }
        });
        binding.historyIjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryIjinFragment historyIjinFragment = new HistoryIjinFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,historyIjinFragment)
                        .commit();
            }
        });
        binding.historyMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryMasukFragment historyMasukFragment = new HistoryMasukFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,historyMasukFragment)
                        .commit();
            }
        });
        binding.historyPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryPulangFragment historyPulangFragment = new HistoryPulangFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container,historyPulangFragment)
                        .commit();
            }
        });
        binding.historySakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistorySakitFragment historySakitFragment = new HistorySakitFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container, historySakitFragment)
                        .commit();
            }
        });
        binding.profileClickHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Container, adminProfileFragment)
                        .commit();
            }
        });
        LoadIcons();
        return binding.getRoot();
    }

    private void LoadIcons() {
        FontDrawable drawableMasuk = new FontDrawable(getContext(),R.string.fa_sign_in_alt_solid,true,false);
        drawableMasuk.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        binding.absenMasuk.setImageDrawable(drawableMasuk);
        binding.historyMasuk.setImageDrawable(drawableMasuk);
        FontDrawable drawableSakit = new FontDrawable(getContext(),R.string.fa_user_injured_solid,true,false);
        drawableSakit.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        binding.absenSakit.setImageDrawable(drawableSakit);
        binding.historySakit.setImageDrawable(drawableSakit);
        FontDrawable drawableIjin = new FontDrawable(getContext(),R.string.fa_running_solid,true,false);
        drawableIjin.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        binding.absenIjin.setImageDrawable(drawableIjin);
        binding.historyIjin.setImageDrawable(drawableIjin);
        FontDrawable drawablePulang = new FontDrawable(getContext(),R.string.fa_sign_out_alt_solid,true,false);
        drawablePulang.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        binding.absenPulang.setImageDrawable(drawablePulang);
        binding.historyPulang.setImageDrawable(drawablePulang);
    }
}