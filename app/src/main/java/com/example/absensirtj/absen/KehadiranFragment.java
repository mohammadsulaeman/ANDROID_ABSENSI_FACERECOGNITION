package com.example.absensirtj.absen;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.PicklocationActivity;
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.activity.users.UsersActivity;
import com.example.absensirtj.constants.BaseApp;
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.databinding.FragmentKehadiranBinding;
import com.example.absensirtj.json.KehadiranRequestJson;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.models.User;
import com.example.absensirtj.utils.NetworkUtils;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mapbox.mapboxsdk.geometry.LatLng;


import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import info.androidhive.fontawesome.FontDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KehadiranFragment extends Fragment {

    FragmentKehadiranBinding binding;
    Context context;
    byte[] imageByteArray;
    Bitmap decoded;
    String saveDate,saveTime;
    String latitude, longitude;
    private final int DESTINATION_ID = 1;
    private DatabaseReference hadirRef;
    private String downloadURl;
    private StorageReference profilehadirRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentKehadiranBinding.inflate(inflater,container,false);
        context = getContext();
        hadirRef = FirebaseDatabase.getInstance().getReference().child("Kehadiran");
        profilehadirRef = FirebaseStorage.getInstance().getReference().child("ProfileHadir");
        dataWaktu();
        dataTanggal();
        LoadIcons();
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.namamitra.setText(user.getName());
        binding.phonemitrahadir.setText(user.getNoTelepon());
        binding.karyawanstatus.setText(user.getStatus());
        binding.lokasimitrahadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PicklocationActivity.class);
                intent.putExtra(PicklocationActivity.FORM_VIEW_INDICATOR, DESTINATION_ID);
                startActivityForResult(intent, PicklocationActivity.LOCATION_PICKER_ID);
            }
        });
        binding.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageByteArray == null) {
                    Toast.makeText(getContext(), getString(R.string.addphoto), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.namamitra.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.namaempty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.lokasimitrahadir.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.lokasiempty), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.tanggalmitrahadir.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.tanggalempty), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.waktumitra.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.waktuempty), Toast.LENGTH_SHORT).show();
                }  else if (TextUtils.isEmpty(binding.karyawanstatus.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.statusempty), Toast.LENGTH_SHORT).show();
                }   else {
                    if (NetworkUtils.isConnected(context)){
                        upload();
                    }else {
                        Toast.makeText(context, getString(R.string.text_noInternet), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return binding.getRoot();
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        imageByteArray = baos.toByteArray();
        return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
    }


    private void LoadIcons(){

        FontDrawable drawablePhone = new FontDrawable(context,R.string.fa_phone_solid,true,false);
        drawablePhone.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imgphone.setImageDrawable(drawablePhone);
        FontDrawable drawableLokasi = new FontDrawable(context,R.string.fa_map_marker_alt_solid,true,false);
        drawableLokasi.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imglokasi.setImageDrawable(drawableLokasi);
        FontDrawable drawableTanggal = new FontDrawable(context,R.string.fa_calendar_alt_solid,true,false);
        drawableTanggal.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imgdate.setImageDrawable(drawableTanggal);
        FontDrawable drawableWaktu = new FontDrawable(context,R.string.fa_times_solid,true,false);
        drawableWaktu.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.waktulokasi.setImageDrawable(drawableWaktu);
        FontDrawable drawableList = new FontDrawable(context,R.string.fa_list_alt_solid,true,false);
        drawableList.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imgstatushadir.setImageDrawable(drawableList);
    }

    private boolean check_ReadStoragepermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            Constant.permission_camera_code);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return false;
    }

    private void selectImage() {
        if (check_ReadStoragepermission()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PicklocationActivity.LOCATION_PICKER_ID) {
                String addressset = data.getStringExtra(PicklocationActivity.LOCATION_NAME);
                LatLng latLng = data.getParcelableExtra(PicklocationActivity.LOCATION_LATLNG);
                binding.lokasimitrahadir.setText(addressset);
                latitude = String.valueOf(Objects.requireNonNull(latLng).getLatitude());
                longitude = String.valueOf(latLng.getLongitude());
            } else if (requestCode == 1) {
                Bundle extras = data.getExtras();
                final Bitmap imagebitmap = (Bitmap) extras.get("data");
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                binding.foto.setImageBitmap(rotatedBitmap);
                imageByteArray = baos.toByteArray();
                decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));

            }
        }

    }

    private void dataTanggal() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat datatanggal = new SimpleDateFormat("dd-MM-yyyy");
        saveTime = datatanggal.format(calendar.getTime());
        binding.tanggalmitrahadir.setText(saveTime);
    }

    private void dataWaktu() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataWaktu = new SimpleDateFormat("HH:mm:ss a");
        saveDate = dataWaktu.format(calendar.getTime());
        binding.waktumitra.setText(saveDate);
    }

    private void upload() {
        KehadiranRequestJson request = new KehadiranRequestJson();
        request.setNama(binding.namamitra.getText().toString());
        request.setStatus(binding.karyawanstatus.getText().toString());
        request.setLokasi(binding.lokasimitrahadir.getText().toString());
        request.setPhoto(getStringImage(decoded));
        request.setPhone(binding.phonemitrahadir.getText().toString());
        request.setTanggal(binding.tanggalmitrahadir.getText().toString());
        request.setWaktu(binding.waktumitra.getText().toString());
        request.setLatitude(latitude);
        request.setLongitude(longitude);
        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,request.getNama(),request.getPhone());
        service.hadir(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                if (response.isSuccessful()){
                    SendFirebaseDatabseHadir();
                }else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SendFirebaseDatabseHadir() {
        final String getNama = binding.namamitra.getText().toString();
        final String getPhone = binding.phonemitrahadir.getText().toString();
        final String getStatus = binding.karyawanstatus.getText().toString();
        final String getLokasi = binding.lokasimitrahadir.getText().toString();
        final String getTanggalHadir = binding.tanggalmitrahadir.getText().toString();
        final String getWaktuHadir = binding.waktumitra.getText().toString();

        final StorageReference ref = profilehadirRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        UploadTask uploadTask = ref.putBytes(imageByteArray);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadURl = task.getResult().toString();
                    HashMap<String,Object> profileMap = new HashMap<>();
                    profileMap.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                    profileMap.put("nama",getNama);
                    profileMap.put("tanggalhadir",getTanggalHadir);
                    profileMap.put("phone",getPhone);
                    profileMap.put("status",getStatus);
                    profileMap.put("waktuhadir",getWaktuHadir);
                    profileMap.put("lokasihadir",getLokasi);
                    profileMap.put("image",downloadURl);
                    hadirRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .updateChildren(profileMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        if (binding.karyawanstatus.equals("Karyawan Tetap")){
                                            Intent admin = new Intent(context, AdminActivity.class);
                                            startActivity(admin);
                                        }else{
                                            Intent users = new Intent(context,UsersActivity.class);
                                            startActivity(users);
                                        }
                                    }else
                                    {
                                        Toast.makeText(context, getString(R.string.error1), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}