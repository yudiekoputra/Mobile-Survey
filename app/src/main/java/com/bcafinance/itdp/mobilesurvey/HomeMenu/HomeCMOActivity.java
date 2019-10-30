package com.bcafinance.itdp.mobilesurvey.HomeMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.LoginActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.HistoryCMOActivity;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.InputSurveyActivity;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.ProfileActivity;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class HomeCMOActivity extends AppCompatActivity {
    private Context context = this;
    private TextView username;
    private ImageButton imageLogOut;
    private int counter_back = 1;
    private CardView buttonInputForm, buttonHistory, buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        username = findViewById(R.id.username);
        String text = SessionManager.getUsername(context);
//        String text = SessionManager.getNIP(context);
        username.setText(text);

        buttonInputForm = findViewById(R.id.buttonInputForm);
        buttonInputForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, FormIdentitasKonsumenActivity.class);
                Intent intent = new Intent(context, InputSurveyActivity.class);
                startActivity(intent);
//                finish();
            }
        });
        imageLogOut = findViewById(R.id.imageLogOut);
        imageLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutConfirmation();
            }
        });

        buttonHistory = findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoryCMOActivity.class);
//                Intent intent = new Intent(context, HistoryCMOActivity.class);
                startActivity(intent);
            }
        });

        buttonProfile = findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
//                finish();
            }
        });

    }

    private void logoutConfirmation(){
        AlertDialog.Builder option = new AlertDialog.Builder(context);
        option.setMessage("Anda yakin mau Logout ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //jika user pilih ya
                        logoutApp();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //jika user pilih tidak
                        dialog.cancel();
                    }
                })
                .setCancelable(false);
        AlertDialog showOption = option.create();
        showOption.show();
    }

    private void logoutApp(){
        SessionManager.saveLoginFlag(context, false);

        //open login activity
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);

        //tutup home menu activity
        finish();
    }

    //fungsi handle exit app 2 kali tap back

    @Override
    protected void onResume() {
        counter_back = 1;
        super.onResume();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(counter_back<2){
            Toast.makeText(context, "Tekan Back sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            counter_back++;
            countdownReset();
        }else if(counter_back==2){
            finish();
        }

    }

    private void countdownReset(){
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                counter_back=1;
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, Constanta.COUNTDOWN_RESET);
    }
}
