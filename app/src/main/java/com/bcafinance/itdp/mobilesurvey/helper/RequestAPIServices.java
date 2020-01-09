package com.bcafinance.itdp.mobilesurvey.helper;

import com.bcafinance.itdp.mobilesurvey.ResponseLogin;
import com.bcafinance.itdp.mobilesurvey.helper.AddSurvey.AddSurvey;
import com.bcafinance.itdp.mobilesurvey.helper.EditSurvey.EditSurvey;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestAPIServices {
    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin>loginRequest(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("grant_type")String grant_type);

    @POST("API/AddKonsumen")
    Call<AddKonsumen> addKonsumen(@Header("Authorization") String token,
                                 @Body AddKonsumen body);

    @GET("API/getHistoryKonsumen")
    Call<HistoryKonsumen>historyKonsumen(@Header("Authorization") String token);

    @POST("/API/AddSurvey")
    Call<AddSurvey> addSurvey(@Header("Authorization") String token,
                              @Body AddSurvey body);

    @POST("/API/EditSurvey")
    Call<EditSurvey> editSurvey(@Header("Authorization") String token,
                              @Body EditSurvey body);
}
