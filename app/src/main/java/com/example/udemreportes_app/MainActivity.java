package com.example.udemreportes_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "clopez";
        String password = "123";

        LoginRequest loginRequest = new LoginRequest(username, password);
        MyApplication.getApiService().login(loginRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Manejar la respuesta exitosa
                    Log.d(TAG, "Inicio de sesión exitoso");
                } else {
                    // Manejar la respuesta de error
                    Log.e(TAG, "Error en el inicio de sesión: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Manejar el fallo de la solicitud
                Log.e(TAG, "Error al realizar la solicitud: " + t.getMessage());
            }
        });
    }
}