package com.bcafinance.itdp.mobilesurvey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeBMActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeRMActivity;
import com.bcafinance.itdp.mobilesurvey.helper.APIUtilities;
import com.bcafinance.itdp.mobilesurvey.helper.RequestAPIServices;
import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context = this;
    private static final String TAG = "LoginActivity";
    private EditText nip, password;
    private CheckBox rememberme;
    private Button buttonLogin;
    RequestAPIServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiServices = APIUtilities.getAPIServices();

        nip = findViewById(R.id.nip);
        password = findViewById(R.id.password);
        rememberme = findViewById(R.id.rememberme);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        checkRememberMe();
    }

    private void signIn(){
        String usernameValue = nip.getText().toString();
        String pwdValue = password.getText().toString();
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);

        apiServices.loginRequest(nip.getText().toString(), password.getText().toString(), "password")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        loading.dismiss();
                        if (response.code()==200) {
                            Intent intent = new Intent(context, HomeCMOActivity.class);
                                        startActivity(intent);
                        }else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
            //save data login
            SessionManager.saveDataLogin(context, usernameValue, pwdValue, rememberme.isChecked());

            //save login flag
            SessionManager.saveLoginFlag(context, true);
    }

    private void checkRememberMe(){
        if(SessionManager.getRemember(context)){
            //check remember me dipilih
            nip.setText(SessionManager.getNIP(context));
            password.setText(SessionManager.getPassword(context));

            rememberme.setChecked(true);
        }else{
            //none
        }
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
        loading.show();

        int i = v.getId();
        if(i == R.id.buttonLogin){
            signIn();
        }
    }
}