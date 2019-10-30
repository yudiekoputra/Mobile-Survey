package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//import com.bcafinance.itdp.mobilesurvey.utility.input_survey;
import com.bcafinance.itdp.mobilesurvey.utility.inputSurvey;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;

public class InputSurveyActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context = this;
    private EditText namaKonsumen, noKTP, tempatLahir, tanggalLahir, noTelp, alamatRumah, kelurahanRumah, kecamatanRumah, kodePosRumah, namaPasangan, jmlTanggungan, namaIbuKandung,
            namaTempatUsaha, alamatUsaha, kelurahanUsaha, kecamatanUsaha, kodePosUsaha, merkMobil, warnaMobil, dealerShowroom, mobileId, informasiTambahan;
    private Button buttonSubmit;
    private CardView buttonRumah, buttonUsaha, buttonKonsumen;
    TextView tanggalSurvey, jamSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_survey);

        namaKonsumen = findViewById(R.id.namaKonsumen);
        noKTP = findViewById(R.id.noKTP);
        tempatLahir = findViewById(R.id.tempatLahir);
        tanggalLahir = findViewById(R.id.tanggalLahir);
        noTelp = findViewById(R.id.noTelp);
        alamatRumah = findViewById(R.id.alamatRumah);
        kelurahanRumah = findViewById(R.id.kelurahanRumah);
        kecamatanRumah = findViewById(R.id.kecamatanRumah);
        kodePosRumah = findViewById(R.id.kodePosRumah);
        namaPasangan = findViewById(R.id.namaPasangan);
        jmlTanggungan = findViewById(R.id.jmlTanggungan);
        namaIbuKandung = findViewById(R.id.namaIbuKandung);
        namaTempatUsaha = findViewById(R.id.namaTempatUsaha);
        alamatUsaha = findViewById(R.id.alamatUsaha);
        kelurahanUsaha = findViewById(R.id.kelurahanUsaha);
        kecamatanUsaha = findViewById(R.id.kecamatanUsaha);
        kodePosUsaha = findViewById(R.id.kodePosUsaha);
        merkMobil = findViewById(R.id.merkMobil);
        warnaMobil = findViewById(R.id.warnaMobil);
        dealerShowroom = findViewById(R.id.dealerShowroom);
        mobileId = findViewById(R.id.mobileId);
        informasiTambahan = findViewById(R.id.informasiTambahan);

        String tanggalSurveySekarang = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        String jamSurveySekarang = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        tanggalSurvey = findViewById(R.id.tanggalSurvey);
        tanggalSurvey.setText(tanggalSurveySekarang);

        jamSurvey = findViewById(R.id.jamSurvey);
        jamSurvey.setText(jamSurveySekarang);

        buttonRumah = findViewById(R.id.buttonRumah);
        buttonRumah.setOnClickListener(this);

        buttonUsaha = findViewById(R.id.buttonUsahaKantor);
        buttonUsaha.setOnClickListener(this);

        buttonKonsumen = findViewById(R.id.buttonKonsumen);
        buttonKonsumen.setOnClickListener(this);

        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);

        setTanggalLahir();

    }

    private void saveContent(){
//        String username = auth.getCurrentUser().getUid();
            final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
            loading.show();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference getReference;

            String statusValue = "In Progress";
            String namaKonsumenValue = namaKonsumen.getText().toString();
            String noKTPValue = noKTP.getText().toString();
            String tempatLahirValue = tempatLahir.getText().toString();
            String tanggalLahirValue = tanggalLahir.getText().toString();
            String noTelpValue = noTelp.getText().toString();
            String alamatRumahValue = alamatRumah.getText().toString();
            String kelurahanRumahValue = kelurahanRumah.getText().toString();
            String kecamatanRumahValue = kecamatanRumah.getText().toString();
            String kodePosRumahValue = kodePosRumah.getText().toString();
            String namaPasanganValue = namaPasangan.getText().toString();
            String jmlTanggunganValue = jmlTanggungan.getText().toString();
            String namaIbuKandungValue = namaIbuKandung.getText().toString();
            String namaTempatUsahaValue = namaTempatUsaha.getText().toString();
            String alamatUsahaValue = alamatUsaha.getText().toString();
            String kelurahanUsahaValue = kelurahanUsaha.getText().toString();
            String kecamatanUsahaValue = kecamatanUsaha.getText().toString();
            String kodePosUsahaValue = kodePosUsaha.getText().toString();
            String merkMobilValue = merkMobil.getText().toString();
            String warnaMobilValue = warnaMobil.getText().toString();
            String dealerShowroomValue = dealerShowroom.getText().toString();
            String mobileIdValue = mobileId.getText().toString();
            String tanggalSurveyValue = tanggalSurvey.getText().toString();
            String jamSurveyValue = jamSurvey.getText().toString();
            String informasiTambahanValue = informasiTambahan.getText().toString();
            String pertanyaanSatuValue = SessionManager.getPertanyaanSatu(context);
            String pertanyaanDuaValue = SessionManager.getPertanyaanDua(context);
            String platNomorValue = SessionManager.getPlatNomor(context);
            String merkTipeValue = SessionManager.getMerkTipe(context);
            String warnaValue = SessionManager.getWarna(context);
            String tahunKendaraanValue = SessionManager.getTahunKendaraan(context);
            String jenisKreditValue = SessionManager.getJenisKredit(context);
            String totalAngsuranValue = SessionManager.getTotalAngsuran(context);
            String totalPenghasilanValue = SessionManager.getTotalPenghasilan(context);
            String pertanyaanLimaValue = SessionManager.getPertanyaanLima(context);
            String pertanyaanEnamValue = SessionManager.getPertanyaanEnam(context);
            String pertanyaanTujuhValue = SessionManager.getPertanyaanTujuh(context);
            String pertanyaanDelapanValue = SessionManager.getPertanyaanDelapan(context);
            String pertanyaanSembilanValue = SessionManager.getPertanyaanSembilan(context);
            String pertanyaanSepuluhValue = SessionManager.getPertanyaanSepuluh(context);
            String pertanyaanSebelasValue = SessionManager.getPertanyaanSebelas(context);
            String pertanyaanDuaBelasValue = SessionManager.getPertanyaanDuaBelas(context);
            String pertanyaanTigaBelasValue = SessionManager.getPertanyaanTigaBelas(context);
            String pertanyaanEmpatBelasValue = SessionManager.getPertanyaanEmpatBelas(context);
            String pertanyaanLimaBelasValue = SessionManager.getPertanyaanLimaBelas(context);
            String pertanyaanEnamBelasValue = SessionManager.getPertanyaanEnamBelas(context);
            String pertanyaanTujuhBelasValue =SessionManager.getPertanyaanTujuhBelas(context);
            String pertanyaanDelapanBelasValue =SessionManager.getPertanyaanDelapanBelas(context);
            String pertanyaanSembilanBelasValue =SessionManager.getPertanyaanSembilanBelas(context);
            String pertanyaanDuaPuluhValue = SessionManager.getPertanyaanDuaPuluh(context);
            String pertanyaanDuaSatuValue = SessionManager.getPertanyaanDuaSatu(context);
            String pertanyaanDuaDuaValue = SessionManager.getPertanyaanDua(context);
            String pertanyaanDuaTigaValue = SessionManager.getPertanyaanDuaTiga(context);
            String pertanyaanDuaEmpatValue = SessionManager.getPertanyaanDuaEmpat(context);
            String pertanyaanDuaLimaValue = SessionManager.getPertanyaanDuaLima(context);
            String pertanyaanDuaEnamValue = SessionManager.getPertanyaanDuaEnam(context);
            String pertanyaanDuaTujuhValue = SessionManager.getPertanyaanDuaTujuh(context);
            String jawabanEnamBelasValue = SessionManager.getJawabanEnamBelas(context);
            String jawabanTujuhBelasValue = SessionManager.getJawabanTujuhBelas(context);
            String jawabanDelapanBelasTipeValue = SessionManager.getJawabanDelapanBelasTipe(context);
            String jawabanDelapanBelasWarnaValue = SessionManager.getJawabanDelapanBelasWarna(context);
            String jawabanDuaPuluhValue = SessionManager.getJawabanDuaPuluh(context);
            String jawabanDuaTigaValue = SessionManager.getJawabanDuaTiga(context);
            String jawabanDuaEmpatValue = SessionManager.getJawabanDuaEmpat(context);
            String jawabanDuaLimaTipeValue = SessionManager.getJawabanDuaLimaTipe(context);
            String jawabanDuaLimaWarnaValue = SessionManager.getJawabanDuaLimaWarna(context);
            String jawabanDuaTujuhValue = SessionManager.getJawabanDuaTujuh(context);
            String namaNarasumber1Value = SessionManager.getNamaNarasumber1(context);
            String namaNarasumber2Value = SessionManager.getNamaNarasumber2(context);
            String namaAlamatRumahValue = getIntent().getStringExtra(Constanta.NAMAALAMATRUMAH);
            String latitude1Value = getIntent().getStringExtra(Constanta.LATITUDE1);
            String longitude1Value = getIntent().getStringExtra(Constanta.LONGITUDE1);
            String pertanyaanDuaDelapanValue = SessionManager.getPertanyaanDuaDelapan(context);
            String pertanyaanDuaSembilanValue = SessionManager.getPertanyaanDuaSembilan(context);
            String pertanyaanTigaPuluhValue = SessionManager.getPertanyaanTigaPuluh(context);
            String pertanyaanTigaSatuValue = SessionManager.getPertanyaanTigaSatu(context);
            String jawabanDuaDelapanValue = SessionManager.getJawabanDuaDelapan(context);
            String jawabanDuaSembilanValue = SessionManager.getJawabanDuaSembilan(context);
            String pertanyaanTigaDuaValue = SessionManager.getPertanyaanTigaDua(context);
            String pertanyaanTigaTigaValue = SessionManager.getPertanyaanTigaTiga(context);
            String pertanyaanTigaEmpatValue = SessionManager.getPertanyaanTigaEmpat(context);
            String pertanyaanTigaLimaValue = SessionManager.getPertanyaanTigaLima(context);
            String jawabanTigaDuaValue = SessionManager.getJawabanTigaDua(context);
            String jawabanTigaTigaValue = SessionManager.getJawabanTigaTiga(context);
            String jawabanTigaEmpatValue = SessionManager.getJawabanTigaEmpat(context);
            String jawabanTigaLimaValue = SessionManager.getJawabanTigaLima(context);
            String namaNarasumber3Value = SessionManager.getNamaNarasumber3(context);
            String namaNarasumber4Value = SessionManager.getNamaNarasumber4(context);
            String namaAlamatUsahaValue = SessionManager.getNamaAlamatKantor(context);
            String latitude2Value = SessionManager.getLatitude2(context);
            String longitude2Value = SessionManager.getLongitude2(context);

        getReference = database.getReference();

            if (isEmpty(namaKonsumenValue)&& isEmpty(noKTPValue)){
                Toast.makeText(context, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                loading.dismiss();

            }else {
                getReference.child("inputSurvey").push()
                        .setValue(new inputSurvey(statusValue, namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, noTelpValue, alamatRumahValue, kelurahanRumahValue, kecamatanRumahValue, kodePosRumahValue, namaPasanganValue, jmlTanggunganValue, namaIbuKandungValue,
                                namaTempatUsahaValue, alamatUsahaValue, kelurahanUsahaValue, kecamatanUsahaValue, kodePosUsahaValue, merkMobilValue, warnaMobilValue, dealerShowroomValue, mobileIdValue, tanggalSurveyValue, jamSurveyValue, informasiTambahanValue,
                                pertanyaanSatuValue, pertanyaanDuaValue, platNomorValue, merkTipeValue, warnaValue, tahunKendaraanValue, jenisKreditValue, totalAngsuranValue, totalPenghasilanValue, pertanyaanLimaValue,
                                pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue,
                                pertanyaanDuaBelasValue, pertanyaanTigaBelasValue, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue, pertanyaanDelapanBelasValue, pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue,
                                pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue, pertanyaanDuaEnamValue, pertanyaanDuaTujuhValue,
                                jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue, jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value,
                                namaAlamatRumahValue, latitude1Value, longitude1Value, pertanyaanDuaDelapanValue, pertanyaanDuaSembilanValue, pertanyaanTigaPuluhValue, pertanyaanTigaSatuValue, jawabanDuaDelapanValue, jawabanDuaSembilanValue, pertanyaanTigaDuaValue, pertanyaanTigaTigaValue, pertanyaanTigaEmpatValue, pertanyaanTigaLimaValue,
                                jawabanTigaDuaValue, jawabanTigaTigaValue, jawabanTigaEmpatValue, jawabanTigaLimaValue, namaNarasumber3Value, namaNarasumber4Value, namaAlamatUsahaValue, latitude2Value, longitude2Value)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Data Tersimpan", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, HomeCMOActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
    }

    private void setTanggalLahir(){
        tanggalLahir.setFocusable(false);
        tanggalLahir.setClickable(true);

        android.icu.util.Calendar today = android.icu.util.Calendar.getInstance();
        final int yearNow = today.get(android.icu.util.Calendar.YEAR);
        final int monthNow = today.get(android.icu.util.Calendar.MONTH);
        final int dayNow = today.get(android.icu.util.Calendar.DATE);

        tanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.CustomDatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        android.icu.util.Calendar selected = Calendar.getInstance();
                        selected.set(year,month,dayOfMonth);

                        //konversi ke string
                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                        String tanggal = formatDate.format(selected.getTime());

                        tanggalLahir.setText(tanggal);
                    }
                }, yearNow, monthNow,dayNow );
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int a = v.getId();
        TextView tvNotif = findViewById(R.id.tvSurveyRumahKantor);
        TextView tvKons = findViewById(R.id.tvSurveyKonsumen);
        if (a == R.id.buttonKonsumen){
            tvKons.setVisibility(View.GONE);
            Intent i = new Intent(context, SurveyKonsumenActivity.class);
            startActivity(i);
        }else if (a == R.id.buttonRumah){
            buttonKonsumen.setContextClickable(false);
            tvNotif.setVisibility(View.GONE);
            Intent i = new Intent(context, SurveyRumahActivity.class);
            startActivity(i);
        }else if (a == R.id.buttonUsahaKantor){
            buttonRumah.setContextClickable(false);
            tvNotif.setVisibility(View.GONE);
            Intent i = new Intent(context, SurveyUsahaActivity.class);
            startActivity(i);
        }else if (a == R.id.buttonSubmit){
            saveContent();
        }
    }
}
