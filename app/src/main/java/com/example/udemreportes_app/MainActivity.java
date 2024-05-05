package com.example.udemreportes_app;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import retrofit2.Response;
import androidx.annotation.NonNull;

import com.example.udemreportes_app.network.ApiClient;
import com.example.udemreportes_app.response.LoginRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.button2);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Verificar si los campos de texto no están vacíos
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Realizar la solicitud de inicio de sesión con los valores de los campos de texto
                    LoginRequest loginRequest = new LoginRequest(username, password);
                    ApiClient.getApiService().login(loginRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                // Manejar la respuesta exitosa
                                Log.d(TAG, "Inicio de sesión exitoso");
                                // Si el inicio de sesión es exitoso, inicia MainActivity2
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                finish(); // Esto evita que el usuario pueda regresar a esta actividad presionando el botón Atrás
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
                } else {
                    // Mostrar un mensaje de error si los campos de texto están vacíos
                    Log.e(TAG, "Los campos de texto están vacíos");
                }
            }
        });
    }
}