package com.bcafinance.itdp.mobilesurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeBMActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeRMActivity;
import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //cek flag login
                if(SessionManager.cekLoginFlag(context)){
                    //sudah login
                    skipKeHomeActivity();
//                    pindahKeLoginActivity();
                }else{
                    pindahKeLoginActivity();

                }

                finish();
            }
        };

        //timer
        Timer timer = new Timer();
        timer.schedule(timerTask, Constanta.SPLASH_DELAY_TIME);
    }

    private void pindahKeLoginActivity(){
        Intent intent = new Intent(context, LoginActivity.class );
        startActivity(intent);
    }

    private void skipKeHomeActivity(){
        if (SessionManager.getNIP(context).equals("cmo@bcaf.co.id")){
            Intent intent = new Intent(context, HomeCMOActivity.class);
            startActivity(intent);
        }else if (SessionManager.getNIP(context).equals("bm@bcaf.co.id")){
            Intent intent = new Intent(context, HomeBMActivity.class);
            startActivity(intent);
        }else if (SessionManager.getNIP(context).equals("rm@bcaf.co.id")){
            Intent intent = new Intent(context, HomeRMActivity.class);
            startActivity(intent);
        }
//        Intent intent = new Intent(context, HomeMenuActivity.class );
//        startActivity(intent);
    }

}
