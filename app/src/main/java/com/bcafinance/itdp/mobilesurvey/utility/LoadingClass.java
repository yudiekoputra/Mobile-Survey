package com.bcafinance.itdp.mobilesurvey.utility;

import android.app.ProgressDialog;
import android.content.Context;

import com.bcafinance.itdp.mobilesurvey.R;

public class LoadingClass {
    public static ProgressDialog loadingAnimationCustom(Context context){
        ProgressDialog loading = new ProgressDialog(context, R.style.CustomLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);

        return loading;
    };
}
