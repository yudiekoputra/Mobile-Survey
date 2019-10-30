//package com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.icu.util.Calendar;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//
//import java.text.SimpleDateFormat;
//
//public class FormIdentitasKonsumenEdit extends AppCompatActivity {
//    private Context context = this;
//    private EditText namaKonsumen, noKTP, tempatLahir, tanggalLahir, alamatRumah, kelurahanRumah, kecamatanRumah, kodePosRumah, noTelp, namaPasangan, jmlTanggungan, namaIbuKandung, namaTempatUsaha, alamatUsaha, kelurahanUsaha, kecamatanUsaha, kodePosUsaha, merkMobil, warnaMobil, dealerShowroom, tanggalSurvey, jamSurvey;
//    private Button buttonSubmitIdentitasKonsumen;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_identitas_konsumen_edit);
//
//        namaKonsumen = findViewById(R.id.namaKonsumen);
//        namaKonsumen.setText(SessionManager.getNamaKonsumen(context));
//        noKTP = findViewById(R.id.noKTP);
//        noKTP.setText(SessionManager.getNoKTP(context));
//        tempatLahir = findViewById(R.id.tempatLahir);
//        tempatLahir.setText(SessionManager.getTempatLahir(context));
//        tanggalLahir = findViewById(R.id.tanggalLahir);
//        tanggalLahir.setText(SessionManager.getTanggalLahir(context));
//        alamatRumah = findViewById(R.id.alamatRumah);
//        alamatRumah.setText(SessionManager.getAlamatRumah(context));
//        kelurahanRumah = findViewById(R.id.kelurahanRumah);
//        kelurahanRumah.setText(SessionManager.getKelurahanRumah(context));
//        kecamatanRumah = findViewById(R.id.kecamatanRumah);
//        kecamatanRumah.setText(SessionManager.getKecamatanRumah(context));
//        kodePosRumah = findViewById(R.id.kodePosRumah);
//        kodePosRumah.setText(SessionManager.getKodePosRumah(context));
//        noTelp = findViewById(R.id.noTelp);
//        noTelp.setText(SessionManager.getNoTelp(context));
//        namaPasangan = findViewById(R.id.namaPasangan);
//        namaPasangan.setText(SessionManager.getNamaPasangan(context));
//        jmlTanggungan = findViewById(R.id.jmlTanggungan);
//        jmlTanggungan.setText(SessionManager.getJmlTanggungan(context));
//        namaIbuKandung = findViewById(R.id.namaIbuKandung);
//        namaIbuKandung.setText(SessionManager.getNamaIbuKandung(context));
//        namaTempatUsaha = findViewById(R.id.namaTempatUsaha);
//        namaTempatUsaha.setText(SessionManager.getNamaUsaha(context));
//        alamatUsaha = findViewById(R.id.alamatUsaha);
//        alamatUsaha.setText(SessionManager.getAlamatUsaha(context));
//        kelurahanUsaha = findViewById(R.id.kelurahanUsaha);
//        kelurahanUsaha.setText(SessionManager.getKelurahanUsaha(context));
//        kecamatanUsaha = findViewById(R.id.kecamatanUsaha);
//        kecamatanUsaha.setText(SessionManager.getKecamatanUsaha(context));
//        kodePosUsaha = findViewById(R.id.kodePosUsaha);
//        kodePosUsaha.setText(SessionManager.getKodePosUsaha(context));
//        merkMobil = findViewById(R.id.merkMobil);
//        merkMobil.setText(SessionManager.getMerkMobil(context));
//        warnaMobil = findViewById(R.id.warnaMobil);
//        warnaMobil.setText(SessionManager.getWarnaMobil(context));
//        dealerShowroom = findViewById(R.id.dealerShowroom);
//        dealerShowroom.setText(SessionManager.getDealerShowroom(context));
//        tanggalSurvey = findViewById(R.id.tanggalSurvey);
//        tanggalSurvey.setText(SessionManager.getTanggalSurvey(context));
//        jamSurvey = findViewById(R.id.jamSurvey);
//        jamSurvey.setText(SessionManager.getJamSurvey(context));
//        buttonSubmitIdentitasKonsumen = findViewById(R.id.buttonSubmitIdentitasKonsumen);
//        buttonSubmitIdentitasKonsumen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveContent();
//            }
//        });
//
//        setTanggalLahir();
//        setTanggalSurvey();
//    }
//
//    private void saveContent(){
////        String namaKonsumenValue = namaKonsumen.getText().toString();
////        String noKTPValue = noKTP.getText().toString();
////        String tempatLahirValue = tempatLahir.getText().toString();
////        String tanggalLahirValue = tanggalLahir.getText().toString();
////        String alamatRumahValue = alamatRumah.getText().toString();
////        String kelurahanRumahValue = kelurahanRumah.getText().toString();
////        String kecamatanRumahValue = kecamatanRumah.getText().toString();
////        String kodePosRumahValue = kodePosRumah.getText().toString();
////        String noTelpValue = noTelp.getText().toString();
////        String namaPasanganValue = namaPasangan.getText().toString();
////        String jmlTanggunganValue = jmlTanggungan.getText().toString();
////        String namaIbuKandungValue = namaIbuKandung.getText().toString();
////        String namaTempatUsahaValue =namaTempatUsaha.getText().toString();
////        String alamatUsahaValue = alamatUsaha.getText().toString();
////        String kelurahanUsahaValue = kelurahanUsaha.getText().toString();
////        String kecamatanUsahaValue = kecamatanUsaha.getText().toString();
////        String kodePosUsahaValue = kodePosUsaha.getText().toString();
////        String merkMobilValue = merkMobil.getText().toString();
////        String warnaMobilValue = warnaMobil.getText().toString();
////        String dealerShowroomValue = dealerShowroom.getText().toString();
////        String tanggalSurveyValue = tanggalSurvey.getText().toString();
////        String jamSurveyValue = jamSurvey.getText().toString();
////
////        //save data login
////        SessionManager.saveDataDiri(context, namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, alamatRumahValue, kelurahanRumahValue, kecamatanRumahValue,
////                kodePosRumahValue, noTelpValue, namaPasanganValue, jmlTanggunganValue, namaIbuKandungValue, namaTempatUsahaValue, alamatUsahaValue, kelurahanUsahaValue, kecamatanUsahaValue, kodePosUsahaValue,
////                merkMobilValue, warnaMobilValue, dealerShowroomValue, tanggalSurveyValue, jamSurveyValue);
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    //untuk input tanggal kalender
//    private void setTanggalLahir(){
//        tanggalLahir.setFocusable(false);
//        tanggalLahir.setClickable(true);
//
//        Calendar today = Calendar.getInstance();
//        final int yearNow = today.get(Calendar.YEAR);
//        final int monthNow = today.get(Calendar.MONTH);
//        final int dayNow = today.get(Calendar.DATE);
//
//        tanggalLahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.CustomDatePicker, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Calendar selected = Calendar.getInstance();
//                        selected.set(year,month,dayOfMonth);
//
//                        //konversi ke string
//                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
//                        String tanggal = formatDate.format(selected.getTime());
//
//                        tanggalLahir.setText(tanggal);
//                    }
//                }, yearNow, monthNow,dayNow );
//                datePickerDialog.getDatePicker().setSpinnersShown(true);
//                datePickerDialog.getDatePicker().setCalendarViewShown(false);
//                datePickerDialog.show();
//            }
//        });
//    }
//
//    private void setTanggalSurvey(){
//        tanggalSurvey.setFocusable(false);
//        tanggalSurvey.setClickable(true);
//
//        Calendar today = Calendar.getInstance();
//        final int yearNow = today.get(Calendar.YEAR);
//        final int monthNow = today.get(Calendar.MONTH);
//        final int dayNow = today.get(Calendar.DATE);
//
//        tanggalSurvey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.CustomDatePicker, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Calendar selected = Calendar.getInstance();
//                        selected.set(year,month,dayOfMonth);
//
//                        //konversi ke string
//                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
//                        String tanggal = formatDate.format(selected.getTime());
//
//                        tanggalSurvey.setText(tanggal);
//                    }
//                }, yearNow, monthNow,dayNow );
//                datePickerDialog.getDatePicker().setSpinnersShown(true);
//                datePickerDialog.getDatePicker().setCalendarViewShown(false);
//                datePickerDialog.show();
//            }
//        });
//    }
//}
