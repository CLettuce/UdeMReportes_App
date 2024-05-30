package com.example.udemreportes_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udemreportes_app.adapter.SolicitudesAdapter;
import com.example.udemreportes_app.network.APIService;
import com.example.udemreportes_app.network.ApiClient;
import com.example.udemreportes_app.response.SolicitudResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistorialFragment extends Fragment {



    public HistorialFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private SolicitudesAdapter solicitudesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        recyclerView = view.findViewById(R.id.rv_mostarApi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        solicitudesAdapter = new SolicitudesAdapter();
        recyclerView.setAdapter(solicitudesAdapter);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String nombrePersona = sharedPreferences.getString("username", "default_user");

        if (!nombrePersona.isEmpty()) {
            obtenerSolicitudesPorUsuario(nombrePersona);
        } else {
            Log.e("HistorialFragment", "El nombre de usuario no está disponible");
        }
        return view;
    }

    private void obtenerSolicitudesPorUsuario(String username) {
        APIService apiService = ApiClient.getApiService();
        Call<List<SolicitudResponse>> call = apiService.obtenerSolicitudesPorUsuario(username);

        call.enqueue(new Callback<List<SolicitudResponse>>() {
            @Override
            public void onResponse(Call<List<SolicitudResponse>> call, Response<List<SolicitudResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SolicitudResponse> solicitudes = response.body();
                    for (SolicitudResponse solicitud : solicitudes) {
                        Log.d("Solicitud", "Estado: " + solicitud.getEstado() + ", Descripcion: " + solicitud.getDescripcion() + ", FechaRegistro: " + solicitud.getFechaRegistro());
                    }
                    solicitudesAdapter.setSolicitudes(solicitudes);
                } else {
                    Toast.makeText(getContext(), "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SolicitudResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}