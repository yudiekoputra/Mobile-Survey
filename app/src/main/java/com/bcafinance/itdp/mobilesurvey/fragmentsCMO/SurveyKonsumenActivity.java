package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormSurveyKonsumenEdit;
import com.bcafinance.itdp.mobilesurvey.helper.APIUtilities;
import com.bcafinance.itdp.mobilesurvey.helper.AddSurvey.AddSurvey;
import com.bcafinance.itdp.mobilesurvey.helper.BitmapHelper;
import com.bcafinance.itdp.mobilesurvey.helper.Datum;
import com.bcafinance.itdp.mobilesurvey.helper.RequestAPIServices;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyKonsumenActivity extends AppCompatActivity {
    private Context context = this;
    private Spinner pertanyaanDua, pertanyaanLima;
    private RadioGroup pertanyaanSatu, platNomor, merkTipe, warna, tahunKendaraan, pilihSurvey;
    private EditText jenisKredit, totalAngsuran, totalPenghasilan;
    private Button buttonSubmitSurveyKonsumen, buttonBack, buttonCamera9, buttonCamera10, buttonCamera11, buttonCamera12;
    private ImageView imageKtp, imageKtpSelfie, imageSelfieCmoKonsumen, imageSelfieCmoRumah;
    private RadioButton surveyRumah, surveyUsaha;
    RequestAPIServices apiServices;

    private int REQUEST_CODE_CAMERA9 = 9;
    private int REQUEST_CODE_CAMERA10 = 10;
    private int REQUEST_CODE_CAMERA11 = 11;
    private int REQUEST_CODE_CAMERA12 = 12;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_konsumen);
        apiServices = APIUtilities.getAPIServices();
        generatekodeSurvey();

        progressBar = findViewById(R.id.progressBar);

        pertanyaanSatu = findViewById(R.id.pertanyaanSatu);
        pertanyaanDua = findViewById(R.id.pertanyaanDua);
        platNomor = findViewById(R.id.platNomor);
        merkTipe = findViewById(R.id.merkTipe);
        warna = findViewById(R.id.warna);
        tahunKendaraan = findViewById(R.id.tahunKendaraan);
        jenisKredit = findViewById(R.id.jenisKredit);
        totalAngsuran = findViewById(R.id.totalAngsuran);
        totalPenghasilan = findViewById(R.id.totalPenghasilan);
        pertanyaanLima = findViewById(R.id.pertanyaanLima);
        pilihSurvey = findViewById(R.id.pilihSurvey);
        surveyRumah = findViewById(R.id.surveyRumah);
        surveyUsaha = findViewById(R.id.surveyUsaha);

        imageKtp = findViewById(R.id.imageKtp);
        imageKtp.setOnClickListener(v -> imageClick9());

        imageKtpSelfie = findViewById(R.id.imageKtpSelfie);
        imageKtpSelfie.setOnClickListener(v -> imageClick10());

        imageSelfieCmoKonsumen = findViewById(R.id.imageSelfieCmoKonsumen);
        imageSelfieCmoKonsumen.setOnClickListener(v -> imageClick11());

        imageSelfieCmoRumah = findViewById(R.id.imageSelfieCmoRumah);
        imageSelfieCmoRumah.setOnClickListener(v -> imageClick12());

        buttonCamera9 = findViewById(R.id.buttonCamera9);
        buttonCamera9.setOnClickListener(v -> invokeCameraEsafirm9());

        buttonCamera10 = findViewById(R.id.buttonCamera10);
        buttonCamera10.setOnClickListener(v -> invokeCameraEsafirm10());

        buttonCamera11 = findViewById(R.id.buttonCamera11);
        buttonCamera11.setOnClickListener(v -> invokeCameraEsafirm11());

        buttonCamera12 = findViewById(R.id.buttonCamera12);
        buttonCamera12.setOnClickListener(v -> invokeCameraEsafirm12());

        Button buttonSubmitSurveyKonsumen = findViewById(R.id.buttonSubmitSurveyKonsumen);
        buttonSubmitSurveyKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (surveyRumah.isChecked()){
//                    Intent intent = new Intent(context, SurveyRumahActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Intent intent = new Intent(context, SurveyUsahaActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
                saveSurveyKonsumen();
            }
        });
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeCMOActivity.class);
                startActivity(intent);
                finish();
            }
        });

        setSpinnerData();

    }

    private void generatekodeSurvey(){
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();

        String token = SessionManager.getToken(context);

        AddSurvey addSurvey = new AddSurvey();
        addSurvey.setKodeKonsumen(SessionManager.getKodeKonsumen(context));
        addSurvey.setJenisSurvey("Konsumen");

        apiServices.addSurvey("bearer "+token, addSurvey).enqueue(new Callback<AddSurvey>() {
            @Override
            public void onResponse(Call<AddSurvey> call, Response<AddSurvey> response) {
                loading.dismiss();
                if (response.code()==200){
                    String kodeSurvey = response.body().getData().getDataID();
                    SessionManager.saveKodeSurvey(context, kodeSurvey);
//                    Toast.makeText(context, "kodeSurvey: "+kodeSurvey, Toast.LENGTH_LONG).show();
                }else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AddSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Gagal Load Data sebelumnya, Mohon Ulangi", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void saveSurveyKonsumen(){
        String pertanyaanSatuCode = "SK001";
        String pertanyaanDuaCode = "SK002";
        String pertanyaanTigaCode = "SK003";
        String pertanyaanTigaPlatNomerCode = "DK001";
        String pertanyaanTigaWarnaCode = "DK002";
        String pertanyaanTigaMerkTipeCode = "DK003";
        String pertanyaanTigaTahunCode = "DK004";
        String pertanyaanEmpatCode = "SK004";
        String pertanyaanEmpatAnswer = "1";
        String pertanyaanEmpatJenisKreditCode = "AL001";
        String pertanyaanEmpatAngsuranCode = "AL002";
        String pertanyaanEmpaPenghasilanCode = "AL003";
        String pertanyaanLimaCode = "SK005";

        RadioButton sk00101 = findViewById(R.id.sk00101);
        RadioButton sk00102 = findViewById(R.id.sk00102);
        String pertanyaanSatuAnswer =null;
        if (sk00101.isChecked()){
            pertanyaanSatuAnswer = "1";
        }else if (sk00102.isChecked()){
            pertanyaanSatuAnswer ="2";
        }else{}

        String pertanyaanDuaAnswer = null;
        if (pertanyaanDua.getSelectedItemPosition()==1){
            pertanyaanDuaAnswer ="1";
        }else if (pertanyaanDua.getSelectedItemPosition()==2){
            pertanyaanDuaAnswer="2";
        }else if (pertanyaanDua.getSelectedItemPosition()==3){
            pertanyaanDuaAnswer="3";
        }else{ }

        RadioButton dk00101 = findViewById(R.id.dk00101);
        RadioButton dk00102 = findViewById(R.id.dk00102);
        String pertanyaanTigaPlatNomerAnswer = null;
        if (dk00101.isChecked()){
            pertanyaanTigaPlatNomerAnswer = "1";
        }else if (dk00102.isChecked()){
            pertanyaanTigaPlatNomerAnswer ="2";
        }else{}

        RadioButton dk00201 = findViewById(R.id.dk00201);
        RadioButton dk00202 = findViewById(R.id.dk00202);
        String pertanyaanTigaWarnaAnswer = null;
        if (dk00201.isChecked()){
            pertanyaanTigaWarnaAnswer = "1";
        }else if (dk00202.isChecked()){
            pertanyaanTigaWarnaAnswer ="2";
        }else{}

        RadioButton dk00301 = findViewById(R.id.dk00301);
        RadioButton dk00302 = findViewById(R.id.dk00302);
        String pertanyaanTigaMerkTipeAnswer = null;
        if (dk00301.isChecked()){
            pertanyaanTigaMerkTipeAnswer = "1";
        }else if (dk00302.isChecked()){
            pertanyaanTigaMerkTipeAnswer ="2";
        }else{}

        RadioButton dk00401 = findViewById(R.id.dk00401);
        RadioButton dk00402 = findViewById(R.id.dk00402);
        String pertanyaanTigaTahunAnswer = null;
        if (dk00401.isChecked()){
            pertanyaanTigaTahunAnswer = "1";
        }else if (dk00402.isChecked()){
            pertanyaanTigaTahunAnswer ="2";
        }else{}

        String pertanyaanEmpatJenisKreditAnswer = jenisKredit.getText().toString();
        String pertanyaanEmpatAngsuranAnswer = totalAngsuran.getText().toString();
        String pertanyaanEmpatPenghasilanAnswer = totalPenghasilan.getText().toString();

        String pertanyaanLimaAnswer = null;
        if (pertanyaanLima.getSelectedItemPosition()==1){
            pertanyaanLimaAnswer ="1";
        }else if (pertanyaanLima.getSelectedItemPosition()==2){
            pertanyaanLimaAnswer="2";
        }else if (pertanyaanLima.getSelectedItemPosition()==3){
            pertanyaanLimaAnswer="3";
        }else{ }

    }

    private void setSpinnerData() {
        ArrayAdapter<String> adapterPertanyaanDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUA);
        adapterPertanyaanDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDua.setAdapter(adapterPertanyaanDua);

        ArrayAdapter<String> adapterPertanyaanLima = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMA);
        adapterPertanyaanLima.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanLima.setAdapter(adapterPertanyaanLima);
    }
    private void invokeCameraEsafirm9(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA9);
    }
    private void invokeCameraEsafirm10(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA10);
    }
    private void invokeCameraEsafirm11(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA11);
    }
    private void invokeCameraEsafirm12(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA9){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap9 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap9).into(imageKtp);
                BitmapHelper.getInstance().setBitmap9(bitmap9);
                buttonCamera9.setVisibility(View.GONE);
                buttonCamera10.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA10){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap10 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap10).into(imageKtpSelfie);
                BitmapHelper.getInstance().setBitmap10(bitmap10);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA11){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap11 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap11).into(imageSelfieCmoKonsumen);
                BitmapHelper.getInstance().setBitmap11(bitmap11);
                buttonCamera11.setVisibility(View.GONE);
                buttonCamera12.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA12){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap12 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap12).into(imageSelfieCmoRumah);
                BitmapHelper.getInstance().setBitmap12(bitmap12);
            }
        }
    }

    private void imageClick9(){
        if(imageKtp != null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap9());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }else {

        }
    }
    private void imageClick10(){
        if (imageKtpSelfie !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap10());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick11(){
        if (imageSelfieCmoKonsumen !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap11());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick12(){
        if (imageSelfieCmoRumah !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap12());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }

    private void uploadImage9(){
        //tangkap data dari image view

        imageKtp.setDrawingCacheEnabled(true);
        imageKtp.buildDrawingCache();
        Bitmap bitmap9 = ((BitmapDrawable) imageKtp.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap9.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
//        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.VISIBLE);
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                progressBar.setProgress((int) progress);
//            }
//        });
    }

    private void uploadImage10(){
        //tangkap data dari image view

        imageKtpSelfie.setDrawingCacheEnabled(true);
        imageKtpSelfie.buildDrawingCache();
        Bitmap bitmap10 = ((BitmapDrawable) imageKtpSelfie.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap10.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
//        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.VISIBLE);
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                progressBar.setProgress((int) progress);
//            }
//        });
    }

    private void uploadImage11(){
        //tangkap data dari image view

        imageSelfieCmoKonsumen.setDrawingCacheEnabled(true);
        imageSelfieCmoKonsumen.buildDrawingCache();
        Bitmap bitmap11 = ((BitmapDrawable) imageSelfieCmoKonsumen.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap11.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
//        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.VISIBLE);
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                progressBar.setProgress((int) progress);
//            }
//        });
    }

    private void uploadImage12(){
        //tangkap data dari image view

        imageSelfieCmoRumah.setDrawingCacheEnabled(true);
        imageSelfieCmoRumah.buildDrawingCache();
        Bitmap bitmap12 = ((BitmapDrawable) imageSelfieCmoRumah.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap12.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[]bytes=stream.toByteArray();

        //lokasi gambar
        String namaFile = UUID.randomUUID()+".jpg";
        String pathImage = "gambar/"+namaFile;
//        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(context, "Upload Gagal", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                progressBar.setVisibility(View.VISIBLE);
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                progressBar.setProgress((int) progress);
//            }
//        });
    }
}
