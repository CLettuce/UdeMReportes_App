package com.example.udemreportes_app.network;
import com.example.udemreportes_app.response.LoginRequest;
import com.example.udemreportes_app.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("CallAndroid/VerificarUsuario")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
