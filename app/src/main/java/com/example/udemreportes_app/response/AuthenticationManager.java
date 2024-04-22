package com.example.udemreportes_app.response;

import android.content.Context;
import android.widget.Toast;
import com.example.udemreportes_app.model.ApiResponse;
import com.example.udemreportes_app.network.ApiClient;
import com.example.udemreportes_app.network.ApiReportes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AuthenticationManager {
    private ApiReportes apiReportes;
    private Context context;

    public AuthenticationManager(Context context) {
        this.context = context;
        apiReportes = ApiClient.getClient().create(ApiReportes.class);
    }

    public void authenticateUser(String username, String password) {
        Call<ApiResponse> call = apiReportes.login(username, password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    // Manejar la respuesta exitosa aquí
                    if (apiResponse != null && apiResponse.getStatus().equals("success")) {
                        // Si la autenticación es exitosa, mostrar un mensaje al usuario
                        Toast.makeText(context, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                    } else {
                        // La respuesta del servidor indica un estado de éxito, pero no contiene un mensaje de éxito
                        Toast.makeText(context, "¡Error en la respuesta del servidor!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // La respuesta del servidor no fue exitosa (código de estado diferente a 2XX)
                    Toast.makeText(context, "¡Error en la respuesta del servidor!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Ocurrió un error al realizar la llamada
                Toast.makeText(context, "¡Error al conectar con el servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
