package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcafinance.itdp.mobilesurvey.LoginActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;

public class ProfileActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ImageView fotoProfile = findViewById(R.id.fotoProfile);
        TextView nama = findViewById(R.id.nama);
        String textnama = SessionManager.getUsername(context);
        nama.setText(textnama);
        
        TextView email = findViewById(R.id.email);
        String text = SessionManager.getNIP(context);
        email.setText(text);

        fotoProfile.setImageResource(R.drawable.ic_account_circle_black_24dp);
    }
}
