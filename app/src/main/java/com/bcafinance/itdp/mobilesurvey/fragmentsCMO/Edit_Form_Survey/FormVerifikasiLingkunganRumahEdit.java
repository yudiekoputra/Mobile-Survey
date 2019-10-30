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
//public class FormVerifikasiLingkunganRumahEdit extends AppCompatActivity {
//
//    private Context context = this;
//    private TextView barExpandNarasumber1, barExpandNarasumber2;
//    private ExpandableLinearLayout layoutNarasumber1, layoutNarasumber2;
//    private CustomExpandCollapseBar expandCollapseBar;
//    private EditText namaNarasumber1, jawabanEnamBelas, jawabanTujuhBelas, jawabanDelapanBelasTipe, jawabanDelapanBelasWarna, jawabanDuaPuluh, namaNarasumber2,
//            jawabanDuaTiga, jawabanDuaEmpat, jawabanDuaLimaTipe, jawabanDuaLimaWarna, jawabanDuaTujuh;
//    private Spinner pertanyaanEmpatBelas, pertanyaanLimaBelas, pertanyaanEnamBelas, pertanyaanTujuhBelas, pertanyaanDelapanBelas, pertanyaanSembilanBelas,
//            pertanyaanDuaPuluh, pertanyaanDuaSatu, pertanyaanDuaDua, pertanyaanDuaTiga, pertanyaanDuaEmpat, pertanyaanDuaLima, pertanyaanDuaEnam, pertanyaanDuaTujuh;
//
//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_verifikasi_lingkungan_rumah_edit);
//
//        barExpandNarasumber1 =findViewById(R.id.barExpandNarasumber1);
//        layoutNarasumber1 = findViewById(R.id.layoutNarasumber1);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber1, layoutNarasumber1,false);
//
//        barExpandNarasumber2 =findViewById(R.id.barExpandNarasumber2);
//        layoutNarasumber2 = findViewById(R.id.layoutNarasumber2);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandNarasumber2, layoutNarasumber2,false);
//
//        namaNarasumber1 = findViewById(R.id.namaNarasumber1);
//        jawabanEnamBelas = findViewById(R.id.jawabanEnamBelas);
//        jawabanTujuhBelas = findViewById(R.id.jawabanTujuhBelas);
//        jawabanDelapanBelasTipe = findViewById(R.id.jawabanDelapanBelasTipe);
//        jawabanDelapanBelasWarna = findViewById(R.id.jawabanDelapanBelasWarna);
//        jawabanDuaPuluh = findViewById(R.id.jawabanDuaPuluh);
//        namaNarasumber2 = findViewById(R.id.namaNarasumber2);
//        jawabanDuaTiga = findViewById(R.id.jawabanDuaTiga);
//        jawabanDuaEmpat = findViewById(R.id.jawabanDuaEmpat);
//        jawabanDuaLimaTipe = findViewById(R.id.jawabanDuaLimaTipe);
//        jawabanDuaLimaWarna = findViewById(R.id.jawabanDuaLimaWarna);
//        jawabanDuaTujuh = findViewById(R.id.jawabanDuaTujuh);
//        pertanyaanEmpatBelas = findViewById(R.id.pertanyaanEmpatBelas);
//        pertanyaanLimaBelas = findViewById(R.id.pertanyaanLimaBelas);
//        pertanyaanEnamBelas = findViewById(R.id.pertanyaanEnamBelas);
//        pertanyaanEnamBelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanEnamBelas.getSelectedItemId() == 1){
//                    jawabanEnamBelas.setVisibility(View.VISIBLE);
//                }else if (pertanyaanEnamBelas.getSelectedItemId() == 6){
//                    jawabanEnamBelas.setVisibility(View.VISIBLE);
//                }else if (pertanyaanEnamBelas.getSelectedItemId() == 7){
//                    jawabanEnamBelas.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanEnamBelas.setVisibility(View.GONE);
//                    jawabanEnamBelas.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanTujuhBelas = findViewById(R.id.pertanyaanTujuhBelas);
//        pertanyaanTujuhBelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanTujuhBelas.getSelectedItemId() == 1){
//                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
//                }else if (pertanyaanTujuhBelas.getSelectedItemId() == 6){
//                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
//                }else if (pertanyaanTujuhBelas.getSelectedItemId() == 7){
//                    jawabanTujuhBelas.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanTujuhBelas.setVisibility(View.GONE);
//                    jawabanTujuhBelas.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDelapanBelas = findViewById(R.id.pertanyaanDelapanBelas);
//        pertanyaanDelapanBelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanDelapanBelas.getSelectedItemId()== 2){
//                    jawabanDelapanBelasTipe.setVisibility(View.VISIBLE);
//                    jawabanDelapanBelasWarna.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDelapanBelasTipe.setVisibility(View.GONE);
//                    jawabanDelapanBelasWarna.setVisibility(View.GONE);
//                    jawabanDelapanBelasTipe.setText("");
//                    jawabanDelapanBelasWarna.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanSembilanBelas = findViewById(R.id.pertanyaanSembilanBelas);
//        pertanyaanDuaPuluh = findViewById(R.id.pertanyaanDuaPuluh);
//        pertanyaanDuaPuluh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanDuaPuluh.getSelectedItemId()==6){
//                    jawabanDuaPuluh.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaPuluh.setVisibility(View.GONE);
//                    jawabanDuaPuluh.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDuaSatu = findViewById(R.id.pertanyaanDuaSatu);
//        pertanyaanDuaDua = findViewById(R.id.pertanyaanDuaDua);
//        pertanyaanDuaTiga = findViewById(R.id.pertanyaanDuaTiga);
//        pertanyaanDuaTiga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanDuaTiga.getSelectedItemId() == 1){
//                    jawabanDuaTiga.setVisibility(View.VISIBLE);
//                }else if (pertanyaanDuaTiga.getSelectedItemId() == 6){
//                    jawabanDuaTiga.setVisibility(View.VISIBLE);
//                }else if (pertanyaanDuaTiga.getSelectedItemId() == 7){
//                    jawabanDuaTiga.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaTiga.setVisibility(View.GONE);
//                    jawabanDuaTiga.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDuaEmpat = findViewById(R.id.pertanyaanDuaEmpat);
//        pertanyaanDuaEmpat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanDuaEmpat.getSelectedItemId() == 1){
//                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
//                }else if (pertanyaanDuaEmpat.getSelectedItemId() == 6){
//                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
//                }else if (pertanyaanDuaEmpat.getSelectedItemId() == 7){
//                    jawabanDuaEmpat.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaEmpat.setVisibility(View.GONE);
//                    jawabanDuaEmpat.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDuaLima = findViewById(R.id.pertanyaanDuaLima);
//        pertanyaanDuaLima.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (pertanyaanDuaLima.getSelectedItemId()== 2){
//                    jawabanDuaLimaTipe.setVisibility(View.VISIBLE);
//                    jawabanDuaLimaWarna.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaLimaTipe.setVisibility(View.GONE);
//                    jawabanDuaLimaWarna.setVisibility(View.GONE);
//                    jawabanDuaLimaTipe.setText("");
//                    jawabanDuaLimaWarna.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDuaEnam = findViewById(R.id.pertanyaanDuaEnam);
//        pertanyaanDuaTujuh = findViewById(R.id.pertanyaanDuaTujuh);
//        pertanyaanDuaTujuh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanDuaTujuh.getSelectedItemId()==6){
//                    jawabanDuaTujuh.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaTujuh.setVisibility(View.GONE);
//                    jawabanDuaTujuh.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//
//        namaNarasumber1.setText(SessionManager.getNamaNarasumber1(context));
//        namaNarasumber2.setText(SessionManager.getNamaNarasumber2(context));
//        jawabanEnamBelas.setText(SessionManager.getJawabanEnamBelas(context));
//        jawabanTujuhBelas.setText(SessionManager.getJawabanTujuhBelas(context));
//        jawabanDelapanBelasTipe.setText(SessionManager.getJawabanDelapanBelasTipe(context));
//        jawabanDelapanBelasWarna.setText(SessionManager.getJawabanDelapanBelasWarna(context));
//        jawabanDuaPuluh.setText(SessionManager.getJawabanDuaPuluh(context));
//        jawabanDuaTiga.setText(SessionManager.getJawabanDuaTiga(context));
//        jawabanDuaEmpat.setText(SessionManager.getJawabanDuaEmpat(context));
//        jawabanDuaLimaTipe.setText(SessionManager.getJawabanDuaLimaTipe(context));
//        jawabanDuaLimaWarna.setText(SessionManager.getJawabanDuaLimaWarna(context));
//        jawabanDuaTujuh.setText(SessionManager.getJawabanDuaTujuh(context));
//
//        Button buttonSubmitVerifikasiLingkunganRumah = findViewById(R.id.buttonSubmitVerifikasiLingkunganRumah);
//        buttonSubmitVerifikasiLingkunganRumah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveVerifikasiLingkunganRumah();
//            }
//        });
//
//        setSpinnerData();
//
//    }
//
//    private void saveVerifikasiLingkunganRumah(){
//        String pertanyaanEmpatBelasValue = pertanyaanEmpatBelas.getSelectedItem().toString();
//        String pertanyaanLimaBelasValue = pertanyaanLimaBelas.getSelectedItem().toString();
//        String pertanyaanEnamBelasValue = pertanyaanEnamBelas.getSelectedItem().toString();
//        String pertanyaanTujuhBelasValue = pertanyaanTujuhBelas.getSelectedItem().toString();
////        String pertanyaanDelapanBelasValue = pertanyaanDelapanBelas.getSelectedItem().toString();
//        String pertanyaanSembilanBelasValue = pertanyaanSembilanBelas.getSelectedItem().toString();
//        String pertanyaanDuaPuluhValue = pertanyaanDuaPuluh.getSelectedItem().toString();
//        String pertanyaanDuaSatuValue = pertanyaanDuaSatu.getSelectedItem().toString();
//        String pertanyaanDuaDuaValue = pertanyaanDuaDua.getSelectedItem().toString();
//        String pertanyaanDuaTigaValue = pertanyaanDuaTiga.getSelectedItem().toString();
//        String pertanyaanDuaEmpatValue = pertanyaanDuaEmpat.getSelectedItem().toString();
//        String pertanyaanDuaLimaValue = pertanyaanDuaLima.getSelectedItem().toString();
//        String pertanyaanDuaEnamValue = pertanyaanDuaEnam.getSelectedItem().toString();
//        String pertanyaanDuaTujuhValue = pertanyaanDuaTujuh.getSelectedItem().toString();
//        String jawabanEnamBelasValue = jawabanEnamBelas.getText().toString();
//        String jawabanTujuhBelasValue = jawabanTujuhBelas.getText().toString();
//        String jawabanDelapanBelasTipeValue = jawabanDelapanBelasTipe.getText().toString();
//        String jawabanDelapanBelasWarnaValue = jawabanDelapanBelasWarna.getText().toString();
//        String jawabanDuaPuluhValue = jawabanDuaPuluh.getText().toString();
//        String jawabanDuaTigaValue = jawabanDuaTiga.getText().toString();
//        String jawabanDuaEmpatValue = jawabanDuaEmpat.getText().toString();
//        String jawabanDuaLimaTipeValue = jawabanDuaLimaTipe.getText().toString();
//        String jawabanDuaLimaWarnaValue = jawabanDuaLimaWarna.getText().toString();
//        String jawabanDuaTujuhValue = jawabanDuaTujuh.getText().toString();
//        String namaNarasumber1Value = namaNarasumber1.getText().toString();
//        String namaNarasumber2Value = namaNarasumber2.getText().toString();
//
//        SessionManager.saveVerifikasiLingkunganRumah(context, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue,
//                pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue, pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue,
//                pertanyaanDuaEnamValue,pertanyaanDuaTujuhValue, jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue,
//                jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value);
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void setSpinnerData(){
//        ArrayAdapter<String> adapterPertanyaanEmpatBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_EMPATBELAS);
//        adapterPertanyaanEmpatBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanEmpatBelas.setAdapter(adapterPertanyaanEmpatBelas);
//
//        ArrayAdapter<String> adapterPertanyaanLimaBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMABELAS);
//        adapterPertanyaanLimaBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanLimaBelas.setAdapter(adapterPertanyaanLimaBelas);
//
//        ArrayAdapter<String> adapterPertanyaanEnamBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
//        adapterPertanyaanEnamBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanEnamBelas.setAdapter(adapterPertanyaanEnamBelas);
//
//        ArrayAdapter<String> adapterPertanyaanTujuhBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
//        adapterPertanyaanTujuhBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTujuhBelas.setAdapter(adapterPertanyaanTujuhBelas);
//
//        ArrayAdapter<String> adapterPertanyaanDelapanBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DELAPANBELAS);
//        adapterPertanyaanDelapanBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDelapanBelas.setAdapter(adapterPertanyaanDelapanBelas);
//
//        ArrayAdapter<String> adapterPertanyaanSembilanBelas = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEMBILANBELAS);
//        adapterPertanyaanSembilanBelas.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanSembilanBelas.setAdapter(adapterPertanyaanSembilanBelas);
//
//        ArrayAdapter<String> adapterPertanyaanDuaPuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUAPULUH);
//        adapterPertanyaanDuaPuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaPuluh.setAdapter(adapterPertanyaanDuaPuluh);
//
//        ArrayAdapter<String> adapterPertanyaanDuaSatu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_EMPATBELAS);
//        adapterPertanyaanDuaSatu.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaSatu.setAdapter(adapterPertanyaanDuaSatu);
//
//        ArrayAdapter<String> adapterPertanyaanDuaDua = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_LIMABELAS);
//        adapterPertanyaanDuaDua.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaDua.setAdapter(adapterPertanyaanDuaDua);
//
//        ArrayAdapter<String> adapterPertanyaanDuaTiga = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
//        adapterPertanyaanDuaTiga.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaTiga.setAdapter(adapterPertanyaanDuaTiga);
//
//        ArrayAdapter<String> adapterPertanyaanDuaEmpat = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_ENAMBELAS);
//        adapterPertanyaanDuaEmpat.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaEmpat.setAdapter(adapterPertanyaanDuaEmpat);
//
//        ArrayAdapter<String> adapterPertanyaanDuaLima = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DELAPANBELAS);
//        adapterPertanyaanDuaLima.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaLima.setAdapter(adapterPertanyaanDuaLima);
//
//        ArrayAdapter<String> adapterPertanyaanDuaEnam = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_SEMBILANBELAS);
//        adapterPertanyaanDuaEnam.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaEnam.setAdapter(adapterPertanyaanDuaEnam);
//
//        ArrayAdapter<String> adapterPertanyaanDuaTujuh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUAPULUH);
//        adapterPertanyaanDuaTujuh.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaTujuh.setAdapter(adapterPertanyaanDuaTujuh);
//    }
//}
