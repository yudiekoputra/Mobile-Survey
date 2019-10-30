package com.bcafinance.itdp.mobilesurvey.helper;

public class APIUtilities {
    private static String BASE_URL_API = "https://192.168.29.193:9903/";

    public static RequestAPIServices getAPIServices(){
        return RetrofitClient.getClient(BASE_URL_API).create(RequestAPIServices.class);
    }
}
