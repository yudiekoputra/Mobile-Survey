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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context = this;
    private static final String TAG = "LoginActivity";
    private DatabaseReference mDatabase;
    private EditText nip, password;
    private CheckBox rememberme;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;
    private RequestAPIServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiServices = APIUtilities.getAPIServices();

        //memanggil fungsi
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        nip = findViewById(R.id.nip);
        password = findViewById(R.id.password);
        rememberme = findViewById(R.id.rememberme);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        checkRememberMe();
    }

    private void signIn(){
        Log.d(TAG, "signIn");
        if (!validateForm()){
            return;
        }

        String nipValue = nip.getText().toString();
        String pwdValue = password.getText().toString();

        mAuth.signInWithEmailAndPassword(nipValue, pwdValue)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:"+task.isSuccessful());

                        if (task.isSuccessful()){
                            onAuthSuccess(task.getResult().getUser());
                        }else{
                            Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            //save data login
            SessionManager.saveDataLogin(context, nipValue, pwdValue, rememberme.isChecked());

            //save login flag
            SessionManager.saveLoginFlag(context, true);
    }

    public void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());
        System.out.println(username);
        SessionManager.saveUsername(context, username);

        writeNewAdmin(user.getUid(), username, user.getEmail());

        if (username.equalsIgnoreCase("cmo")){
            Intent intent = new Intent(context, HomeCMOActivity.class);
            startActivity(intent);
            finish();
        }else if(username.equalsIgnoreCase("bm")){
            Intent intent = new Intent(context, HomeBMActivity.class);
            startActivity(intent);
            finish();
        }else if (username.equalsIgnoreCase("rm")){
            Intent intent = new Intent(context, HomeRMActivity.class);
            startActivity(intent);
            finish();
        }

    }

    //fungsi mengambil nama di depan @ email
    private String usernameFromEmail(String email){
        if(email.contains("@")){
            return email.split("@")[0];
        }else{
            return email;
        }
    }

    private boolean validateForm(){
        boolean result = true;
        if(TextUtils.isEmpty(nip.getText().toString())){
            nip.setError("Required");
            result = false;
        }else {
            nip.setError(null);
        }
        if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required");
            result = false;
        }else {
            password.setError(null);
        }

        return result;
    }

    // menulis ke Database
    private void writeNewAdmin(String userId, String name, String nip) {
        Admin admin = new Admin(name, nip);

        mDatabase.child("admins").child(userId).setValue(admin);
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
//            requestLogin();
        }
    }

    private void requestLogin(){
//        mApiService.
    }
}

//    private void validasiInput(){
//        String NIPValue = nip.getText().toString();
//        String pwdValue = password.getText().toString();
//
//        if(NIPValue.length()==0 || NIPValue.equalsIgnoreCase("")){
//            Toast.makeText(context,"Anda belum mengisi NIP", Toast.LENGTH_SHORT).show();
//        }else if(pwdValue.length()==0 || pwdValue.equalsIgnoreCase("")){
//            Toast.makeText(context, "Anda belum mengisi password", Toast.LENGTH_SHORT).show();
//        }else{
//            //check remember me atau tidak
//            if(rememberme.isChecked()){
//                System.out.println("Remember Me");
//
//                //simpan flag remember beserta username & password ke
//            }else{
//                System.out.println("Tanpa Remember Me");
//            }
//
//            //save data login
//            SessionManager.saveDataLogin(context, NIPValue, pwdValue, rememberme.isChecked());
//
//            //save login flag
//            SessionManager.saveLoginFlag(context, true);
//
//            //login now
//            Intent intent = new Intent(context, HomeMenuActivity.class);
//
//            //bawa NIP
//            intent.putExtra(Constanta.ID_EXTRA_NIP, NIPValue);
//
//            startActivity(intent);
//            finish();
//        }
//
//    }