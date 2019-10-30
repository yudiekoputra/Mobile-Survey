//package com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//
//public class FormSurveyKonsumenEdit extends AppCompatActivity {
//    private Context context = this;
//    private Spinner pertanyaanSatu, pertanyaanDua, platNomor, merkTipe, warna, tahunKendaraan, pertanyaanLima;
//    private EditText jenisKredit, totalAngsuran, totalPenghasilan;
////    SharedPreferences sharedPreferences;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_survey_konsumen_edit);
//
//        //belum bisa ambil data dari spinner activity lain
////        sharedPreferences = getSharedPreferences("spinner", Context.MODE_PRIVATE);
////        final int pertanyaanSatuValue = sharedPreferences.getInt("pertanyaanSatu", 0);
//
//        pertanyaanSatu = findViewById(R.id.pertanyaanSatu);
//        pertanyaanDua = findViewById(R.id.pertanyaanDua);
//        platNomor = findViewById(R.id.platNomor);
//        merkTipe = findViewById(R.id.merkTipe);
//        warna = findViewById(R.id.warna);
//        tahunKendaraan = findViewById(R.id.tahunKendaraan);
//        jenisKredit = findViewById(R.id.jenisKredit);
//        totalAngsuran = findViewById(R.id.totalAngsuran);
//        totalPenghasilan = findViewById(R.id.totalPenghasilan);
//        pertanyaanLima = findViewById(R.id.pertanyaanLima);
//
////        pertanyaanSatu.setSelection(pertanyaanSatuValue);
////        pertanyaanSatu.setSelection(SessionManager.getPertanyaanSatuSpin(0));
////        pertanyaanDua.setOnItemSelectedListener(SessionManager.getPertanyaanDua(context));
////        platNomor.setOnItemSelectedListener(SessionManager.getPlatNomor(context));
////        merkTipe.setOnItemSelectedListener(SessionManager.getMerkTipe(context));
////        warna.setOnItemClickListener(SessionManager.getWarna(context));
////        tahunKendaraan.setOnItemSelectedListener(AdapterView.OnItemSelectedListener);
//        jenisKredit.setText(SessionManager.getJenisKredit(context));
//        totalAngsuran.setText(SessionManager.getTotalAngsuran(context));
//        totalPenghasilan.setText(SessionManager.getTotalPenghasilan(context));
////        pertanyaanLima.getSelectedItem(SessionManager.getPertanyaanLima(context));
//
//        Button buttonSubmitSurveyKonsumen = findViewById(R.id.buttonSubmitSurveyKonsumen);
//        buttonSubmitSurveyKonsumen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveContent();
//            }
//        });
//
//        setSpinnerData();
//
//    }
//
//    private void saveContent(){
//        String pertanyaanSatuValue = pertanyaanSatu.getSelectedItem().toString();
//        String pertanyaanDuaValue = pertanyaanDua.getSelectedItem().toString();
//        String platNomorValue = platNomor.getSelectedItem().toString();
//        String merkTipeValue = merkTipe.getSelectedItem().toString();
//        String warnaValue = warna.getSelectedItem().toString();
//        String tahunKendaraanValue = tahunKendaraan.getSelectedItem().toString();
//        String jenisKreditValue = jenisKredit.getText().toString();
//        String totalAngsuranValue = totalAngsuran.getText().toString();
//        String totalPenghasilanValue = totalPenghasilan.getText().toString();
//        String pertanyaanLimaValue = pertanyaanLima.getSelectedItem().toString();
//
////        SessionManager.saveSurveyKonsumen(context, pertanyaanSatuValue, pertanyaanDuaValue, platNomorValue, merkTipeValue, warnaValue,
////                tahunKendaraanValue, jenisKreditValue, totalAngsuranValue, totalPenghasilanValue, pertanyaanLimaValue);
////        Intent intent = new Intent(context, FormSurveyRumahKonsumen.class);
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void setSpinnerData() {
//        ArrayAdapter<String> adapterPertanyaanSatu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SATU);
//        adapterPertanyaanSatu.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanSatu.setAdapter(adapterPertanyaanSatu);
//
//        ArrayAdapter<String> adapterPertanyaanDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUA);
//        adapterPertanyaanDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDua.setAdapter(adapterPertanyaanDua);
//
//        ArrayAdapter<String> adapterPlatNomor = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.TAHU_TIDAKTAHU);
//        adapterPlatNomor.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        platNomor.setAdapter(adapterPlatNomor);
//
//        ArrayAdapter<String> adapterMerkTipe = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.TAHU_TIDAKTAHU);
//        adapterMerkTipe.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        merkTipe.setAdapter(adapterMerkTipe);
//
//        ArrayAdapter<String> adapterWarna = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.TAHU_TIDAKTAHU);
//        adapterWarna.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        warna.setAdapter(adapterWarna);
//
//        ArrayAdapter<String> adapterTahunKendaraan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.TAHU_TIDAKTAHU);
//        adapterTahunKendaraan.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        tahunKendaraan.setAdapter(adapterTahunKendaraan);
//
//        ArrayAdapter<String> adapterPertanyaanLima = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMA);
//        adapterPertanyaanLima.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanLima.setAdapter(adapterPertanyaanLima);
//    }
//}
