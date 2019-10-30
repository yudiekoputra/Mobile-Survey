package com.bcafinance.itdp.mobilesurvey.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestAPIServices {
//contoh dari BCAITDP
    //deklarasikan semua mthod yang berhubungan dengan API request

    //untuk memanggil list user
//    @GET("users")
//    Call<ListUserModel> getListUser(@Query("page")int page);
    @FormUrlEncoded
    @POST("api/Users/Mobile")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    //ini untuk create user baru
//    @FormUrlEncoded
//    @POST("users")
//    Call<CreateUser> createUser(@Field("name") String name, @Field("job") String job);

}
