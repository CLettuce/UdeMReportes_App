package com.example.udemreportes_app.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiCliente {
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5197/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
