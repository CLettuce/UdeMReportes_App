package com.example.udemreportes_app;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("CallAndroid/VerificarUsuario")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);
}
