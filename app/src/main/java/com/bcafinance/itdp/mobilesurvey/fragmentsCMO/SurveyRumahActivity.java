package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.customs.CustomExpandCollapseBar;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormSurveyRumahKonsumenEdit;
import com.bcafinance.itdp.mobilesurvey.helper.APIUtilities;
import com.bcafinance.itdp.mobilesurvey.helper.AddSurvey.AddSurvey;
import com.bcafinance.itdp.mobilesurvey.helper.BitmapHelper;
import com.bcafinance.itdp.mobilesurvey.helper.CloseSurvey.CloseSurvey;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.DataQuest;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.EditSurvey;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.Narasumber1;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.Narasumber2;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.SubQuest;
import com.bcafinance.itdp.mobilesurvey.helper.RequestAPIServices;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.bcafinance.itdp.mobilesurvey.utility.inputSurvey;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import mumayank.com.airlocationlibrary.AirLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.text.TextUtils.isEmpty;

public class SurveyRumahActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private Context context = this;
    private TextView latitude, longitude, namaAlamatRumah, barExpandNarasumber1, barExpandNarasumber2;
    private ExpandableLinearLayout layoutNarasumber1, layoutNarasumber2;
    private CustomExpandCollapseBar expandCollapseBar;
    private Spinner pertanyaanEnam, pertanyaanTujuh, pertanyaanDelapan, pertanyaanSepuluh, pertanyaanSebelas, pertanyaanDuaBelas, pertanyaanTigaBelas;
    private EditText namaNarasumber1, jawabanEnamBelas, jawabanTujuhBelas, jawabanDelapanBelasTipe, jawabanDelapanBelasWarna, jawabanDuaPuluh, namaNarasumber2,
            jawabanDuaTiga, jawabanDuaEmpat, jawabanDuaLimaTipe, jawabanDuaLimaWarna, jawabanDuaTujuh;
    private Button buttonSubmitRumah, buttonBack, buttonCamera1, buttonCamera2, buttonCamera3, buttonCamera4;
    private ImageView imageRumah1, imageRumah2, imageJalanRumah1, imageJalanRumah2;
    private Spinner pertanyaanEmpatBelas, pertanyaanLimaBelas, pertanyaanEnamBelas, pertanyaanTujuhBelas, pertanyaanSembilanBelas,
            pertanyaanDuaPuluh, pertanyaanDuaSatu, pertanyaanDuaDua, pertanyaanDuaTiga, pertanyaanDuaEmpat, pertanyaanDuaEnam, pertanyaanDuaTujuh;
    private RadioGroup pertanyaanDelapanBelas, pertanyaanSembilan, pertanyaanDuaLima;

    private int REQUEST_CODE_CAMERA1 = 1;
    private int REQUEST_CODE_CAMERA2 = 2;
    private int REQUEST_CODE_CAMERA3 = 3;
    private int REQUEST_CODE_CAMERA4 = 4;
    private int PLACE_PICKER = 0;

    private AirLocation airLocation;
    private GoogleMap mMap;
    LocationManager locationManager;
    private ProgressBar progressBar;
    private TextView imageText;
    RequestAPIServices apiServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_rumah);
        apiServices = APIUtilities.getAPIServices();
        generatekodeSurvey();
        progressBar = findViewById(R.id.progressBar);

        pertanyaanEnam = findViewById(R.id.pertanyaanEnam);
        pertanyaanTujuh = findViewById(R.id.pertanyaanTujuh);
        pertanyaanDelapan = findViewById(R.id.pertanyaanDelapan);
        pertanyaanSembilan = findViewById(R.id.pertanyaanSembilan);
        pertanyaanSepuluh = findViewById(R.id.pertanyaanSepuluh);
        pertanyaanSebelas = findViewById(R.id.pertanyaanSebelas);
        pertanyaanDuaBelas = findViewById(R.id.pertanyaanDuaBelas);
        pertanyaanTigaBelas = findViewById(R.id.pertanyaanTigaBelas);

        barExpandNarasumber1 =findViewById(R.id.barExpandNarasumber1);
        layoutNarasumber1 = findViewById(R.id.layoutNarasumber1);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber1, layoutNarasumber1,false);

        barExpandNarasumber2 =findViewById(R.id.barExpandNarasumber2);
        layoutNarasumber2 = findViewById(R.id.layoutNarasumber2);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber2, layoutNarasumber2,false);

        namaNarasumber1 = findViewById(R.id.namaNarasumber1);
        jawabanEnamBelas = findViewById(R.id.jawabanEnamBelas);
        jawabanTujuhBelas = findViewById(R.id.jawabanTujuhBelas);
        jawabanDelapanBelasTipe = findViewById(R.id.jawabanDelapanBelasTipe);
        jawabanDelapanBelasWarna = findViewById(R.id.jawabanDelapanBelasWarna);
        jawabanDuaPuluh = findViewById(R.id.jawabanDuaPuluh);
        namaNarasumber2 = findViewById(R.id.namaNarasumber2);
        jawabanDuaTiga = findViewById(R.id.jawabanDuaTiga);
        jawabanDuaEmpat = findViewById(R.id.jawabanDuaEmpat);
        jawabanDuaLimaTipe = findViewById(R.id.jawabanDuaLimaTipe);
        jawabanDuaLimaWarna = findViewById(R.id.jawabanDuaLimaWarna);
        jawabanDuaTujuh = findViewById(R.id.jawabanDuaTujuh);
        pertanyaanEmpatBelas = findViewById(R.id.pertanyaanEmpatBelas);
        pertanyaanLimaBelas = findViewById(R.id.pertanyaanLimaBelas);
        pertanyaanEnamBelas = findViewById(R.id.pertanyaanEnamBelas);
        pertanyaanEnamBelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pertanyaanEnamBelas.getSelectedItemId() == 1){
                    jawabanEnamBelas.setVisibility(View.VISIBLE);
                }else if (pertanyaanEnamBelas.getSelectedItemId() == 6){
                    jawabanEnamBelas.setVisibility(View.VISIBLE);
                }else if (pertanyaanEnamBelas.getSelectedItemId() == 7){
                    jawabanEnamBelas.setVisibility(View.VISIBLE);
                }else{
                    jawabanEnamBelas.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanTujuhBelas = findViewById(R.id.pertanyaanTujuhBelas);
        pertanyaanTujuhBelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pertanyaanTujuhBelas.getSelectedItemId() == 1){
                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
                }else if (pertanyaanTujuhBelas.getSelectedItemId() == 6){
                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
                }else if (pertanyaanTujuhBelas.getSelectedItemId() == 7){
                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
                }else{
                    jawabanTujuhBelas.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanDelapanBelas = findViewById(R.id.pertanyaanDelapanBelas);
        pertanyaanDelapanBelas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ns00501:
                        jawabanDelapanBelasTipe.setVisibility(View.VISIBLE);
                        jawabanDelapanBelasWarna.setVisibility(View.VISIBLE);
                        break;
                    case R.id.ns00502:
                        jawabanDelapanBelasTipe.setVisibility(View.GONE);
                        jawabanDelapanBelasWarna.setVisibility(View.GONE);
                        break;
                }
            }
        });

        pertanyaanSembilanBelas = findViewById(R.id.pertanyaanSembilanBelas);
        pertanyaanDuaPuluh = findViewById(R.id.pertanyaanDuaPuluh);
        pertanyaanDuaPuluh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanDuaPuluh.getSelectedItemId()==6){
                    jawabanDuaPuluh.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaPuluh.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanDuaSatu = findViewById(R.id.pertanyaanDuaSatu);
        pertanyaanDuaDua = findViewById(R.id.pertanyaanDuaDua);
        pertanyaanDuaTiga = findViewById(R.id.pertanyaanDuaTiga);
        pertanyaanDuaTiga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pertanyaanDuaTiga.getSelectedItemId() == 1){
                    jawabanDuaTiga.setVisibility(View.VISIBLE);
                }else if (pertanyaanDuaTiga.getSelectedItemId() == 6){
                    jawabanDuaTiga.setVisibility(View.VISIBLE);
                }else if (pertanyaanDuaTiga.getSelectedItemId() == 7){
                    jawabanDuaTiga.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaTiga.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        pertanyaanDuaEmpat = findViewById(R.id.pertanyaanDuaEmpat);
        pertanyaanDuaEmpat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pertanyaanDuaEmpat.getSelectedItemId() == 1){
                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
                }else if (pertanyaanDuaEmpat.getSelectedItemId() == 6){
                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
                }else if (pertanyaanDuaEmpat.getSelectedItemId() == 7){
                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaEmpat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        pertanyaanDuaLima = findViewById(R.id.pertanyaanDuaLima);
        pertanyaanDuaLima.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ns0050101:
                        jawabanDuaLimaTipe.setVisibility(View.VISIBLE);
                        jawabanDuaLimaWarna.setVisibility(View.VISIBLE);
                        break;
                    case R.id.ns0050202:
                        jawabanDuaLimaTipe.setVisibility(View.GONE);
                        jawabanDuaLimaWarna.setVisibility(View.GONE);
                        break;
                }
            }
        });

        pertanyaanDuaEnam = findViewById(R.id.pertanyaanDuaEnam);
        pertanyaanDuaTujuh = findViewById(R.id.pertanyaanDuaTujuh);
        pertanyaanDuaTujuh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pertanyaanDuaTujuh.getSelectedItemId()==6){
                    jawabanDuaTujuh.setVisibility(View.VISIBLE);
                }else{
                    jawabanDuaTujuh.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        namaAlamatRumah = findViewById(R.id.namaAlamatRumah);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);

        imageRumah1 = findViewById(R.id.imageRumah1);
        imageRumah1.setOnClickListener(v -> imageClick());

        imageRumah2 = findViewById(R.id.imageRumah2);
        imageRumah2.setOnClickListener(v -> imageClick2());

        imageJalanRumah1 = findViewById(R.id.imageJalanRumah1);
        imageJalanRumah1.setOnClickListener(v -> imageClick3());

        imageJalanRumah2 = findViewById(R.id.imageJalanRumah2);
        imageJalanRumah2.setOnClickListener(v -> imageClick4());

        buttonCamera1 = findViewById(R.id.buttonCamera1);
        buttonCamera1.setOnClickListener(v -> invokeCameraEsafirm1());

        buttonCamera2 = findViewById(R.id.buttonCamera2);
        buttonCamera2.setOnClickListener(v -> invokeCameraEsafirm2());

        buttonCamera3 = findViewById(R.id.buttonCamera3);
        buttonCamera3.setOnClickListener(v -> invokeCameraEsafirm3());

        buttonCamera4 = findViewById(R.id.buttonCamera4);
        buttonCamera4.setOnClickListener(v -> invokeCameraEsafirm4());

        if(!checkIsGPSEnabled()) {
            showSettingLocation();
        }
        setSpinnerData();

        buttonSubmitRumah = findViewById(R.id.buttonSubmitRumah);
        buttonSubmitRumah.setOnClickListener(v -> {
            saveRumah();
        });
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(context, SurveyKonsumenActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void generatekodeSurvey(){
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();

        String token = SessionManager.getToken(context);

        AddSurvey addSurvey = new AddSurvey();
        addSurvey.setKodeKonsumen(SessionManager.getKodeKonsumen(context));
        addSurvey.setJenisSurvey("RumahKonsumen");

        apiServices.addSurvey("bearer "+token, addSurvey).enqueue(new Callback<AddSurvey>() {
            @Override
            public void onResponse(Call<AddSurvey> call, Response<AddSurvey> response) {
                loading.dismiss();
                if (response.code()==200){
                    String kodeSurvey = response.body().getData().getDataID();
                    SessionManager.saveKodeSurveyRumahKonsumen(context, kodeSurvey);
//                    Toast.makeText(context, "kodeSurvey: "+kodeSurvey, Toast.LENGTH_LONG).show();
                }else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AddSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Gagal Load Data sebelumnya, Mohon Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void saveRumah(){
        String pertanyaanEnamCode = "RK001";
        String pertanyaanTujuhCode = "RK002";
        String pertanyaanDelapanCode = "RK003";
        String pertanyaanSembilanCode = "RK004";
        String pertanyaanSepuluhCode = "RK005";
        String pertanyaanSebelasCode = "RK006";
        String pertanyaanDuaBelasCode = "RK007";
        String pertanyaanTigaBelasCode = "RK008";

        String pertanyaanEnamAnswer = null;
        if (pertanyaanEnam.getSelectedItemPosition()==1){
            pertanyaanEnamAnswer="1";
        }else if (pertanyaanEnam.getSelectedItemPosition()==2){
            pertanyaanEnamAnswer="2";
        }else if (pertanyaanEnam.getSelectedItemPosition()==3){
            pertanyaanEnamAnswer="3";
        }else if (pertanyaanEnam.getSelectedItemPosition()==4){
            pertanyaanEnamAnswer="4";
        }else{}

        String pertanyaanTujuhAnswer = null;
        if (pertanyaanTujuh.getSelectedItemPosition()==1){
            pertanyaanTujuhAnswer="1";
        }else if (pertanyaanTujuh.getSelectedItemPosition()==2){
            pertanyaanTujuhAnswer="2";
        }else if (pertanyaanTujuh.getSelectedItemPosition()==3){
            pertanyaanTujuhAnswer="3";
        }else if (pertanyaanTujuh.getSelectedItemPosition()==4){
            pertanyaanTujuhAnswer="4";
        }else if (pertanyaanTujuh.getSelectedItemPosition()==5){
            pertanyaanTujuhAnswer="5";
        }else if (pertanyaanTujuh.getSelectedItemPosition()==6){
            pertanyaanTujuhAnswer="6";
        }else{}

        String pertanyaanDelapanAnswer = null ;
        if (pertanyaanDelapan.getSelectedItemPosition()==1){
            pertanyaanDelapanAnswer="1";
        }else if (pertanyaanDelapan.getSelectedItemPosition()==2){
            pertanyaanDelapanAnswer="2";
        }else if (pertanyaanDelapan.getSelectedItemPosition()==3){
            pertanyaanDelapanAnswer="3";
        }else if (pertanyaanDelapan.getSelectedItemPosition()==4){
            pertanyaanDelapanAnswer="4";
        }else{}

        RadioButton rk00401 = findViewById(R.id.rk00401);
        RadioButton rk00402 = findViewById(R.id.rk00402);
        String pertanyaanSembilanAnswer = null;
        if (rk00401.isChecked()){
            pertanyaanSembilanAnswer="1";
        }else if (rk00402.isChecked()){
            pertanyaanSembilanAnswer="2";
        }else{}

        String pertanyaanSepuluhAnswer = null;
        if (pertanyaanSepuluh.getSelectedItemPosition()==1){
            pertanyaanSepuluhAnswer="1";
        }else if (pertanyaanSepuluh.getSelectedItemPosition()==2){
            pertanyaanSepuluhAnswer="2";
        }else if (pertanyaanSepuluh.getSelectedItemPosition()==3){
            pertanyaanSepuluhAnswer="3";
        }else if (pertanyaanSepuluh.getSelectedItemPosition()==4){
            pertanyaanSepuluhAnswer="4";
        }else{}

        String pertanyaanSebelasAnswer = null;
        if (pertanyaanSebelas.getSelectedItemPosition()==1){
            pertanyaanSebelasAnswer="1";
        }else if (pertanyaanSebelas.getSelectedItemPosition()==2){
            pertanyaanSebelasAnswer="2";
        }else if (pertanyaanSebelas.getSelectedItemPosition()==3){
            pertanyaanSebelasAnswer="3";
        }else if (pertanyaanSebelas.getSelectedItemPosition()==4){
            pertanyaanSebelasAnswer="4";
        }else if (pertanyaanSebelas.getSelectedItemPosition()==5){
            pertanyaanSebelasAnswer="5";
        }else{}

        String pertanyaanDuaBelasAnswer = null;
        if (pertanyaanDuaBelas.getSelectedItemPosition()==1){
            pertanyaanDuaBelasAnswer="1";
        }else if (pertanyaanDuaBelas.getSelectedItemPosition()==2){
            pertanyaanDuaBelasAnswer="2";
        }else if (pertanyaanDuaBelas.getSelectedItemPosition()==3){
            pertanyaanDuaBelasAnswer="3";
        }else if (pertanyaanDuaBelas.getSelectedItemPosition()==4){
            pertanyaanDuaBelasAnswer="4";
        }else if (pertanyaanDuaBelas.getSelectedItemPosition()==5){
            pertanyaanDuaBelasAnswer="5";
        }else if (pertanyaanDuaBelas.getSelectedItemPosition()==6){
            pertanyaanDuaBelasAnswer="6";
        }else{}

        String pertanyaanTigaBelasAnswer = null;
        if (pertanyaanTigaBelas.getSelectedItemPosition()==1){
            pertanyaanTigaBelasAnswer="1";
        }else if (pertanyaanTigaBelas.getSelectedItemPosition()==2){
            pertanyaanTigaBelasAnswer="2";
        }else if (pertanyaanTigaBelas.getSelectedItemPosition()==3){
            pertanyaanTigaBelasAnswer="3";
        }else if (pertanyaanTigaBelas.getSelectedItemPosition()==4){
            pertanyaanTigaBelasAnswer="4";
        }else if (pertanyaanTigaBelas.getSelectedItemPosition()==5){
            pertanyaanTigaBelasAnswer="5";
        }else{}

        String narasumberSatu = "NS01";
        String pertanyaanEmpatBelasCode = "NS001";
        String pertanyaanLimaBelasCode = "NS002";
        String pertanyaanEnamBelasCode = "NS003";
        String pertanyaanTujuhBelasCode = "NS004";
        String pertanyaanDelapanBelasCode = "NS005";
        String pertanyaanSembilanBelasCode = "NS006";
        String pertanyaanDuaPuluhCode = "NS007";
        String namaNarasumberCode = "NS010";

        String pertanyaanEmpatBelasAnswer = null;
        if (pertanyaanEmpatBelas.getSelectedItemPosition()==1){
            pertanyaanEmpatBelasAnswer="1";
        }else if (pertanyaanEmpatBelas.getSelectedItemPosition()==2){
            pertanyaanEmpatBelasAnswer="2";
        }else if (pertanyaanEmpatBelas.getSelectedItemPosition()==3){
            pertanyaanEmpatBelasAnswer="3";
        }else if (pertanyaanEmpatBelas.getSelectedItemPosition()==4){
            pertanyaanEmpatBelasAnswer="4";
        }else{}

        String pertanyaanLimaBelasAnswer = null;
        if (pertanyaanLimaBelas.getSelectedItemPosition()==1){
            pertanyaanLimaBelasAnswer="1";
        }else if (pertanyaanLimaBelas.getSelectedItemPosition()==2){
            pertanyaanLimaBelasAnswer="2";
        }else if (pertanyaanLimaBelas.getSelectedItemPosition()==3){
            pertanyaanLimaBelasAnswer="3";
        }else if (pertanyaanLimaBelas.getSelectedItemPosition()==4){
            pertanyaanLimaBelasAnswer="4";
        }else if (pertanyaanLimaBelas.getSelectedItemPosition()==5){
            pertanyaanLimaBelasAnswer="5";
        }else{}

        String pertanyaanEnamBelasAnswer = null;
        if (pertanyaanEnamBelas.getSelectedItemPosition()==1){
            pertanyaanEnamBelasAnswer="1";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==2){
            pertanyaanEnamBelasAnswer="2";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==3){
            pertanyaanEnamBelasAnswer="3";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==4){
            pertanyaanEnamBelasAnswer="4";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==5){
            pertanyaanEnamBelasAnswer="5";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==6){
            pertanyaanEnamBelasAnswer="6";
        }else if (pertanyaanEnamBelas.getSelectedItemPosition()==7){
            pertanyaanEnamBelasAnswer="7";
        }else{}

        String pertanyaanTujuhBelasAnswer = null;
        if (pertanyaanTujuhBelas.getSelectedItemPosition()==1){
            pertanyaanTujuhBelasAnswer="1";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==2){
            pertanyaanTujuhBelasAnswer="2";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==3){
            pertanyaanTujuhBelasAnswer="3";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==4){
            pertanyaanTujuhBelasAnswer="4";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==5){
            pertanyaanTujuhBelasAnswer="5";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==6){
            pertanyaanTujuhBelasAnswer="6";
        }else if (pertanyaanTujuhBelas.getSelectedItemPosition()==7){
            pertanyaanTujuhBelasAnswer="7";
        }else{}

        RadioButton ns00501 = findViewById(R.id.ns00501);
        RadioButton ns00502 = findViewById(R.id.ns00502);
        String pertanyaanDelapanBelasAnswer = null;
        if (ns00502.isChecked()){
            pertanyaanDelapanBelasAnswer="1";
        }else if (ns00501.isChecked()){
            pertanyaanDelapanBelasAnswer="2";
        }else{}

        String pertanyaanSembilanBelasAnswer = null;
        if (pertanyaanSembilanBelas.getSelectedItemPosition()==1){
            pertanyaanSembilanBelasAnswer="1";
        }else if (pertanyaanSembilanBelas.getSelectedItemPosition()==2){
            pertanyaanSembilanBelasAnswer="2";
        }else if (pertanyaanSembilanBelas.getSelectedItemPosition()==3){
            pertanyaanSembilanBelasAnswer="3";
        }else if (pertanyaanSembilanBelas.getSelectedItemPosition()==4){
            pertanyaanSembilanBelasAnswer="4";
        }else{}

        String pertanyaanDuaPuluhAnswer = null;
        if (pertanyaanDuaPuluh.getSelectedItemPosition()==1){
            pertanyaanDuaPuluhAnswer="1";
        }else if (pertanyaanDuaPuluh.getSelectedItemPosition()==2){
            pertanyaanDuaPuluhAnswer="2";
        }else if (pertanyaanDuaPuluh.getSelectedItemPosition()==3){
            pertanyaanDuaPuluhAnswer="3";
        }else if (pertanyaanDuaPuluh.getSelectedItemPosition()==4){
            pertanyaanDuaPuluhAnswer="4";
        }else if (pertanyaanDuaPuluh.getSelectedItemPosition()==5){
            pertanyaanDuaPuluhAnswer="5";
        }else if (pertanyaanDuaPuluh.getSelectedItemPosition()==6){
            pertanyaanDuaPuluhAnswer="6";
        }else{}

        String narasumberDua = "NS02";
        String pertanyaanDuaSatuCode = "NS001";
        String pertanyaanDuaDuaCode = "NS002";
        String pertanyaanDuaTigaCode = "NS003";
        String pertanyaanDuaEmpatCode = "NS004";
        String pertanyaanDuaLimaCode = "NS005";
        String pertanyaanDuaEnamCode = "NS006";
        String pertanyaanDuaTujuhCode = "NS007";

        String pertanyaanDuaSatuAnswer = null;
        if (pertanyaanDuaSatu.getSelectedItemPosition()==1){
            pertanyaanDuaSatuAnswer="1";
        }else if (pertanyaanDuaSatu.getSelectedItemPosition()==2){
            pertanyaanDuaSatuAnswer="2";
        }else if (pertanyaanDuaSatu.getSelectedItemPosition()==3){
            pertanyaanDuaSatuAnswer="3";
        }else if (pertanyaanDuaSatu.getSelectedItemPosition()==4){
            pertanyaanDuaSatuAnswer="4";
        }else{}

        String pertanyaanDuaDuaAnswer = null;
        if (pertanyaanDuaDua.getSelectedItemPosition()==1){
            pertanyaanDuaDuaAnswer="1";
        }else if (pertanyaanDuaDua.getSelectedItemPosition()==2){
            pertanyaanDuaDuaAnswer="2";
        }else if (pertanyaanDuaDua.getSelectedItemPosition()==3){
            pertanyaanDuaDuaAnswer="3";
        }else if (pertanyaanDuaDua.getSelectedItemPosition()==4){
            pertanyaanDuaDuaAnswer="4";
        }else if (pertanyaanDuaDua.getSelectedItemPosition()==5){
            pertanyaanDuaDuaAnswer="5";
        }else{}

        String pertanyaanDuaTigaAnswer = null;
        if (pertanyaanDuaTiga.getSelectedItemPosition()==1){
            pertanyaanDuaTigaAnswer="1";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==2){
            pertanyaanDuaTigaAnswer="2";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==3){
            pertanyaanDuaTigaAnswer="3";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==4){
            pertanyaanDuaTigaAnswer="4";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==5){
            pertanyaanDuaTigaAnswer="5";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==6){
            pertanyaanDuaTigaAnswer="6";
        }else if (pertanyaanDuaTiga.getSelectedItemPosition()==7){
            pertanyaanDuaTigaAnswer="7";
        }else{}

        String pertanyaanDuaEmpatAnswer = null;
        if (pertanyaanDuaEmpat.getSelectedItemPosition()==1){
            pertanyaanDuaEmpatAnswer="1";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==2){
            pertanyaanDuaEmpatAnswer="2";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==3){
            pertanyaanDuaEmpatAnswer="3";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==4){
            pertanyaanDuaEmpatAnswer="4";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==5){
            pertanyaanDuaEmpatAnswer="5";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==6){
            pertanyaanDuaEmpatAnswer="6";
        }else if (pertanyaanDuaEmpat.getSelectedItemPosition()==7){
            pertanyaanDuaEmpatAnswer="7";
        }else{}

        RadioButton ns0050101 = findViewById(R.id.ns0050101);
        RadioButton ns0050202 = findViewById(R.id.ns0050202);
        String pertanyaanDuaLimaAnswer = null;
        if (ns0050202.isChecked()){
            pertanyaanDuaLimaAnswer="1";
        }else if (ns0050101.isChecked()){
            pertanyaanDuaLimaAnswer="2";
        }else{}

        String pertanyaanDuaEnamAnswer = null;
        if (pertanyaanDuaEnam.getSelectedItemPosition()==1){
            pertanyaanDuaEnamAnswer="1";
        }else if (pertanyaanDuaEnam.getSelectedItemPosition()==2){
            pertanyaanDuaEnamAnswer="2";
        }else if (pertanyaanDuaEnam.getSelectedItemPosition()==3){
            pertanyaanDuaEnamAnswer="3";
        }else if (pertanyaanDuaEnam.getSelectedItemPosition()==4){
            pertanyaanDuaEnamAnswer="4";
        }else{}

        String pertanyaanDuaTujuhAnswer = null;
        if (pertanyaanDuaTujuh.getSelectedItemPosition()==1){
            pertanyaanDuaTujuhAnswer="1";
        }else if (pertanyaanDuaTujuh.getSelectedItemPosition()==2){
            pertanyaanDuaTujuhAnswer="2";
        }else if (pertanyaanDuaTujuh.getSelectedItemPosition()==3){
            pertanyaanDuaTujuhAnswer="3";
        }else if (pertanyaanDuaTujuh.getSelectedItemPosition()==4){
            pertanyaanDuaTujuhAnswer="4";
        }else if (pertanyaanDuaTujuh.getSelectedItemPosition()==5){
            pertanyaanDuaTujuhAnswer="5";
        }else if (pertanyaanDuaTujuh.getSelectedItemPosition()==6){
            pertanyaanDuaTujuhAnswer="6";
        }else{}

        String jawabanEnamBelasValue = jawabanEnamBelas.getText().toString();
        String jawabanTujuhBelasValue = jawabanTujuhBelas.getText().toString();
        String jawabanDelapanBelasTipeValue = jawabanDelapanBelasTipe.getText().toString();
        String jawabanDelapanBelasWarnaValue = jawabanDelapanBelasWarna.getText().toString();
        String jawabanDuaPuluhValue = jawabanDuaPuluh.getText().toString();

        String jawabanDuaTigaValue = jawabanDuaTiga.getText().toString();
        String jawabanDuaEmpatValue = jawabanDuaEmpat.getText().toString();
        String jawabanDuaLimaTipeValue = jawabanDuaLimaTipe.getText().toString();
        String jawabanDuaLimaWarnaValue = jawabanDuaLimaWarna.getText().toString();
        String jawabanDuaTujuhValue = jawabanDuaTujuh.getText().toString();

        String namaNarasumber1Answer = namaNarasumber1.getText().toString();
        String namaNarasumber2Answer = namaNarasumber2.getText().toString();


        String namaAlamatRumahValue = namaAlamatRumah.getText().toString();
        String latitude1Value = latitude.getText().toString();
        String longitude1Value = longitude.getText().toString();

        List<Narasumber2> narasumber2List = new ArrayList<>();

        Narasumber2 narasumber2001 = new Narasumber2();
        narasumber2001.setCodePertanyaan(namaNarasumberCode);
        narasumber2001.setJenisNarasumber(narasumberDua);
        narasumber2001.setAnswer(namaNarasumber2Answer);
        narasumber2001.setDesc("");

        Narasumber2 narasumber0201 = new Narasumber2();
        narasumber0201.setCodePertanyaan(pertanyaanDuaSatuCode);
        narasumber0201.setJenisNarasumber(narasumberDua);
        narasumber0201.setAnswer(pertanyaanDuaSatuAnswer);
        narasumber0201.setDesc("");

        Narasumber2 narasumber0202 = new Narasumber2();
        narasumber0202.setCodePertanyaan(pertanyaanDuaDuaCode);
        narasumber0202.setJenisNarasumber(narasumberDua);
        narasumber0202.setAnswer(pertanyaanDuaDuaAnswer);
        narasumber0202.setDesc("");

        Narasumber2 narasumber0203 = new Narasumber2();
        narasumber0203.setCodePertanyaan(pertanyaanDuaTigaCode);
        narasumber0203.setJenisNarasumber(narasumberDua);
        narasumber0203.setAnswer(pertanyaanDuaTigaAnswer);
        narasumber0203.setDesc(jawabanDuaTigaValue);

        Narasumber2 narasumber0204 = new Narasumber2();
        narasumber0204.setCodePertanyaan(pertanyaanDuaEmpatCode);
        narasumber0204.setJenisNarasumber(narasumberDua);
        narasumber0204.setAnswer(pertanyaanDuaEmpatAnswer);
        narasumber0204.setDesc(jawabanDuaEmpatValue);

        Narasumber2 narasumber0205 = new Narasumber2();
        if (pertanyaanDuaLimaAnswer=="2"){
            narasumber0205.setCodePertanyaan(pertanyaanDuaLimaCode);
            narasumber0205.setJenisNarasumber(narasumberDua);
            narasumber0205.setAnswer(pertanyaanDuaLimaAnswer);
            narasumber0205.setDesc("Tipe : "+jawabanDuaLimaTipeValue+" & Warna : "+jawabanDuaLimaWarnaValue);
        }else{
            narasumber0205.setCodePertanyaan(pertanyaanDuaLimaCode);
            narasumber0205.setJenisNarasumber(narasumberDua);
            narasumber0205.setAnswer(pertanyaanDuaLimaAnswer);
            narasumber0205.setDesc("");
        }

        Narasumber2 narasumber0206 = new Narasumber2();
        narasumber0206.setCodePertanyaan(pertanyaanDuaEnamCode);
        narasumber0206.setJenisNarasumber(narasumberDua);
        narasumber0206.setAnswer(pertanyaanDuaEnamAnswer);
        narasumber0206.setDesc("");

        Narasumber2 narasumber0207 = new Narasumber2();
        narasumber0207.setCodePertanyaan(pertanyaanDuaTujuhCode);
        narasumber0207.setJenisNarasumber(narasumberDua);
        narasumber0207.setAnswer(pertanyaanDuaTujuhAnswer);
        narasumber0207.setDesc(jawabanDuaTujuhValue);

        narasumber2List.add(narasumber2001);
        narasumber2List.add(narasumber0201);
        narasumber2List.add(narasumber0202);
        narasumber2List.add(narasumber0203);
        narasumber2List.add(narasumber0204);
        narasumber2List.add(narasumber0205);
        narasumber2List.add(narasumber0206);
        narasumber2List.add(narasumber0207);

        List<Narasumber1> narasumber1List = new ArrayList<>();

        Narasumber1 narasumber1001 = new Narasumber1();
        narasumber1001.setCodePertanyaan(namaNarasumberCode);
        narasumber1001.setJenisNarasumber(narasumberSatu);
        narasumber1001.setAnswer(namaNarasumber1Answer);
        narasumber1001.setDesc("");

        Narasumber1 narasumber0101 = new Narasumber1();
        narasumber0101.setCodePertanyaan(pertanyaanEmpatBelasCode);
        narasumber0101.setJenisNarasumber(narasumberSatu);
        narasumber0101.setAnswer(pertanyaanEmpatBelasAnswer);
        narasumber0101.setDesc("");

        Narasumber1 narasumber0102 = new Narasumber1();
        narasumber0102.setCodePertanyaan(pertanyaanLimaBelasCode);
        narasumber0102.setJenisNarasumber(narasumberSatu);
        narasumber0102.setAnswer(pertanyaanLimaBelasAnswer);
        narasumber0102.setDesc("");

        Narasumber1 narasumber0103 = new Narasumber1();
        narasumber0103.setCodePertanyaan(pertanyaanEnamBelasCode);
        narasumber0103.setJenisNarasumber(narasumberSatu);
        narasumber0103.setAnswer(pertanyaanEnamBelasAnswer);
        narasumber0103.setDesc(jawabanEnamBelasValue);

        Narasumber1 narasumber0104 = new Narasumber1();
        narasumber0104.setCodePertanyaan(pertanyaanTujuhBelasCode);
        narasumber0104.setJenisNarasumber(narasumberSatu);
        narasumber0104.setAnswer(pertanyaanTujuhBelasAnswer);
        narasumber0104.setDesc(jawabanTujuhBelasValue);

        Narasumber1 narasumber0105 = new Narasumber1();
        if (pertanyaanDelapanBelasAnswer=="2"){
            narasumber0105.setCodePertanyaan(pertanyaanDelapanBelasCode);
            narasumber0105.setJenisNarasumber(narasumberSatu);
            narasumber0105.setAnswer(pertanyaanDelapanBelasAnswer);
            narasumber0105.setDesc("Tipe : "+jawabanDelapanBelasTipeValue+" & Warna : "+jawabanDelapanBelasWarnaValue);
        }else{
            narasumber0105.setCodePertanyaan(pertanyaanDelapanBelasCode);
            narasumber0105.setJenisNarasumber(narasumberSatu);
            narasumber0105.setAnswer(pertanyaanDelapanBelasAnswer);
            narasumber0105.setDesc("");
        }

        Narasumber1 narasumber0106 = new Narasumber1();
        narasumber0106.setCodePertanyaan(pertanyaanSembilanBelasCode);
        narasumber0106.setJenisNarasumber(narasumberSatu);
        narasumber0106.setAnswer(pertanyaanSembilanBelasAnswer);
        narasumber0106.setDesc("");

        Narasumber1 narasumber0107 = new Narasumber1();
        narasumber0107.setCodePertanyaan(pertanyaanDuaPuluhCode);
        narasumber0107.setJenisNarasumber(narasumberSatu);
        narasumber0107.setAnswer(pertanyaanDuaPuluhAnswer);
        narasumber0107.setDesc(jawabanDuaPuluhValue);

        narasumber1List.add(narasumber1001);
        narasumber1List.add(narasumber0101);
        narasumber1List.add(narasumber0102);
        narasumber1List.add(narasumber0103);
        narasumber1List.add(narasumber0104);
        narasumber1List.add(narasumber0105);
        narasumber1List.add(narasumber0106);
        narasumber1List.add(narasumber0107);

        List<SubQuest> subQuest = new ArrayList<>();

        List<DataQuest> dataQuest = new ArrayList<>();

        DataQuest dataQuest1 = new DataQuest();
        dataQuest1.setCodeQuest(pertanyaanEnamCode);
        dataQuest1.setAnswer(pertanyaanEnamAnswer);
        dataQuest1.setDesc("");
        dataQuest1.setSubQuest(subQuest);

        DataQuest dataQuest2 = new DataQuest();
        dataQuest2.setCodeQuest(pertanyaanTujuhCode);
        dataQuest2.setAnswer(pertanyaanTujuhAnswer);
        dataQuest2.setDesc("");
        dataQuest2.setSubQuest(subQuest);

        DataQuest dataQuest3 = new DataQuest();
        dataQuest3.setCodeQuest(pertanyaanDelapanCode);
        dataQuest3.setAnswer(pertanyaanDelapanAnswer);
        dataQuest3.setDesc("");
        dataQuest3.setSubQuest(subQuest);

        DataQuest dataQuest4 = new DataQuest();
        dataQuest4.setCodeQuest(pertanyaanSembilanCode);
        dataQuest4.setAnswer(pertanyaanSembilanAnswer);
        dataQuest4.setDesc("");
        dataQuest4.setSubQuest(subQuest);

        DataQuest dataQuest5 = new DataQuest();
        dataQuest5.setCodeQuest(pertanyaanSepuluhCode);
        dataQuest5.setAnswer(pertanyaanSepuluhAnswer);
        dataQuest5.setDesc("");
        dataQuest5.setSubQuest(subQuest);

        DataQuest dataQuest6 = new DataQuest();
        dataQuest6.setCodeQuest(pertanyaanSebelasCode);
        dataQuest6.setAnswer(pertanyaanSebelasAnswer);
        dataQuest6.setDesc("");
        dataQuest6.setSubQuest(subQuest);

        DataQuest dataQuest7 = new DataQuest();
        dataQuest7.setCodeQuest(pertanyaanDuaBelasCode);
        dataQuest7.setAnswer(pertanyaanDuaBelasAnswer);
        dataQuest7.setDesc("");
        dataQuest7.setSubQuest(subQuest);

        DataQuest dataQuest8 = new DataQuest();
        dataQuest8.setCodeQuest(pertanyaanTigaBelasCode);
        dataQuest8.setAnswer(pertanyaanTigaBelasAnswer);
        dataQuest8.setDesc("");
        dataQuest8.setSubQuest(subQuest);

        dataQuest.add(dataQuest1);
        dataQuest.add(dataQuest2);
        dataQuest.add(dataQuest3);
        dataQuest.add(dataQuest4);
        dataQuest.add(dataQuest5);
        dataQuest.add(dataQuest6);
        dataQuest.add(dataQuest7);
        dataQuest.add(dataQuest8);

        EditSurvey editSurvey = new EditSurvey();
        editSurvey.setCodeSurvey(SessionManager.getKodeSurveyRumahKonsumen(context));
        editSurvey.setLatitude(latitude1Value);
        editSurvey.setLongitude(longitude1Value);
        editSurvey.setAlamat(namaAlamatRumahValue);
        editSurvey.setDataQuest(dataQuest);
        editSurvey.setNarasumber1(narasumber1List);
        editSurvey.setNarasumber2(narasumber2List);

        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();
        String token = SessionManager.getToken(context);
        apiServices.editSurvey("bearer "+token, editSurvey).enqueue(new Callback<EditSurvey>() {
            @Override
            public void onResponse(Call<EditSurvey> call, Response<EditSurvey> response) {
                loading.dismiss();
                if (response.code() == 200) {
//                    String kodeSurvey = response.body().getData().getDataID();
//                    SessionManager.saveKodeSurvey(context, kodeSurvey);
//                    Toast.makeText(context, "Data berhasil di save", Toast.LENGTH_LONG).show();
                    closeSurvey();
                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<EditSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Gagal Load Data sebelumnya, Mohon Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void closeSurvey(){
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();
        String token = SessionManager.getToken(context);
        String kodeKonsumen = SessionManager.getKodeKonsumen(context);
        apiServices.closeSurvey(kodeKonsumen, "bearer "+token).enqueue(new Callback<CloseSurvey>() {
            @Override
            public void onResponse(Call<CloseSurvey> call, Response<CloseSurvey> response) {
                loading.dismiss();
                if (response.code() == 200) {
//                    String kodeSurvey = response.body().getData().getDataID();
//                    SessionManager.saveKodeSurvey(context, kodeSurvey);
                    Toast.makeText(context, "Data berhasil di save", Toast.LENGTH_LONG).show();
                    backKeHome();
                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CloseSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Gagal Load Data sebelumnya, Mohon Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerData() {
        ArrayAdapter<String> adapterPertanyaanEnam = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAM);
        adapterPertanyaanEnam.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanEnam.setAdapter(adapterPertanyaanEnam);

        ArrayAdapter<String> adapterPertanyaanTujuh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TUJUH);
        adapterPertanyaanTujuh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTujuh.setAdapter(adapterPertanyaanTujuh);

        ArrayAdapter<String> adapterPertanyaanDelapan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DELAPAN);
        adapterPertanyaanDelapan.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDelapan.setAdapter(adapterPertanyaanDelapan);

        ArrayAdapter<String> adapterPertanyaanSepuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEPULUH);
        adapterPertanyaanSepuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanSepuluh.setAdapter(adapterPertanyaanSepuluh);

        ArrayAdapter<String> adapterPertanyaanSebelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEBELAS);
        adapterPertanyaanSebelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanSebelas.setAdapter(adapterPertanyaanSebelas);

        ArrayAdapter<String> adapterPertanyaanDuaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUABELAS);
        adapterPertanyaanDuaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaBelas.setAdapter(adapterPertanyaanDuaBelas);

        ArrayAdapter<String> adapterPertanyaanTigaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGABELAS);
        adapterPertanyaanTigaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTigaBelas.setAdapter(adapterPertanyaanTigaBelas);

        ArrayAdapter<String> adapterPertanyaanEmpatBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_EMPATBELAS);
        adapterPertanyaanEmpatBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanEmpatBelas.setAdapter(adapterPertanyaanEmpatBelas);

        ArrayAdapter<String> adapterPertanyaanLimaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMABELAS);
        adapterPertanyaanLimaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanLimaBelas.setAdapter(adapterPertanyaanLimaBelas);

        ArrayAdapter<String> adapterPertanyaanEnamBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
        adapterPertanyaanEnamBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanEnamBelas.setAdapter(adapterPertanyaanEnamBelas);

        ArrayAdapter<String> adapterPertanyaanTujuhBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
        adapterPertanyaanTujuhBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanTujuhBelas.setAdapter(adapterPertanyaanTujuhBelas);

        ArrayAdapter<String> adapterPertanyaanSembilanBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEMBILANBELAS);
        adapterPertanyaanSembilanBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanSembilanBelas.setAdapter(adapterPertanyaanSembilanBelas);

        ArrayAdapter<String> adapterPertanyaanDuaPuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUAPULUH);
        adapterPertanyaanDuaPuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaPuluh.setAdapter(adapterPertanyaanDuaPuluh);

        ArrayAdapter<String> adapterPertanyaanDuaSatu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_EMPATBELAS);
        adapterPertanyaanDuaSatu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaSatu.setAdapter(adapterPertanyaanDuaSatu);

        ArrayAdapter<String> adapterPertanyaanDuaDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMABELAS);
        adapterPertanyaanDuaDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaDua.setAdapter(adapterPertanyaanDuaDua);

        ArrayAdapter<String> adapterPertanyaanDuaTiga = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
        adapterPertanyaanDuaTiga.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaTiga.setAdapter(adapterPertanyaanDuaTiga);

        ArrayAdapter<String> adapterPertanyaanDuaEmpat = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
        adapterPertanyaanDuaEmpat.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaEmpat.setAdapter(adapterPertanyaanDuaEmpat);

        ArrayAdapter<String> adapterPertanyaanDuaEnam = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEMBILANBELAS);
        adapterPertanyaanDuaEnam.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaEnam.setAdapter(adapterPertanyaanDuaEnam);

        ArrayAdapter<String> adapterPertanyaanDuaTujuh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUAPULUH);
        adapterPertanyaanDuaTujuh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pertanyaanDuaTujuh.setAdapter(adapterPertanyaanDuaTujuh);
    }

    private void getMyLocation(){
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        airLocation = new AirLocation(this, true, true, new AirLocation.Callbacks() {
            @Override
            public void onSuccess(Location location) {
                String latitude_value = String.valueOf(location.getLatitude());
                String longitude_value = String.valueOf(location.getLongitude());

                latitude.setText(latitude_value);
                longitude.setText(longitude_value);

                LatLng myLocation=new LatLng(location.getLatitude(), location.getLongitude());
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

    private void invokeCameraEsafirm1(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA1);
        getMyLocation();
    }
    private void invokeCameraEsafirm2(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA2);
    }
    private void invokeCameraEsafirm3(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA3);
    }
    private void invokeCameraEsafirm4(){
        ImagePicker.cameraOnly().start((Activity) context, REQUEST_CODE_CAMERA4);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CAMERA1){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(image.getPath(), options);
                Glide.with(context).load(bitmap).into(imageRumah1);
                BitmapHelper.getInstance().setBitmap(bitmap);
                buttonCamera1.setVisibility(View.GONE);
                buttonCamera2.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA2){
            final Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                final Bitmap bitmap2 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap2).into(imageRumah2);
                BitmapHelper.getInstance().setBitmap2(bitmap2);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA3){
            final Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                final Bitmap bitmap3 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap3).into(imageJalanRumah1);
                BitmapHelper.getInstance().setBitmap3(bitmap3);
                buttonCamera3.setVisibility(View.GONE);
                buttonCamera4.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUEST_CODE_CAMERA4){
            final Image image = ImagePicker.getFirstImageOrNull(data);
            if(image!=null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                final Bitmap bitmap4 = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap4).into(imageJalanRumah2);
                BitmapHelper.getInstance().setBitmap4(bitmap4);
            }

        }else if (requestCode == PLACE_PICKER){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Nama Tempat : %s \n"+
                        "Alamat : %s \n", place.getName(), place.getAddress());
                namaAlamatRumah.setText(toastMsg);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            namaAlamatRumah.setText(namaAlamatRumah.getText() + addresses.get(0).getAddressLine(0) + ", " +
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
        mMap = googleMap;
    }

    private void imageClick(){
        if(imageRumah1 != null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }else {

        }
    }
    private void imageClick2(){
        if (imageRumah2 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap2());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick3(){
        if (imageJalanRumah1 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap3());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }
    private void imageClick4(){
        if (imageJalanRumah2 !=null){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
            PhotoView photoView = mView.findViewById(R.id.imageView);
            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap4());
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
    }

    private void uploadImage(){
        //tangkap data dari image view
        imageRumah1.setDrawingCacheEnabled(true);
        imageRumah1.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageRumah1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
//                final String downloadUrl = taskSnapshot.getTask().getResult().toString();
////                dbase.child("CMO1").child("identitas_konsumen").child("image").setValue(downloadUrl)
////                        .addOnSuccessListener(new OnSuccessListener<Void>() {
////                            @Override
////                            public void onSuccess(Void aVoid) {
////                                if (taskSnapshot.getTask().isSuccessful()){
////                                    Toast.makeText(context, "Gambar Tersimpan di Database", Toast.LENGTH_SHORT).show();
////                                }
////                                else{
////                                    String message = taskSnapshot.getTask().getException().toString();
////                                    Toast.makeText(context, "Gagal menyimpan : "+message, Toast.LENGTH_SHORT).show();
////                                }
////                            }
////                        });
////                imageText.setText(downloadUrl);
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
    private void uploadImage2(){
        //tangkap data dari image view

        imageRumah2.setDrawingCacheEnabled(true);
        imageRumah2.buildDrawingCache();
        Bitmap bitmap2 = ((BitmapDrawable) imageRumah2.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
    private void uploadImage3(){
        //tangkap data dari image view
        imageJalanRumah1.setDrawingCacheEnabled(true);
        imageJalanRumah1.buildDrawingCache();
        Bitmap bitmap3 = ((BitmapDrawable) imageJalanRumah1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
    private void uploadImage4(){
        //tangkap data dari image view
        imageJalanRumah2.setDrawingCacheEnabled(true);
        imageJalanRumah2.buildDrawingCache();
        Bitmap bitmap4 = ((BitmapDrawable) imageJalanRumah2.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //kompres
        bitmap4.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder option = new AlertDialog.Builder(context);
        option.setMessage("Apakah Anda yakin ingin membatalkan input survey ini dan kembali ke Menu ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backKeHome();
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setCancelable(true);
        AlertDialog showOption = option.create();
        showOption.show();
    }

    private void backKeHome(){
        Intent intent = new Intent(context, HomeCMOActivity.class);
        startActivity(intent);
        finish();
    }
}
