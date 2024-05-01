package com.example.udemreportes_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.udemreportes_app.network.ApiInterface;
import com.example.udemreportes_app.response.LoginResponse;
import com.example.udemreportes_app.response.LoginRequest;
import retrofit2.Call;
import androidx.annotation.NonNull;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://localhost:5197/api/"; // Reemplaza con la URL de tu API
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Button loginButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(v -> realizarSolicitudLogin());
    }

    private void realizarSolicitudLogin() {
        LoginRequest loginRequest = new LoginRequest("nombre_usuario", "contraseña");

        Call<LoginResponse> call = apiInterface.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Procesar la respuesta exitosa
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String message = loginResponse.getMessage();
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Procesar la respuesta de error
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                // Manejar errores de conexión
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}