package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.customs.CustomExpandCollapseBar;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormIdentitasKonsumenEdit;
import com.bcafinance.itdp.mobilesurvey.helper.BitmapHelper;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import mumayank.com.airlocationlibrary.AirLocation;

public class SurveyUsahaActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private Context context = this;
    private TextView barExpandNarasumber1, barExpandNarasumber2, latitudeUsaha, longitudeUsaha, namaAlamatUsaha;
    private ExpandableLinearLayout layoutNarasumber1, layoutNarasumber2;
    private CustomExpandCollapseBar expandCollapseBar;
    private Spinner pertanyaanDuaDelapan, pertanyaanDuaSembilan, pertanyaanTigaPuluh, pertanyaanTigaSatu, pertanyaanTigaDua, pertanyaanTigaTiga, pertanyaanTigaEmpat, pertanyaanTigaLima;
    private EditText namaNarasumber1, jawabanTigaDua, jawabanTigaTiga, namaNarasumber2, jawabanTigaEmpat, jawabanTigaLima, jawabanDuaDelapan, jawabanDuaSembilan, namaTempatUsaha, alamatUsaha, kelurahanUsaha, kecamatanUsaha, kodePosUsaha;
    private Button buttonNarasumber, buttonSubmitUsaha, buttonBack, buttonCamera5, buttonCamera6, buttonCamera7, buttonCamera8;
    private ImageView imageUsaha1, imageUsaha2, imageJalanUsaha1, imageJalanUsaha2;

    private int REQUEST_CODE_CAMERA5 = 5;
    private int REQUEST_CODE_CAMERA6 = 6;
    private int REQUEST_CODE_CAMERA7 = 7;
    private int REQUEST_CODE_CAMERA8 = 8;

    private AirLocation airLocation2;
    private GoogleMap mMap;
    LocationManager locationManager;
    private StorageReference reference;
    private DatabaseReference dbase;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_usaha);
        reference = FirebaseStorage.getInstance().getReference();
        dbase = FirebaseDatabase.getInstance().getReference();
        progressBar = findViewById(R.id.progressBar);

        jawabanDuaDelapan = findViewById(R.id.jawabanDuaDelapan);
        jawabanDuaSembilan = findViewById(R.id.jawabanDuaSembilan);
        pertanyaanDuaDelapan = findViewById(R.id.pertanyaanDuaDelapan);
        pertanyaanDuaDelapan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanDuaDelapan.getSelectedItemId()==8){
                    jawabanDuaDelapan.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaDelapan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanDuaSembilan = findViewById(R.id.pertanyaanDuaSembilan);
        pertanyaanDuaSembilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanDuaSembilan.getSelectedItemId()==7){
                    jawabanDuaSembilan.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaSembilan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanTigaPuluh = findViewById(R.id.pertanyaanTigaPuluh);
        pertanyaanTigaSatu = findViewById(R.id.pertanyaanTigaSatu);

        barExpandNarasumber1 = findViewById(R.id.barExpandNarasumber1);
        layoutNarasumber1 = findViewById(R.id.layoutNarasumber1);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber1, layoutNarasumber1, false);

        barExpandNarasumber2 = findViewById(R.id.barExpandNarasumber2);
        layoutNarasumber2 = findViewById(R.id.layoutNarasumber2);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber2, layoutNarasumber2, false);

        namaNarasumber1 = findViewById(R.id.namaNarasumber1);
        jawabanTigaDua = findViewById(R.id.jawabanTigaDua);
        jawabanTigaTiga = findViewById(R.id.jawabanTigaTiga);
        namaNarasumber2 = findViewById(R.id.namaNarasumber2);
        jawabanTigaEmpat = findViewById(R.id.jawabanTigaEmpat);
        jawabanTigaLima = findViewById(R.id.jawabanTigaLima);
        pertanyaanTigaDua = findViewById(R.id.pertanyaanTigaDua);
        pertanyaanTigaDua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanTigaDua.getSelectedItemId()==6){
                    jawabanTigaDua.setVisibility(View.VISIBLE);
                }else{
                    jawabanTigaDua.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanTigaTiga = findViewById(R.id.pertanyaanTigaTiga);
        pertanyaanTigaEmpat = findViewById(R.id.pertanyaanTigaEmpat);
        pertanyaanTigaEmpat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanTigaEmpat.getSelectedItemId()==6){
                    jawabanTigaEmpat.setVisibility(View.VISIBLE);
                }else{
                    jawabanTigaEmpat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanTigaLima = findViewById(R.id.pertanyaanTigaLima);

        namaAlamatUsaha = findViewById(R.id.namaAlamatUsaha);
        latitudeUsaha = findViewById(R.id.latitudeUsaha);
        longitudeUsaha = findViewById(R.id.longitudeUsaha);

        imageUsaha1 = findViewById(R.id.imageUsaha1);
        imageUsaha1.setOnClickListener(v -> imageClick5());

        imageUsaha2 = findViewById(R.id.imageUsaha2);
        imageUsaha2.setOnClickListener(v -> imageClick6());

        imageJalanUsaha1 = findViewById(R.id.imageJalanUsaha1);
        imageJalanUsaha1.setOnClickListener(v -> imageClick7());

        imageJalanUsaha2 = findViewById(R.id.imageJalanUsaha2);
        imageJalanUsaha2.setOnClickListener(v -> imageClick8());

        buttonCamera5 = findViewById(R.id.buttonCamera5);
        buttonCamera5.setOnClickListener(v -> invokeCameraEsafirm5());

        buttonCamera6 = findViewById(R.id.buttonCamera6);
        buttonCamera6.setOnClickListener(v -> invokeCameraEsafirm6());

        buttonCamera7 = findViewById(R.id.buttonCamera7);
        buttonCamera7.setOnClickListener(v -> invokeCameraEsafirm7());

        buttonCamera8 = findViewById(R.id.buttonCamera8);
        buttonCamera8.setOnClickListener(v -> invokeCameraEsafirm8());

        if(!checkIsGPSEnabled()) {
            showSettingLocation();
        }

        setSpinnerData();

        buttonSubmitUsaha = findViewById(R.id.buttonSubmitUsaha);
        buttonSubmitUsaha.setOnClickListener(v -> {
            saveFotoRumah();
        });
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(context, InputSurveyActivity.class);
            startActivity(intent);
            finish();
        });

    }
    private void saveFotoRumah(){
        String namaAlamatUsahaValue = namaAlamatUsaha.getText().toString();
        String latitude2Value = latitudeUsaha.getText().toString();
        String longitude2Value = longitudeUsaha.getText().toString();
        SessionManager.saveUsahaKonsumen(context, namaAlamatUsahaValue, latitude2Value, longitude2Value);

        String pertanyaanDuaDelapanValue = pertanyaanDuaDelapan.getSelectedItem().toString();
        String pertanyaanDuaSembilanValue = pertanyaanDuaSembilan.getSelectedItem().toString();
        String pertanyaanTigaPuluhValue = pertanyaanTigaPuluh.getSelectedItem().toString();
        String pertanyaanTigaSatuValue = pertanyaanTigaSatu.getSelectedItem().toString();
        String jawabanDuaDelapanValue = jawabanDuaDelapan.getText().toString();
        String jawabanDuaSembilanValue = jawabanDuaSembilan.getText().toString();

        SessionManager.savePengamatanSurveyor(context, pertanyaanDuaDelapanValue, pertanyaanDuaSembilanValue, pertanyaanTigaPuluhValue, pertanyaanTigaSatuValue,
                jawabanDuaDelapanValue, jawabanDuaSembilanValue);

        String pertanyaanTigaDuaValue = pertanyaanTigaDua.getSelectedItem().toString();
        String pertanyaanTigaTigaValue = pertanyaanTigaTiga.getSelectedItem().toString();
        String pertanyaanTigaEmpatValue = pertanyaanTigaEmpat.getSelectedItem().toString();
        String pertanyaanTigaLimaValue = pertanyaanTigaLima.getSelectedItem().toString();
        String jawabanTigaDuaValue = jawabanTigaDua.getText().toString();
        String jawabanTigaTigaValue = jawabanTigaTiga.getText().toString();
        String jawabanTigaEmpatValue = jawabanTigaEmpat.getText().toString();
        String jawabanTigaLimaValue = jawabanTigaLima.getText().toString();
        String namaNarasumber3Value = namaNarasumber1.getText().toString();
        String namaNarasumber4Value = namaNarasumber2.getText().toString();

        SessionManager.saveSurveyLingkunganUsaha(context, pertanyaanTigaDuaValue, pertanyaanTigaTigaValue, pertanyaanTigaEmpatValue, pertanyaanTigaLimaValue, jawabanTigaDuaValue,
                jawabanTigaTigaValue, jawabanTigaEmpatValue, jawabanTigaLimaValue, namaNarasumber3Value, namaNarasumber4Value);

        uploadImage5();
        uploadImage6();
        uploadImage7();
        uploadImage8();

        Intent intent = new Intent(context, InputSurveyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    private void setSpinnerData() {
        ArrayAdapter<String> adapterPertanyaanDuaDelapan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUADELAPAN);
        adapterPertanyaanDuaDelapan.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaDelapan.setAdapter(adapterPertanyaanDuaDelapan);

        ArrayAdapter<String> adapterPertanyaanDuaSembilan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUASEMBILAN);
        adapterPertanyaanDuaSembilan.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaSembilan.setAdapter(adapterPertanyaanDuaSembilan);

        ArrayAdapter<String> adapterPertanyaanTigaPuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGAPULUH);
        adapterPertanyaanTigaPuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaPuluh.setAdapter(adapterPertanyaanTigaPuluh);

        ArrayAdapter<String> adapterPertanyaanTigaSatu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGASATU);
        adapterPertanyaanTigaSatu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaSatu.setAdapter(adapterPertanyaanTigaSatu);

        ArrayAdapter<String> adapterPertanyaanTigaDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGADUA);
        adapterPertanyaanTigaDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaDua.setAdapter(adapterPertanyaanTigaDua);

        ArrayAdapter<String> adapterPertanyaanTigaTiga = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGATIGA);
        adapterPertanyaanTigaTiga.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaTiga.setAdapter(adapterPertanyaanTigaTiga);

        ArrayAdapter<String> adapterPertanyaanTigaEmpat = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGADUA);
        adapterPertanyaanTigaEmpat.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaEmpat.setAdapter(adapterPertanyaanTigaEmpat);

        ArrayAdapter<String> adapterPertanyaanTigaLima = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGATIGA);
        adapterPertanyaanTigaLima.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaLima.setAdapter(adapterPertanyaanTigaLima);
    }

    private void getMyLocation2(){
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        airLocation2 = new AirLocation(this, true, true, new AirLocation.Callbacks() {
            @Override
            public void onSuccess(Location location) {
                String latitude_value = String.valueOf(location.getLatitude());
                String longitude_value = String.valueOf(location.getLongitude());

                latitudeUsaha.setText(latitude_value);
                longitudeUsaha.setText(longitude_value);

//                LatLng myLocation=new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(myLocation).title("Saya sekarang disini!"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
            }

            @Override
            public void onFailed(AirLocation.LocationFailedEnum locationFailedEnum) {
                Toast.makeText(context,"Gagalmendeteksi Lokasi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkIsGPSEnabled(){
        final LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return false;
        }else{
            return true;
        }
    }

    private void showSettingLocation(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Anda belum megaktifkan GPS, apakah anda mau mengaktifkannya?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void invokeCameraEsafirm5(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA5);
        getMyLocation2();
    }
    private void invokeCameraEsafirm6(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA6);
    }
    private void invokeCameraEsafirm7(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA7);
    }
    private void invokeCameraEsafirm8(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA8);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA5){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap5 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap5).into(imageUsaha1);
                BitmapHelper.getInstance().setBitmap5(bitmap5);
                buttonCamera5.setVisibility(View.GONE);
                buttonCamera6.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA6){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap6 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap6).into(imageUsaha2);
                BitmapHelper.getInstance().setBitmap6(bitmap6);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA7){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap7 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap7).into(imageJalanUsaha1);
                BitmapHelper.getInstance().setBitmap7(bitmap7);
                buttonCamera7.setVisibility(View.GONE);
                buttonCamera8.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA8){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap8 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap8).into(imageJalanUsaha2);
                BitmapHelper.getInstance().setBitmap8(bitmap8);
            }
        }
//        airLocation2.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        airLocation2.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void imageClick5(){
        if(imageUsaha1 != null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap5());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }else {

        }
    }
    private void imageClick6(){
        if (imageUsaha2 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap6());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick7(){
        if (imageJalanUsaha1 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap7());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick8(){
        if (imageJalanUsaha2 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap8());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }

    private void uploadImage5(){
        //tangkap data dari image view

        imageUsaha1.setDrawingCacheEnabled(true);
        imageUsaha1.buildDrawingCache();
        Bitmap bitmap5 = ((BitmapDrawable) imageUsaha1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap5.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
            }
        });
    }

    private void uploadImage6(){
        //tangkap data dari image view

        imageUsaha2.setDrawingCacheEnabled(true);
        imageUsaha2.buildDrawingCache();
        Bitmap bitmap6 = ((BitmapDrawable) imageUsaha2.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap6.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
            }
        });
    }

    private void uploadImage7(){
        //tangkap data dari image view

        imageJalanUsaha1.setDrawingCacheEnabled(true);
        imageJalanUsaha1.buildDrawingCache();
        Bitmap bitmap7 = ((BitmapDrawable) imageJalanUsaha1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap7.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
            }
        });
    }

    private void uploadImage8(){
        //tangkap data dari image view

        imageJalanUsaha2.setDrawingCacheEnabled(true);
        imageJalanUsaha2.buildDrawingCache();
        Bitmap bitmap8 = ((BitmapDrawable) imageJalanUsaha2.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap8.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            namaAlamatUsaha.setText(namaAlamatUsaha.getText() + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
