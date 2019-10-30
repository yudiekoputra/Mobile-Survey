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
//import android.widget.ImageButton;
//import android.widget.Spinner;
//
//import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormSurveyLingkunganUsaha;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormVerifikasiLingkunganRumah;
//import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//
//public class FormPengamatanSurveyorEdit extends AppCompatActivity {
//
//    private Context context = this;
//    private Spinner pertanyaanDuaDelapan, pertanyaanDuaSembilan, pertanyaanTigaPuluh, pertanyaanTigaSatu;
//    private EditText jawabanDuaDelapan, jawabanDuaSembilan;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_pengamatan_surveyor_edit);
//
//        jawabanDuaDelapan = findViewById(R.id.jawabanDuaDelapan);
//        jawabanDuaSembilan = findViewById(R.id.jawabanDuaSembilan);
//        pertanyaanDuaDelapan = findViewById(R.id.pertanyaanduaDelapan);
//        pertanyaanDuaDelapan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanDuaDelapan.getSelectedItemId()==8){
//                    jawabanDuaDelapan.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaDelapan.setVisibility(View.GONE);
//                    jawabanDuaDelapan.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanDuaSembilan = findViewById(R.id.pertanyaanDuaSembilan);
//        pertanyaanDuaSembilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(pertanyaanDuaSembilan.getSelectedItemId()==7){
//                    jawabanDuaSembilan.setVisibility(View.VISIBLE);
//                }else{
//                    jawabanDuaSembilan.setVisibility(View.GONE);
//                    jawabanDuaSembilan.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                return;
//            }
//        });
//        pertanyaanTigaPuluh = findViewById(R.id.pertanyaanTigaPuluh);
//        pertanyaanTigaSatu = findViewById(R.id.pertanyaanTigaSatu);
//
//        jawabanDuaDelapan.setText(SessionManager.getJawabanDuaDelapan(context));
//        jawabanDuaSembilan.setText(SessionManager.getJawabanDuaSembilan(context));
//
//        Button buttonSubmitpengamatanSurveyor = findViewById(R.id.buttonSubmitpengamatanSurveyor);
//        buttonSubmitpengamatanSurveyor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savePengamatanSurveyor();
//            }
//        });
//
//        setSpinnerData();
//    }
//
//    private void savePengamatanSurveyor() {
//        String pertanyaanDuaDelapanValue = pertanyaanDuaDelapan.getSelectedItem().toString();
//        String pertanyaanDuaSembilanValue = pertanyaanDuaSembilan.getSelectedItem().toString();
//        String pertanyaanTigaPuluhValue = pertanyaanTigaPuluh.getSelectedItem().toString();
//        String pertanyaanTigaSatuValue = pertanyaanTigaSatu.getSelectedItem().toString();
//        String jawabanDuaDelapanValue = jawabanDuaDelapan.getText().toString();
//        String jawabanDuaSembilanValue = jawabanDuaSembilan.getText().toString();
//
//        SessionManager.savePengamatanSurveyor(context, pertanyaanDuaDelapanValue, pertanyaanDuaSembilanValue, pertanyaanTigaPuluhValue, pertanyaanTigaSatuValue,
//                jawabanDuaDelapanValue, jawabanDuaSembilanValue);
//
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void setSpinnerData() {
//        ArrayAdapter<String> adapterPertanyaanDuaDelapan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUADELAPAN);
//        adapterPertanyaanDuaDelapan.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaDelapan.setAdapter(adapterPertanyaanDuaDelapan);
//
//        ArrayAdapter<String> adapterPertanyaanDuaSembilan = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_DUASEMBILAN);
//        adapterPertanyaanDuaSembilan.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanDuaSembilan.setAdapter(adapterPertanyaanDuaSembilan);
//
//        ArrayAdapter<String> adapterPertanyaanTigaPuluh = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGAPULUH);
//        adapterPertanyaanTigaPuluh.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaPuluh.setAdapter(adapterPertanyaanTigaPuluh);
//
//        ArrayAdapter<String> adapterPertanyaanTigaSatu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Constanta.PERTANYAAN_TIGASATU);
//        adapterPertanyaanTigaSatu.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        pertanyaanTigaSatu.setAdapter(adapterPertanyaanTigaSatu);
//    }
//}
