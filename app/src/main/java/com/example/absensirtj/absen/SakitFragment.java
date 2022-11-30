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
import com.example.absensirtj.databinding.FragmentSakitBinding;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.json.SakitRequestJson;
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


public class SakitFragment extends Fragment {

    FragmentSakitBinding binding;
    Context context;
    byte[] imageByteArray;
    Bitmap decoded;
    String saveDate,saveTime;
    String latitude, longitude;
    private final int DESTINATION_ID = 1;
    private DatabaseReference sakitRef;
    private String downloadURl;
    private StorageReference profilesakitRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSakitBinding.inflate(inflater,container,false);
        context = getContext();
        sakitRef = FirebaseDatabase.getInstance().getReference().child("Sakit");
        profilesakitRef = FirebaseStorage.getInstance().getReference().child("Profile Sakit");
        User user = BaseApp.getInstance(context).getLoginUser();
        binding.namamitrasakit.setText(user.getName());
        binding.phonemitrasakit.setText(user.getNoTelepon());
        binding.karyawanstatussakit.setText(user.getStatus());
        getDataWaktu();
        getDataTanggal();
        binding.lokasimitrasakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PicklocationActivity.class);
                intent.putExtra(PicklocationActivity.FORM_VIEW_INDICATOR, DESTINATION_ID);
                startActivityForResult(intent, PicklocationActivity.LOCATION_PICKER_ID);
            }
        });
        binding.fotosakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        binding.submitsakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageByteArray == null) {
                    Toast.makeText(getContext(), getString(R.string.addphoto), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.namamitrasakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.namaempty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.keteranganmitrasakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.keteranganempty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.lokasimitrasakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.lokasiempty), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.tanggalmitrasakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.tanggalempty), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.waktumitrasakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.waktuempty), Toast.LENGTH_SHORT).show();
                }  else if (TextUtils.isEmpty(binding.karyawanstatussakit.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.statusempty), Toast.LENGTH_SHORT).show();
                }   else {
                    if (NetworkUtils.isConnected(context)){
                        upload();
                    }else{
                        Toast.makeText(context, getString(R.string.text_noInternet), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        LoadIcons();
        return binding.getRoot();
    }
    private void LoadIcons(){
        FontDrawable drawablePhone = new FontDrawable(context,R.string.fa_phone_solid,true,false);
        drawablePhone.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imgphonesakit.setImageDrawable(drawablePhone);
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
        binding.imgstatussakit.setImageDrawable(drawableList);
        FontDrawable drawableKet = new FontDrawable(context,R.string.fa_id_card_solid,true,false);
        drawableKet.setTextColor(ContextCompat.getColor(context,R.color.gray));
        binding.imgketsakit.setImageDrawable(drawableKet);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PicklocationActivity.LOCATION_PICKER_ID) {
                String addressset = data.getStringExtra(PicklocationActivity.LOCATION_NAME);
                LatLng latLng = data.getParcelableExtra(PicklocationActivity.LOCATION_LATLNG);
                binding.lokasimitrasakit.setText(addressset);
                latitude = String.valueOf(Objects.requireNonNull(latLng).getLatitude());
                longitude = String.valueOf(latLng.getLongitude());
            } else if (requestCode == 1) {
                Bundle extras = data.getExtras();
                final Bitmap imagebitmap = (Bitmap) extras.get("data");
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                binding.fotosakit.setImageBitmap(rotatedBitmap);
                imageByteArray = baos.toByteArray();
                decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));

            }
        }

    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        imageByteArray = baos.toByteArray();
        return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
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
    private void getDataWaktu() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataWaktu = new SimpleDateFormat("HH:mm:ss a");
        saveDate = dataWaktu.format(calendar.getTime());
        binding.waktumitrasakit.setText(saveDate);
    }

    private void getDataTanggal() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat datatanggal = new SimpleDateFormat("dd-MM-yyyy");
        saveTime = datatanggal.format(calendar.getTime());
        binding.tanggalmitrasakit.setText(saveTime);
    }

    private void upload(){
        SakitRequestJson request = new SakitRequestJson();
        request.setNama(binding.namamitrasakit.getText().toString());
        request.setKeterangan(binding.keteranganmitrasakit.getText().toString());
        request.setPhoto(getStringImage(decoded));
        request.setLokasi(binding.lokasimitrasakit.getText().toString());
        request.setLatitude(latitude);
        request.setLongitude(longitude);
        request.setPhone(binding.phonemitrasakit.getText().toString());
        request.setStatus(binding.karyawanstatussakit.getText().toString());
        request.setTanggal(binding.tanggalmitrasakit.getText().toString());
        request.setWaktu(binding.waktumitrasakit.getText().toString());

        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,request.getNama(),request.getPhone());
        service.sakit(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                if (response.isSuccessful())
                {
                    SendFirebaseDatabaseIin();

                }else
                {
                    Toast.makeText(context, "error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SendFirebaseDatabaseIin() {
        final String getNama = binding.namamitrasakit.getText().toString();
        final String getPhone = binding.phonemitrasakit.getText().toString();
        final String getStatus = binding.karyawanstatussakit.getText().toString();
        final String getLokasi = binding.lokasimitrasakit.getText().toString();
        final String getTanggalsakit = binding.tanggalmitrasakit.getText().toString();
        final String getWaktusakit = binding.waktumitrasakit.getText().toString();
        final String getKeterangan = binding.keteranganmitrasakit.getText().toString();

        final StorageReference ref = profilesakitRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
                    profileMap.put("namasakit",getNama);
                    profileMap.put("tanggalsakit",getTanggalsakit);
                    profileMap.put("phone",getPhone);
                    profileMap.put("keterangan",getKeterangan);
                    profileMap.put("statussakit",getStatus);
                    profileMap.put("waktusakit",getWaktusakit);
                    profileMap.put("lokasisakit",getLokasi);
                    profileMap.put("imagesakit",downloadURl);
                    sakitRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .updateChildren(profileMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        if (binding.karyawanstatussakit.equals("Karyawan Tetap")){
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