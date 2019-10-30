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
//import android.widget.Spinner;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//
//public class FormSurveyRumahKonsumenEdit extends AppCompatActivity {
//    private Context context = this;
//    private Spinner pertanyaanEnam, pertanyaanTujuh, pertanyaanDelapan, pertanyaanSembilan, pertanyaanSepuluh, pertanyaanSebelas, pertanyaanDuaBelas, pertanyaanTigaBelas;
//    private Button buttonSubmitSurveyRumahKonsumen;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_survey_rumah_konsumen_edit);
//
//        pertanyaanEnam = findViewById(R.id.pertanyaanEnam);
//        pertanyaanTujuh = findViewById(R.id.pertanyaanTujuh);
//        pertanyaanDelapan = findViewById(R.id.pertanyaanDelapan);
//        pertanyaanSembilan = findViewById(R.id.pertanyaanSembilan);
//        pertanyaanSepuluh = findViewById(R.id.pertanyaanSepuluh);
//        pertanyaanSebelas = findViewById(R.id.pertanyaanSebelas);
//        pertanyaanDuaBelas = findViewById(R.id.pertanyaanDuaBelas);
//        pertanyaanTigaBelas = findViewById(R.id.pertanyaanTigaBelas);
//        buttonSubmitSurveyRumahKonsumen = findViewById(R.id.buttonSubmitSurveyRumahKonsumen);
//        buttonSubmitSurveyRumahKonsumen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveSurveyRumahKonsumen();
//            }
//        });
//
//        setSpinnerData();
//
//    }
//
//    private void saveSurveyRumahKonsumen(){
//        String pertanyaanEnamValue = pertanyaanEnam.getSelectedItem().toString();
//        String pertanyaanTujuhValue = pertanyaanTujuh.getSelectedItem().toString();
//        String pertanyaanDelapanValue = pertanyaanDelapan.getSelectedItem().toString();
//        String pertanyaanSembilanValue = pertanyaanSembilan.getSelectedItem().toString();
//        String pertanyaanSepuluhValue = pertanyaanSepuluh.getSelectedItem().toString();
//        String pertanyaanSebelasValue = pertanyaanSebelas.getSelectedItem().toString();
//        String pertanyaanDuaBelasValue = pertanyaanDuaBelas.getSelectedItem().toString();
//        String pertanyaanTigaBelasValue = pertanyaanTigaBelas.getSelectedItem().toString();
//
//        SessionManager.saveSurveyRumahKonsumen(context, pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue, pertanyaanDuaBelasValue, pertanyaanTigaBelasValue);
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void setSpinnerData() {
//        ArrayAdapter<String> adapterPertanyaanEnam = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAM);
//        adapterPertanyaanEnam.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanEnam.setAdapter(adapterPertanyaanEnam);
//
//        ArrayAdapter<String> adapterPertanyaanTujuh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TUJUH);
//        adapterPertanyaanTujuh.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTujuh.setAdapter(adapterPertanyaanTujuh);
//
//        ArrayAdapter<String> adapterPertanyaanDelapan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DELAPAN);
//        adapterPertanyaanDelapan.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDelapan.setAdapter(adapterPertanyaanDelapan);
//
//        ArrayAdapter<String> adapterPertanyaanSembilan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEMBILAN);
//        adapterPertanyaanSembilan.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanSembilan.setAdapter(adapterPertanyaanSembilan);
//
//        ArrayAdapter<String> adapterPertanyaanSepuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEPULUH);
//        adapterPertanyaanSepuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanSepuluh.setAdapter(adapterPertanyaanSepuluh);
//
//        ArrayAdapter<String> adapterPertanyaanSebelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEBELAS);
//        adapterPertanyaanSebelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanSebelas.setAdapter(adapterPertanyaanSebelas);
//
//        ArrayAdapter<String> adapterPertanyaanDuaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUABELAS);
//        adapterPertanyaanDuaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaBelas.setAdapter(adapterPertanyaanDuaBelas);
//
//        ArrayAdapter<String> adapterPertanyaanTigaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGABELAS);
//        adapterPertanyaanTigaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaBelas.setAdapter(adapterPertanyaanTigaBelas);
//    }
//}
