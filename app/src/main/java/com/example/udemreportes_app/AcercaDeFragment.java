package com.example.udemreportes_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udemreportes_app.network.APIService;
import com.example.udemreportes_app.network.ApiClient;
import com.example.udemreportes_app.response.PersonaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AcercaDeFragment extends Fragment {



    public AcercaDeFragment() {
        // Required empty public constructor
    }
    private TextView txtNombreCompleto;
    private TextView txtUsuario;
    private TextView txtCorreo;
    private TextView txtIdentificacion;
    private APIService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);

        // Referenciar los TextView del diseño XML
        txtNombreCompleto = view.findViewById(R.id.txtNombreCompleto);
        txtUsuario = view.findViewById(R.id.txtUsuario);
        txtCorreo = view.findViewById(R.id.txtCorreo);
        txtIdentificacion = view.findViewById(R.id.txtIdentificacion);

        // Inicializar el servicio API
        apiService = ApiClient.getApiService();

        // Obtener el nombre de usuario de SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String nombrePersona = sharedPreferences.getString("username", "default_user");

        if (!nombrePersona.isEmpty()) {
            obtenerInfoUsuario(nombrePersona);
        } else {
            Log.e("AcercaDeFragment", "El nombre de usuario no está disponible");
        }

        return view;
    }

    private void obtenerInfoUsuario(String username) {

        apiService.obtenerInfoUsuario(username).enqueue(new Callback<PersonaResponse>() {
            @Override
            public void onResponse(Call<PersonaResponse> call, Response<PersonaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    txtNombreCompleto.setText(response.body().getNombreCompleto());
                    txtUsuario.setText(response.body().getUsername());
                    txtCorreo.setText(response.body().getCorreoElectronico());
                    txtIdentificacion.setText(response.body().getNumeroIdentificacion());
                }else {
                    Toast.makeText(getContext(), "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}