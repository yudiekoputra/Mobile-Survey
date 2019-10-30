package com.bcafinance.itdp.mobilesurvey.fragmentBM;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bcafinance.itdp.mobilesurvey.LoginActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;

public class ProfileFragmentBM extends Fragment {
    private Button buttonLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_bm, container, false);

        buttonLogout = (Button)view.findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //panggil fungsi logout
                logoutConfirmation();
//                Intent intent = new Intent(context, LoginActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });
        return view;
    }

    private void logoutConfirmation(){
        AlertDialog.Builder option = new AlertDialog.Builder(getContext());
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
        SessionManager.saveLoginFlag(getContext(), false);

        //open login activity
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        //tutup home menu activity
        getActivity().finish();
    }
}
