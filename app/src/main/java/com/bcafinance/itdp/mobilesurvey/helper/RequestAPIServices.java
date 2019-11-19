package com.bcafinance.itdp.mobilesurvey.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestAPIServices {
    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseBody>loginRequest(@Field("username") String username,
                                   @Field("password") String password,
                                   @Field("grant_type")String grant_type);

}
