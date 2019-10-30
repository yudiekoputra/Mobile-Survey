package com.bcafinance.itdp.mobilesurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeBMActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeRMActivity;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class HomeMenuActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonLogout;
    private int counter_back = 1;
    private Button buttonCMO, buttonRM, buttonBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        buttonCMO = (Button)findViewById(R.id.buttonCMO);
        buttonCMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeCMOActivity.class);
                startActivity(intent);
            }
        });

        buttonBM = (Button)findViewById(R.id.buttonBM);
        buttonBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeBMActivity.class);
                startActivity(intent);
            }
        });

        buttonRM = (Button)findViewById(R.id.buttonRM);
        buttonRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeRMActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout = (Button)findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //panggil fungsi logout
                logoutConfirmation();
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
