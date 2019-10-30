//package com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
//import com.google.android.material.textfield.TextInputEditText;
//
//public class FormInformasiTambahanEdit extends AppCompatActivity {
//    private Context context = this;
//
//    private TextInputEditText informasiTambahan;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_informasi_tambahan_edit);
//
//        informasiTambahan=findViewById(R.id.informasiTambahan);
//        informasiTambahan.setText(SessionManager.getInformasiTambahan(context));
//        Button buttonSubmitInformasiTambahan = findViewById(R.id.buttonSubmitInformasiTambahan);
//        buttonSubmitInformasiTambahan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveInformasiTambahan();
//            }
//        });
//    }
//
//    private void saveInformasiTambahan(){
//        String informasiTambahanValue = informasiTambahan.getText().toString();
//
//        SessionManager.saveInformasiTambahan(context, informasiTambahanValue);
//
//        Intent intent = new Intent(context, FormKonfirmasiSurvey.class);
//        startActivity(intent);
//        finish();
//    }
//}
