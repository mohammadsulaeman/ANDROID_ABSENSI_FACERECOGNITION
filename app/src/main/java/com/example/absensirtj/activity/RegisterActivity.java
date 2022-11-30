package com.example.absensirtj.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.json.RegisterRequestJson;
import com.example.absensirtj.json.ResponseJson;
import com.example.absensirtj.utils.api.ServiceGenerator;
import com.example.absensirtj.utils.api.service.KaryawanService;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.example.absensirtj.R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import info.androidhive.fontawesome.FontDrawable;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class RegisterActivity extends AppCompatActivity  {

    //register json dan authtelp
    ImageView photo,  backbtn, backButtonverify, fotoktp,imgcard,alamatimg;
    EditText password,tanggal,phone,tempat, nama, email, numOne, numTwo, numThree, numFour, numFive, numSix, alamat,idcard;
    TextView  countryCode, sendTo, textnotif, textnotif2;
    Button submit, confirmButton;
    RelativeLayout rlnotif, rlprogress, rlnotif2;
    Spinner gender, status;
    private SimpleDateFormat dateFormatter, dateFormatterview;
    String phoneNumber;
    FirebaseUser firebaseUser;
    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    FirebaseAuth mAuth;
    byte[] imageByteArray, imageByteArrayktp;
    Bitmap decoded, decodedktp;
    String dateview, disableback;
    String[] spinnergender;
    String[] spinnerjob;
    ViewFlipper viewFlipper;
    String country_iso_code = "en";
    String verify, token;
    Realm realm;
    public static final int SIGNUP_ID = 110;
    public static final String USER_KEY = "UserKey";
    //simpan gambar di firebase storage
    private DatabaseReference userRef;
    private String downloadURl;
    private StorageReference profileRef;
    String latitude, longitude;
    private final int DESTINATION_ID = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //locaksi

        realm = Realm.getDefaultInstance();
        fbAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        imgcard = findViewById(R.id.imgidcard);
        firebaseUser = mAuth.getCurrentUser();
        photo = findViewById(R.id.foto_karyawan);
        password = findViewById(R.id.passwordmitra);
        backbtn = findViewById(R.id.back_btn);
        phone = findViewById(R.id.phonenumber);
        nama = findViewById(R.id.namamitra);
        tempat = findViewById(R.id.tempatmitra);
        email = findViewById(R.id.email);
        tanggal = findViewById(R.id.tanggallahir);
        submit = findViewById(R.id.submit);
        rlnotif = findViewById(R.id.rlnotif);
        textnotif = findViewById(R.id.textnotif);
        idcard = findViewById(R.id.idcardkaryawan);
        countryCode = findViewById(R.id.countrycode);
        viewFlipper = findViewById(R.id.viewflipper);
        backButtonverify = findViewById(R.id.back_btn_verify);
        rlprogress = findViewById(R.id.rlprogress);
        rlnotif2 = findViewById(R.id.rlnotif2);
        textnotif2 = findViewById(R.id.textnotif2);
        confirmButton = findViewById(R.id.buttonconfirm);
        token = FirebaseInstanceId.getInstance().getToken();
        numOne = findViewById(R.id.numone);
        numTwo = findViewById(R.id.numtwo);
        numThree = findViewById(R.id.numthree);
        numFour = findViewById(R.id.numfour);
        numFive = findViewById(R.id.numfive);
        numSix = findViewById(R.id.numsix);
        sendTo = findViewById(R.id.sendtotxt);
        gender = findViewById(R.id.karyawanjender);
        alamat = findViewById(R.id.alamatmitra);
        fotoktp = findViewById(R.id.foto_ktp_karyawan);
        alamatimg = findViewById(R.id.imgalamat);

        //penyimpanan ke database
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        profileRef = FirebaseStorage.getInstance().getReference().child("Profile Image");

        spinnergender = getResources().getStringArray(R.array.jender);
        status = findViewById(R.id.karyawanstatus);
        spinnerjob = getResources().getStringArray(R.array.status);

        LoadIcon();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        alamatimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PicklocationActivity.class);
                intent.putExtra(PicklocationActivity.FORM_VIEW_INDICATOR, DESTINATION_ID);
                startActivityForResult(intent, PicklocationActivity.LOCATION_PICKER_ID);
            }
        });

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTanggal();
            }
        });
        fotoktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImagektp();
            }
        });



        countryCode.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                final CountryPicker picker = CountryPicker.newInstance("Select Country");
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        countryCode.setText(dialCode);
                        picker.dismiss();
                        country_iso_code = code;
                    }
                });
                picker.setStyle(R.style.countrypicker_style, R.style.countrypicker_style);
                picker.show(getSupportFragmentManager(), "Select Country");
            }
        });
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatterview = new SimpleDateFormat("dd MMM yyyy", Locale.US);

        //jender spinner
        ArrayAdapter<String> genderSpinner = new ArrayAdapter<>(this, R.layout.spinner, spinnergender);
        genderSpinner.setDropDownViewResource(R.layout.spinner);
        gender.setAdapter(genderSpinner);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);

                } else {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //status spinner
        ArrayAdapter<String> statusSpinner = new ArrayAdapter<>(this, R.layout.spinner, spinnerjob);
        statusSpinner.setDropDownViewResource(R.layout.spinner);
        status.setAdapter(statusSpinner);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);

                } else {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String emailvalidate = email.getText().toString();

                if (imageByteArray == null) {
                    notif(getString(R.string.addphoto));
                }else if (imageByteArrayktp == null) {
                    notif(getString(R.string.ktpempty));
                } else if (TextUtils.isEmpty(phone.getText().toString())) {
                    notif(getString(R.string.phoneempty));
                } else if (TextUtils.isEmpty(nama.getText().toString())) {
                    notif(getString(R.string.namaempty));
                }else if (TextUtils.isEmpty(password.getText().toString())) {
                    notif(getString(R.string.passempty));
                } else if (TextUtils.isEmpty(email.getText().toString())) {
                    notif(getString(R.string.emailempty));
                }else if (TextUtils.isEmpty(alamat.getText().toString())) {
                    notif(getString(R.string.alamatempty));
                } else if (TextUtils.isEmpty(tanggal.getText().toString())) {
                    notif(getString(R.string.birtdayempty));
                } else if (TextUtils.isEmpty(tempat.getText().toString())) {
                    notif(getString(R.string.tempatyempty));
                } else if (!emailvalidate.matches(emailPattern)) {
                    notif("wrong email format!");
                } else if (gender.getSelectedItemPosition() == 0) {
                    notif(getString(R.string.jenderempty));
                } else if (status.getSelectedItemPosition() == 0) {
                    notif(getString(R.string.statusempty));
                }   else {
                    upload("true");
                }
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(viewFlipper);
            }
        });
        backButtonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTanggal();
            }
        });

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PicklocationActivity.class);
                intent.putExtra(PicklocationActivity.FORM_VIEW_INDICATOR, DESTINATION_ID);
                startActivityForResult(intent, PicklocationActivity.LOCATION_PICKER_ID);
            }
        });
        disableback = "false";
        codenumber();
        verify = "false";

    }

    private void LoadIcon() {
        FontDrawable drawableback = new FontDrawable(this,R.string.fa_arrow_left_solid,true,false);
        drawableback.setTextColor(ContextCompat.getColor(this,R.color.black));
        backbtn.setImageDrawable(drawableback);
        backButtonverify.setImageDrawable(drawableback);

        FontDrawable drawablektp = new FontDrawable(this,R.string.fa_id_card_solid,true,false);
        drawablektp.setTextColor(ContextCompat.getColor(this,R.color.black));
        fotoktp.setImageDrawable(drawablektp);

        ImageView gmail = findViewById(R.id.imgemail);
        FontDrawable drawableMail = new FontDrawable(this,R.string.fa_envelope_solid,true,false);
        drawableMail.setTextColor(ContextCompat.getColor(this,R.color.black));
        gmail.setImageDrawable(drawableMail);


        FontDrawable drawablealmt = new FontDrawable(this,R.string.fa_map_marker_alt_solid,true,false);
        drawablealmt.setTextColor(ContextCompat.getColor(this,R.color.black));
        alamatimg.setImageDrawable(drawablealmt);

        // idcard
        FontDrawable drawableidcard = new FontDrawable(this,R.string.fa_id_card_solid,true,false);
        drawableidcard.setTextColor(ContextCompat.getColor(this,R.color.black));
        imgcard.setImageDrawable(drawableidcard);


        ImageView tanggallahir = findViewById(R.id.imgcalender);
        FontDrawable drawableLahir = new FontDrawable(this,R.string.fa_calendar_alt_solid,true,false);
        drawableLahir.setTextColor(ContextCompat.getColor(this,R.color.black));
        tanggallahir.setImageDrawable(drawableLahir);

        ImageView tempatlahir = findViewById(R.id.imgtempatlahir);
        tempatlahir.setImageDrawable(drawablealmt);

        ImageView lockpass = findViewById(R.id.imgpassword);
        FontDrawable drawableLock = new FontDrawable(this,R.string.fa_lock_solid,true,false);
        drawableLock.setTextColor(ContextCompat.getColor(this,R.color.black));
        lockpass.setImageDrawable(drawableLock);

        ImageView statusimg = findViewById(R.id.imgstatusreg);
        FontDrawable drawableStatus = new FontDrawable(this,R.string.fa_list_alt_solid,true,false);
        drawableStatus.setTextColor(ContextCompat.getColor(this,R.color.black));
        statusimg.setImageDrawable(drawableStatus);

        ImageView genderimg = findViewById(R.id.imggender);
        genderimg.setImageDrawable(drawableStatus);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PicklocationActivity.LOCATION_PICKER_ID) {
                String addressset = data.getStringExtra(PicklocationActivity.LOCATION_NAME);
                LatLng latLng = data.getParcelableExtra(PicklocationActivity.LOCATION_LATLNG);
                alamat.setText(addressset);
                latitude = String.valueOf(Objects.requireNonNull(latLng).getLatitude());
                longitude = String.valueOf(latLng.getLongitude());
            }
            else if (requestCode == 1) {
                Bundle extras = data.getExtras();
                final Bitmap imagebitmap = (Bitmap) extras.get("data");
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                photo.setImageBitmap(rotatedBitmap);
                imageByteArray = baos.toByteArray();
                decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));

            } else if (requestCode == 2) {
                Bundle extras = data.getExtras();
                final Bitmap imagebitmap = (Bitmap) extras.get("data");
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                fotoktp.setImageBitmap(rotatedBitmap);
                imageByteArrayktp = baos.toByteArray();
                decodedktp = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));

            }
        }
    }

    //register dan json
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        imageByteArray = baos.toByteArray();
        return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
    }

    public String getStringImagektp(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        imageByteArrayktp = baos.toByteArray();
        return Base64.encodeToString(imageByteArrayktp, Base64.DEFAULT);
    }

    private boolean check_ReadStoragepermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
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

    private void selectImagektp() {
        if (check_ReadStoragepermission()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 2);
        }
    }
    public void progressshow() {
        rlprogress.setVisibility(View.VISIBLE);
        disableback = "true";
    }

    public void progresshide() {
        rlprogress.setVisibility(View.GONE);
        disableback = "false";
    }

    @Override
    public void onBackPressed() {
        if (!disableback.equals("true")) {
            finish();
        }
    }

    public void Nextbtn(View view) {
        phoneNumber = countryCode.getText().toString() + phone.getText().toString();
        String ccode = countryCode.getText().toString();

        if ((!TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(ccode)) && phoneNumber.length() > 5) {
            progressshow();
            Send_Number_tofirebase(phoneNumber);

        } else {
            notif("Please enter phone correctly");
        }
    }


    public void notif(String text) {
        rlnotif.setVisibility(View.VISIBLE);
        textnotif.setText(text);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                rlnotif.setVisibility(View.GONE);
            }
        }, 3000);
    }

    //sendcode-----------------------
    public void notif2(String text) {
        rlnotif2.setVisibility(View.VISIBLE);
        textnotif2.setText(text);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                rlnotif2.setVisibility(View.GONE);
            }
        }, 3000);
    }

    public void Send_Number_tofirebase(String phoneNumber) {
        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks);
    }

    private void setUpVerificatonCallbacks() {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
                verify = "true";
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progresshide();
                android.util.Log.d("respon", e.toString());
                notif2("Verifikasi Gagal!");
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    notif2("wrong code!");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    notif2("Too Many Requests, please try with other phone number!");
                    notif("Too Many Requests, please try with other phone number!");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                phoneVerificationId = verificationId;
                resendToken = token;
                sendTo.setText("Send to ( " + phoneNumber + " )");
                progresshide();
                viewFlipper.setInAnimation(RegisterActivity.this, R.anim.from_right);
                viewFlipper.setOutAnimation(RegisterActivity.this, R.anim.to_left);
                viewFlipper.setDisplayedChild(1);

            }
        };
    }


    public void verifyCode(View view) {
        String code = "" + numOne.getText().toString() + numTwo.getText().toString() + numThree.getText().toString() + numFour.getText().toString() + numFive.getText().toString() + numSix.getText().toString();
        if (!code.equals("")) {
            progressshow();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
            signInWithPhoneAuthCredential(credential);

        } else {
            notif2("Enter your verification code!");
        }

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            upload("false");
                            SendImageLoader();
                        } else {
                            progresshide();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                notif2("wrong code!");
                            } else if (task.getException() instanceof FirebaseTooManyRequestsException) {
                                notif2("Too Many Requests, please try with other phone number!");
                                notif("Too Many Requests, please try with other phone number!");
                            }
                        }
                    }
                });
    }




    public void resendCode(View view) {

        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }

    public void codenumber() {

        numOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (numOne.getText().toString().length() == 0) {
                    numTwo.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (numTwo.getText().toString().length() == 0) {
                    numThree.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (numThree.getText().toString().length() == 0) {
                    numFour.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (numFour.getText().toString().length() == 0) {
                    numFive.requestFocus();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (numFive.getText().toString().length() == 0) {
                    numSix.requestFocus();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void showTanggal() {

        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        tanggal.setText(dateFormatterview.format(date_ship_millis));
                        dateview = dateFormatter.format(date_ship_millis);
                    }
                }
        );
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorgradient));
        datePicker.show(getFragmentManager(), "Tanggal Lahir");
    }
    private void upload(String aTrue) {
        progressshow();
        RegisterRequestJson request = new RegisterRequestJson();
        request.setName(nama.getText().toString());
        request.setAlamat(alamat.getText().toString());
        request.setPass(password.getText().toString());
        request.setCountrycode(countryCode.getText().toString());
        request.setChecked(aTrue);
        request.setIdcard(idcard.getText().toString());
        request.setEmail(email.getText().toString());
        request.setFoto_karyawan(getStringImage(decoded));
        request.setFoto_ktp(getStringImagektp(decodedktp));
        request.setJekel(String.valueOf(gender.getSelectedItem()));
        request.setLahir(tanggal.getText().toString());
        request.setNoTelepon(countryCode.getText().toString().replace("+", "") + phone.getText().toString());
        request.setStatus(String.valueOf(status.getSelectedItem()));
        request.setTempat(tempat.getText().toString());

        KaryawanService service = ServiceGenerator.createService(KaryawanService.class,request.getName(),request.getNoTelepon());
        service.register(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                progresshide();
                if (response.isSuccessful()){
                    if (Objects.requireNonNull(response.body()).getMessage().equalsIgnoreCase("next")){
                        Nextbtn(viewFlipper);
                    }else if (response.body().getMessage().equalsIgnoreCase("success")){
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        notif(response.body().getData());
                    }else
                    {
                        notif(response.body().getMessage());
                    }
                }else{
                    notif("error");
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                progresshide();
                t.printStackTrace();
                notif("error!");
            }
        });
    }

    private void SendImageLoader() {
        final String getNama = nama.getText().toString();
        final String getStatus = String.valueOf(status.getSelectedItem());
        final String getPhone = phone.getText().toString();
        final String getPass = password.getText().toString();
        final String getEmail = email.getText().toString();
        final String getLahir = tanggal.getText().toString();
        final String getAlamat = alamat.getText().toString();

        final StorageReference ref = profileRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
                    profileMap.put("password",getPass);
                    profileMap.put("phone",getPhone);
                    profileMap.put("status",getStatus);
                    profileMap.put("email",getEmail);
                    profileMap.put("lahir",getLahir);
                    profileMap.put("alamat",getAlamat);
                    profileMap.put("image",downloadURl);
                    userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .updateChildren(profileMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        upload("false");
                                    }else
                                    {
                                        notif(getString(R.string.error1));
                                    }
                                }
                            });
                } else {
                    notif("upload gagal dilakukan");
                }
            }
        });
    }

}