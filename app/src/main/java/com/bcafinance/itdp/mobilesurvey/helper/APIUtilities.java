package com.bcafinance.itdp.mobilesurvey.helper;

public class APIUtilities {
    private static String BASE_URL_API = "http://149.129.223.144/";

    public static RequestAPIServices getAPIServices(){
        return RetrofitClient.getClient(BASE_URL_API).create(RequestAPIServices.class);
    }
}
