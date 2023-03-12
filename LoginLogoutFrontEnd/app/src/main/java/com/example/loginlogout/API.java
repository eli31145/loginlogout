package com.example.loginlogout;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//handles Requests & Responses
//API Obj will be created by Retrofit
public interface API {

    @POST("register")
    Call<ResponseBody> createUser(@Body User user);

    @POST("login")
    Call<ResponseBody> checkUser(@Body User user);

    @POST("logout")
    Call<ResponseBody> logoutUser(@Body User user);

}
