package com.bcafinance.itdp.mobilesurvey.HomeMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.LoginActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.fragmentBM.HomeFragmentBM;
import com.bcafinance.itdp.mobilesurvey.fragmentBM.ProfileFragmentBM;
import com.bcafinance.itdp.mobilesurvey.fragmentRM.HomeFragmentRM;
import com.bcafinance.itdp.mobilesurvey.fragmentRM.ProfileFragmentRM;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.HistoryCMOActivity;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.InputSurveyActivity;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.ProfileActivity;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class HomeRMActivity extends AppCompatActivity {
        private Context context = this;
        private TextView username;
        private ImageButton imageLogOut;
        private int counter_back = 1;
        private CardView buttonInputForm, buttonHistory, buttonProfile;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_rm);

            username = findViewById(R.id.username);
//            Bundle extra = getIntent().getExtras();
            String text = SessionManager.getPosition(context);
            username.setText(text);


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
                    finish();
                }
            });

            buttonProfile = findViewById(R.id.buttonProfile);
            buttonProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    startActivity(intent);
                finish();
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
