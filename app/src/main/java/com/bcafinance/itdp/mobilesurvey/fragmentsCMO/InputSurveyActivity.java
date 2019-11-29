package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.bcafinance.itdp.mobilesurvey.customs.CustomExpandCollapseBar;
import com.bcafinance.itdp.mobilesurvey.helper.APIUtilities;
import com.bcafinance.itdp.mobilesurvey.helper.AddKonsumen;
import com.bcafinance.itdp.mobilesurvey.helper.Data;
import com.bcafinance.itdp.mobilesurvey.helper.Datum;
import com.bcafinance.itdp.mobilesurvey.helper.RequestAPIServices;
import com.bcafinance.itdp.mobilesurvey.helper.RetrofitClient;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//import com.bcafinance.itdp.mobilesurvey.utility.input_survey;
import com.bcafinance.itdp.mobilesurvey.utility.inputSurvey;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class InputSurveyActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context = this;
    private EditText namaKonsumen, noKTP, tempatLahir, tanggalLahir, noTelp, noHp, alamatRumah, RtRumah, RwRumah, kelurahanRumah, kecamatanRumah, kotaRumah, kodePosRumah, namaPasangan, jmlTanggungan, namaIbuKandung,
            namaTempatUsaha, alamatUsaha, RtUsaha, RwUsaha, kelurahanUsaha, kecamatanUsaha, kotaUsaha, kodePosUsaha, merkMobil, tipeMobil, tahunMobil, warnaMobil, dealerShowroom, mobileId, informasiTambahan;
    private Button buttonSubmit, buttonBack;
    TextView tanggalSurvey, jamSurvey, barExpandRumah, barExpandUsaha, barExpandMobil;
    RequestAPIServices apiServices;
    private ExpandableLinearLayout layoutRumah, layoutUsaha, layoutMobil;
    private CustomExpandCollapseBar expandCollapseBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_survey);
        apiServices = APIUtilities.getAPIServices();

        mobileId = findViewById(R.id.mobileId);
        namaKonsumen = findViewById(R.id.namaKonsumen);
        noKTP = findViewById(R.id.noKTP);
        tempatLahir = findViewById(R.id.tempatLahir);
        tanggalLahir = findViewById(R.id.tanggalLahir);
        noTelp = findViewById(R.id.noTelp);
        noHp = findViewById(R.id.noHp);
        alamatRumah = findViewById(R.id.alamatRumah);
        RtRumah = findViewById(R.id.RtRumah);
        RwRumah = findViewById(R.id.RwRumah);
        kelurahanRumah = findViewById(R.id.kelurahanRumah);
        kecamatanRumah = findViewById(R.id.kecamatanRumah);
        kotaRumah = findViewById(R.id.kotaRumah);
        kodePosRumah = findViewById(R.id.kodePosRumah);
        namaPasangan = findViewById(R.id.namaPasangan);
        jmlTanggungan = findViewById(R.id.jmlTanggungan);
        namaIbuKandung = findViewById(R.id.namaIbuKandung);
        namaTempatUsaha = findViewById(R.id.namaTempatUsaha);
        alamatUsaha = findViewById(R.id.alamatUsaha);
        RtUsaha = findViewById(R.id.RtUsaha);
        RwUsaha = findViewById(R.id.RwUsaha);
        kelurahanUsaha = findViewById(R.id.kelurahanUsaha);
        kecamatanUsaha = findViewById(R.id.kecamatanUsaha);
        kotaUsaha = findViewById(R.id.kotaUsaha);
        kodePosUsaha = findViewById(R.id.kodePosUsaha);
        merkMobil = findViewById(R.id.merkMobil);
        tipeMobil = findViewById(R.id.tipeMobil);
        tahunMobil = findViewById(R.id.tahunMobil);
        warnaMobil = findViewById(R.id.warnaMobil);
        dealerShowroom = findViewById(R.id.dealerShowroom);
        informasiTambahan = findViewById(R.id.informasiTambahan);
        informasiTambahan.setVisibility(View.GONE);

        barExpandRumah =findViewById(R.id.barExpandRumah);
        layoutRumah = findViewById(R.id.layoutRumah);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandRumah, layoutRumah,false);

        barExpandMobil =findViewById(R.id.barExpandMobil);
        layoutMobil = findViewById(R.id.layoutMobil);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandMobil, layoutMobil,false);

        barExpandUsaha =findViewById(R.id.barExpandUsaha);
        layoutUsaha = findViewById(R.id.layoutUsaha);
        expandCollapseBar = new CustomExpandCollapseBar(barExpandUsaha, layoutUsaha,false);

        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        setTanggalLahir();

    }

    private void checkKosong(){
        String mobileIdValue = mobileId.getText().toString();
        String namaKonsumenValue = namaKonsumen.getText().toString();
        String noKTPValue = noKTP.getText().toString();
        String noHpValue = noHp.getText().toString();
        String tempatLahirValue = tempatLahir.getText().toString();
        String tanggalLahirValue = tanggalLahir.getText().toString();
        String namaIbuKandungValue = namaIbuKandung.getText().toString();

        if(namaIbuKandungValue.isEmpty()) {
            Toast.makeText(context, "Nama Ibu Kandung Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            namaIbuKandung.setPressed(true);
            if(tanggalLahirValue.isEmpty()){
                Toast.makeText(context, "Tanggal Lahir Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                if(tempatLahirValue.isEmpty()){
                    Toast.makeText(context, "Tempat Lahir Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    tempatLahir.setPressed(true);
                    if(noHpValue.isEmpty()){
                        Toast.makeText(context, "No HP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        noHp.setPressed(true);
                        if(noKTPValue.isEmpty()){
                            Toast.makeText(context, "No KTP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                            noKTP.setPressed(true);
                            if(namaKonsumenValue.isEmpty()){
                                Toast.makeText(context, "Nama Konsumen Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                                namaKonsumen.setPressed(true);
                                if (mobileIdValue.isEmpty()) {
                                    Toast.makeText(context, "Mobile ID Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                                    mobileId.setPressed(true);
                                }else{ }
                            }else{ }
                        }else{ }
                    }else{ }
                }else{ }
            }else { }
        }else{ }
        saveContent(mobileIdValue, namaKonsumenValue, noKTPValue, noHpValue, tempatLahirValue, tanggalLahirValue);
    }

    private void saveContent(String mobileId, String namaKonsumen, String noKTP, String noHp, String tempatLahir, String tanggalLahir){
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();
        String user = SessionManager.getUser(context);
        String token = SessionManager.getToken(context);

        Data data = new Data();

        data.setNamaKonsumen(namaKonsumen);
        data.setNoKTP(noKTP);
        data.setTanggallahir(tanggalLahir);
        data.setNamaIbuKandung(namaIbuKandung.getText().toString());
        data.setTempatlahir(tempatLahir);
        data.setNamaPasangan(namaPasangan.getText().toString());
        data.setJumlahTanggungan(jmlTanggungan.getText().toString());
        data.setNoHP(noHp);
        data.setNoTelp(noTelp.getText().toString());
        data.setAlamatRumah(alamatRumah.getText().toString());
        data.setRtRumah(RtRumah.getText().toString());
        data.setRwRumah(RwRumah.getText().toString());
        data.setKelurahanRumah(kelurahanRumah.getText().toString());
        data.setKecamatanRumah(kecamatanRumah.getText().toString());
        data.setKabupatenRumah(kotaRumah.getText().toString());
        data.setKodeposRumah(kodePosRumah.getText().toString());
        data.setNamaUsaha(namaTempatUsaha.getText().toString());
        data.setAlamatUsaha(alamatUsaha.getText().toString());
        data.setRtUsaha(RtUsaha.getText().toString());
        data.setRwUsaha(RwUsaha.getText().toString());
        data.setKelurahanUsaha(kelurahanUsaha.getText().toString());
        data.setKecamatanUsaha(kecamatanUsaha.getText().toString());
        data.setKabupatenUsaha(kotaUsaha.getText().toString());
        data.setKodeposUsaha(kodePosUsaha.getText().toString());
        data.setMerkMobil(merkMobil.getText().toString());
        data.setTypeMobil(tipeMobil.getText().toString());
        data.setTahunMobil(tahunMobil.getText().toString());
        data.setWarnaMobil(warnaMobil.getText().toString());
        data.setDealerMobil(dealerShowroom.getText().toString());

        AddKonsumen addKonsumen = new AddKonsumen();
        addKonsumen.setUserid(user);
        addKonsumen.setMobileID(mobileId);
        addKonsumen.setData(data);

        apiServices.addKonsumen("bearer "+token, addKonsumen).enqueue(new Callback<AddKonsumen>() {
            @Override
            public void onResponse(Call<AddKonsumen> call, Response<AddKonsumen> response) {
                loading.dismiss();
                    if (response.code()==200){
                        String kodeKonsumen = response.body().getData().getDataID();
                        SessionManager.saveKodeKonsumen(context, kodeKonsumen);

                        Toast.makeText(context, "Berhasil Menyimpan Data Konsumen ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, SurveyKonsumenActivity.class);
                        startActivity(i);
                    }else {
                        loading.dismiss();
                    }
                }

            @Override
            public void onFailure(Call<AddKonsumen> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Error, Mohon Cek Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
            }
        });
//            String pertanyaanSatuValue = SessionManager.getPertanyaanSatu(context);
//            String pertanyaanDuaValue = SessionManager.getPertanyaanDua(context);
//            String platNomorValue = SessionManager.getPlatNomor(context);
//            String merkTipeValue = SessionManager.getMerkTipe(context);
//            String warnaValue = SessionManager.getWarna(context);
//            String tahunKendaraanValue = SessionManager.getTahunKendaraan(context);
//            String jenisKreditValue = SessionManager.getJenisKredit(context);
//            String totalAngsuranValue = SessionManager.getTotalAngsuran(context);
//            String totalPenghasilanValue = SessionManager.getTotalPenghasilan(context);
//            String pertanyaanLimaValue = SessionManager.getPertanyaanLima(context);
//            String pertanyaanEnamValue = SessionManager.getPertanyaanEnam(context);
//            String pertanyaanTujuhValue = SessionManager.getPertanyaanTujuh(context);
//            String pertanyaanDelapanValue = SessionManager.getPertanyaanDelapan(context);
//            String pertanyaanSembilanValue = SessionManager.getPertanyaanSembilan(context);
//            String pertanyaanSepuluhValue = SessionManager.getPertanyaanSepuluh(context);
//            String pertanyaanSebelasValue = SessionManager.getPertanyaanSebelas(context);
//            String pertanyaanDuaBelasValue = SessionManager.getPertanyaanDuaBelas(context);
//            String pertanyaanTigaBelasValue = SessionManager.getPertanyaanTigaBelas(context);
//            String pertanyaanEmpatBelasValue = SessionManager.getPertanyaanEmpatBelas(context);
//            String pertanyaanLimaBelasValue = SessionManager.getPertanyaanLimaBelas(context);
//            String pertanyaanEnamBelasValue = SessionManager.getPertanyaanEnamBelas(context);
//            String pertanyaanTujuhBelasValue =SessionManager.getPertanyaanTujuhBelas(context);
//            String pertanyaanDelapanBelasValue =SessionManager.getPertanyaanDelapanBelas(context);
//            String pertanyaanSembilanBelasValue =SessionManager.getPertanyaanSembilanBelas(context);
//            String pertanyaanDuaPuluhValue = SessionManager.getPertanyaanDuaPuluh(context);
//            String pertanyaanDuaSatuValue = SessionManager.getPertanyaanDuaSatu(context);
//            String pertanyaanDuaDuaValue = SessionManager.getPertanyaanDua(context);
//            String pertanyaanDuaTigaValue = SessionManager.getPertanyaanDuaTiga(context);
//            String pertanyaanDuaEmpatValue = SessionManager.getPertanyaanDuaEmpat(context);
//            String pertanyaanDuaLimaValue = SessionManager.getPertanyaanDuaLima(context);
//            String pertanyaanDuaEnamValue = SessionManager.getPertanyaanDuaEnam(context);
//            String pertanyaanDuaTujuhValue = SessionManager.getPertanyaanDuaTujuh(context);
//            String jawabanEnamBelasValue = SessionManager.getJawabanEnamBelas(context);
//            String jawabanTujuhBelasValue = SessionManager.getJawabanTujuhBelas(context);
//            String jawabanDelapanBelasTipeValue = SessionManager.getJawabanDelapanBelasTipe(context);
//            String jawabanDelapanBelasWarnaValue = SessionManager.getJawabanDelapanBelasWarna(context);
//            String jawabanDuaPuluhValue = SessionManager.getJawabanDuaPuluh(context);
//            String jawabanDuaTigaValue = SessionManager.getJawabanDuaTiga(context);
//            String jawabanDuaEmpatValue = SessionManager.getJawabanDuaEmpat(context);
//            String jawabanDuaLimaTipeValue = SessionManager.getJawabanDuaLimaTipe(context);
//            String jawabanDuaLimaWarnaValue = SessionManager.getJawabanDuaLimaWarna(context);
//            String jawabanDuaTujuhValue = SessionManager.getJawabanDuaTujuh(context);
//            String namaNarasumber1Value = SessionManager.getNamaNarasumber1(context);
//            String namaNarasumber2Value = SessionManager.getNamaNarasumber2(context);
//            String namaAlamatRumahValue = getIntent().getStringExtra(Constanta.NAMAALAMATRUMAH);
//            String latitude1Value = getIntent().getStringExtra(Constanta.LATITUDE1);
//            String longitude1Value = getIntent().getStringExtra(Constanta.LONGITUDE1);
//            String pertanyaanDuaDelapanValue = SessionManager.getPertanyaanDuaDelapan(context);
//            String pertanyaanDuaSembilanValue = SessionManager.getPertanyaanDuaSembilan(context);
//            String pertanyaanTigaPuluhValue = SessionManager.getPertanyaanTigaPuluh(context);
//            String pertanyaanTigaSatuValue = SessionManager.getPertanyaanTigaSatu(context);
//            String jawabanDuaDelapanValue = SessionManager.getJawabanDuaDelapan(context);
//            String jawabanDuaSembilanValue = SessionManager.getJawabanDuaSembilan(context);
//            String pertanyaanTigaDuaValue = SessionManager.getPertanyaanTigaDua(context);
//            String pertanyaanTigaTigaValue = SessionManager.getPertanyaanTigaTiga(context);
//            String pertanyaanTigaEmpatValue = SessionManager.getPertanyaanTigaEmpat(context);
//            String pertanyaanTigaLimaValue = SessionManager.getPertanyaanTigaLima(context);
//            String jawabanTigaDuaValue = SessionManager.getJawabanTigaDua(context);
//            String jawabanTigaTigaValue = SessionManager.getJawabanTigaTiga(context);
//            String jawabanTigaEmpatValue = SessionManager.getJawabanTigaEmpat(context);
//            String jawabanTigaLimaValue = SessionManager.getJawabanTigaLima(context);
//            String namaNarasumber3Value = SessionManager.getNamaNarasumber3(context);
//            String namaNarasumber4Value = SessionManager.getNamaNarasumber4(context);
//            String namaAlamatUsahaValue = SessionManager.getNamaAlamatKantor(context);
//            String latitude2Value = SessionManager.getLatitude2(context);
//            String longitude2Value = SessionManager.getLongitude2(context);
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
                        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
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
        if (a == R.id.buttonSubmit){
            checkKosong();
//            saveContent();
//            Intent i = new Intent(context, SurveyKonsumenActivity.class);
//            startActivity(i);
        }
        else if (a == R.id.buttonBack){
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
