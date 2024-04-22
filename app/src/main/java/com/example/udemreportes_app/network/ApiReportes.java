package com.example.udemreportes_app.network;
import com.example.udemreportes_app.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiReportes {
    @FormUrlEncoded
    @POST("api/CallAndroid/VerificarUsuario")
    Call<ApiResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
}
