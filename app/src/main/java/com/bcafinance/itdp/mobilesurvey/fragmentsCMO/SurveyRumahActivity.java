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
import com.bcafinance.itdp.mobilesurvey.helper.BitmapHelper;
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
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import mumayank.com.airlocationlibrary.AirLocation;

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
    private StorageReference reference;
    private ProgressBar progressBar;
    private DatabaseReference dbase;
    private TextView imageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_rumah);
        reference = FirebaseStorage.getInstance().getReference();
        dbase = FirebaseDatabase.getInstance().getReference();
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
                    case R.id.radio1:
                        jawabanDelapanBelasTipe.setVisibility(View.VISIBLE);
                        jawabanDelapanBelasWarna.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radio2:
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
                    case R.id.radio3:
                        jawabanDuaLimaTipe.setVisibility(View.VISIBLE);
                        jawabanDuaLimaWarna.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radio4:
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
            Intent intent = new Intent(context, InputSurveyActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void saveRumah(){
        String pertanyaanEnamValue = pertanyaanEnam.getSelectedItem().toString();
        String pertanyaanTujuhValue = pertanyaanTujuh.getSelectedItem().toString();
        String pertanyaanDelapanValue = pertanyaanDelapan.getSelectedItem().toString();
        String pertanyaanSembilanValue = ((RadioButton)findViewById(pertanyaanSembilan.getCheckedRadioButtonId())).getText().toString();
        String pertanyaanSepuluhValue = pertanyaanSepuluh.getSelectedItem().toString();
        String pertanyaanSebelasValue = pertanyaanSebelas.getSelectedItem().toString();
        String pertanyaanDuaBelasValue = pertanyaanDuaBelas.getSelectedItem().toString();
        String pertanyaanTigaBelasValue = pertanyaanTigaBelas.getSelectedItem().toString();

        SessionManager.saveSurveyRumahKonsumen(context, pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue, pertanyaanDuaBelasValue, pertanyaanTigaBelasValue);

        String pertanyaanEmpatBelasValue = pertanyaanEmpatBelas.getSelectedItem().toString();
        String pertanyaanLimaBelasValue = pertanyaanLimaBelas.getSelectedItem().toString();
        String pertanyaanEnamBelasValue = pertanyaanEnamBelas.getSelectedItem().toString();
        String pertanyaanTujuhBelasValue = pertanyaanTujuhBelas.getSelectedItem().toString();
        String pertanyaanDelapanBelasValue = ((RadioButton)findViewById(pertanyaanDelapanBelas.getCheckedRadioButtonId())).getText().toString();
        String pertanyaanSembilanBelasValue = pertanyaanSembilanBelas.getSelectedItem().toString();
        String pertanyaanDuaPuluhValue = pertanyaanDuaPuluh.getSelectedItem().toString();
        String pertanyaanDuaSatuValue = pertanyaanDuaSatu.getSelectedItem().toString();
        String pertanyaanDuaDuaValue = pertanyaanDuaDua.getSelectedItem().toString();
        String pertanyaanDuaTigaValue = pertanyaanDuaTiga.getSelectedItem().toString();
        String pertanyaanDuaEmpatValue = pertanyaanDuaEmpat.getSelectedItem().toString();
        String pertanyaanDuaLimaValue = ((RadioButton)findViewById(pertanyaanDuaLima.getCheckedRadioButtonId())).getText().toString();
        String pertanyaanDuaEnamValue = pertanyaanDuaEnam.getSelectedItem().toString();
        String pertanyaanDuaTujuhValue = pertanyaanDuaTujuh.getSelectedItem().toString();
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
        String namaNarasumber1Value = namaNarasumber1.getText().toString();
        String namaNarasumber2Value = namaNarasumber2.getText().toString();

        SessionManager.saveVerifikasiLingkunganRumah(context, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue, pertanyaanDelapanBelasValue,
                pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue, pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue,
                pertanyaanDuaEnamValue,pertanyaanDuaTujuhValue, jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue,
                jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value);


        String namaAlamatRumahValue = namaAlamatRumah.getText().toString();
        String latitude1Value = latitude.getText().toString();
        String longitude1Value = longitude.getText().toString();
        SessionManager.saveRumahKonsumen(context, namaAlamatRumahValue, latitude1Value, longitude1Value);

        Intent intent = new Intent(context, InputSurveyActivity.class);
       uploadImage();
       uploadImage2();
       uploadImage3();
       uploadImage4();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
       finish();
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
        UploadTask uploadTask = reference.child(pathImage).putBytes(bytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Upload Berhasil", Toast.LENGTH_SHORT).show();
                final String downloadUrl = taskSnapshot.getTask().getResult().toString();
//                dbase.child("CMO1").child("identitas_konsumen").child("image").setValue(downloadUrl)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                if (taskSnapshot.getTask().isSuccessful()){
//                                    Toast.makeText(context, "Gambar Tersimpan di Database", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    String message = taskSnapshot.getTask().getException().toString();
//                                    Toast.makeText(context, "Gagal menyimpan : "+message, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                imageText.setText(downloadUrl);
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
}
