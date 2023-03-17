package com.example.loginlogout;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//handles Requests & Responses
//API Obj will be created by Retrofit
public interface API {

    @POST("/users/register")
    Call<ResponseBody> createUser(@Body User user);

    @POST("/users/login")
    Call<ResponseBody> checkUser(@Body User user);

    @POST("/users/logout")
    Call<ResponseBody> logoutUser(@Body String username);

}
