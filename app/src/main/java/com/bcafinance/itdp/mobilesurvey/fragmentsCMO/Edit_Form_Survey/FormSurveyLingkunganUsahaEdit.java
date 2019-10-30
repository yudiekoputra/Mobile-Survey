//package com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.customs.CustomExpandCollapseBar;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//import com.github.aakira.expandablelayout.ExpandableLinearLayout;
//
//public class FormSurveyLingkunganUsahaEdit extends AppCompatActivity {
//
//    private Context context = this;
//    private TextView barExpandNarasumber1, barExpandNarasumber2;
//    private ExpandableLinearLayout layoutNarasumber1, layoutNarasumber2;
//    private CustomExpandCollapseBar expandCollapseBar;
//    private EditText namaNarasumber1, jawabanTigaDua, jawabanTigaTiga, namaNarasumber2, jawabanTigaEmpat, jawabanTigaLima;
//    private Spinner pertanyaanTigaDua, pertanyaanTigaTiga, pertanyaanTigaEmpat, pertanyaanTigaLima;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_survey_lingkungan_usaha_edit);
//
//        barExpandNarasumber1 = findViewById(R.id.barExpandNarasumber1);
//        layoutNarasumber1 = findViewById(R.id.layoutNarasumber1);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber1, layoutNarasumber1, false);
//
//        barExpandNarasumber2 = findViewById(R.id.barExpandNarasumber2);
//        layoutNarasumber2 = findViewById(R.id.layoutNarasumber2);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber2, layoutNarasumber2, false);
//
//        namaNarasumber1 = findViewById(R.id.namaNarasumber1);
//        jawabanTigaDua = findViewById(R.id.jawabanTigaDua);
//        jawabanTigaTiga = findViewById(R.id.jawabanTigaTiga);
//        namaNarasumber2 = findViewById(R.id.namaNarasumber2);
//        jawabanTigaEmpat = findViewById(R.id.jawabanTigaEmpat);
//        jawabanTigaLima = findViewById(R.id.jawabanTigaLima);
//        pertanyaanTigaDua = findViewById(R.id.pertanyaanTigaDua);
//        pertanyaanTigaDua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanTigaDua.getSelectedItemId()==6){
//                    jawabanTigaDua.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanTigaDua.setVisibility(View.GONE);
//                    jawabanTigaDua.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanTigaTiga = findViewById(R.id.pertanyaanTigaTiga);
//        pertanyaanTigaEmpat = findViewById(R.id.pertanyaanTigaEmpat);
//        pertanyaanTigaEmpat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanTigaEmpat.getSelectedItemId()==6){
//                    jawabanTigaEmpat.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanTigaEmpat.setVisibility(View.GONE);
//                    jawabanTigaEmpat.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanTigaLima = findViewById(R.id.pertanyaanTigaLima);
//
//        namaNarasumber1.setText(SessionManager.getNamaNarasumber3(context));
//        namaNarasumber2.setText(SessionManager.getNamaNarasumber4(context));
//        jawabanTigaDua.setText(SessionManager.getJawabanTigaDua(context));
//        jawabanTigaTiga.setText(SessionManager.getJawabanTigaTiga(context));
//        jawabanTigaEmpat.setText(SessionManager.getJawabanTigaEmpat(context));
//        jawabanTigaLima.setText(SessionManager.getJawabanTigaLima(context));
//
//        Button buttonSubmitSurveyLingkunganUsaha = findViewById(R.id.buttonSubmitSurveyLingkunganUsaha);
//        buttonSubmitSurveyLingkunganUsaha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveSurveyLingkunganUsaha();
//            }
//        });
//
//        setSpinnerData();
//    }
//
//    private void saveSurveyLingkunganUsaha(){
//        String pertanyaanTigaDuaValue = pertanyaanTigaDua.getSelectedItem().toString();
//        String pertanyaanTigaTigaValue = pertanyaanTigaTiga.getSelectedItem().toString();
//        String pertanyaanTigaEmpatValue = pertanyaanTigaEmpat.getSelectedItem().toString();
//        String pertanyaanTigaLimaValue = pertanyaanTigaLima.getSelectedItem().toString();
//        String jawabanTigaDuaValue = jawabanTigaDua.getText().toString();
//        String jawabanTigaTigaValue = jawabanTigaTiga.getText().toString();
//        String jawabanTigaEmpatValue = jawabanTigaEmpat.getText().toString();
//        String jawabanTigaLimaValue = jawabanTigaLima.getText().toString();
//        String namaNarasumber3Value = namaNarasumber1.getText().toString();
//        String namaNarasumber4Value = namaNarasumber2.getText().toString();
//
//        SessionManager.saveSurveyLingkunganUsaha(context, pertanyaanTigaDuaValue, pertanyaanTigaTigaValue, pertanyaanTigaEmpatValue, pertanyaanTigaLimaValue, jawabanTigaDuaValue,
//                jawabanTigaTigaValue, jawabanTigaEmpatValue, jawabanTigaLimaValue, namaNarasumber3Value, namaNarasumber4Value);
//
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void setSpinnerData() {
//        ArrayAdapter<String> adapterPertanyaanTigaDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGADUA);
//        adapterPertanyaanTigaDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaDua.setAdapter(adapterPertanyaanTigaDua);
//
//        ArrayAdapter<String> adapterPertanyaanTigaTiga = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGATIGA);
//        adapterPertanyaanTigaTiga.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaTiga.setAdapter(adapterPertanyaanTigaTiga);
//
//        ArrayAdapter<String> adapterPertanyaanTigaEmpat = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGADUA);
//        adapterPertanyaanTigaEmpat.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaEmpat.setAdapter(adapterPertanyaanTigaEmpat);
//
//        ArrayAdapter<String> adapterPertanyaanTigaLima = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGATIGA);
//        adapterPertanyaanTigaLima.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaLima.setAdapter(adapterPertanyaanTigaLima);
//
//    }
//}
