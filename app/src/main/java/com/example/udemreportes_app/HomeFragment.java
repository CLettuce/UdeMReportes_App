package com.example.udemreportes_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.udemreportes_app.network.ApiClient;
import com.example.udemreportes_app.network.APIService;
import com.example.udemreportes_app.response.SolicitudRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Spinner spinnerUbicacion = view.findViewById(R.id.cbUbicacion);
        Spinner spinnerCarrera = view.findViewById(R.id.cbCarrera);
        EditText txtDescripcion = view.findViewById(R.id.txtDescripcion);
        Button btnEnviar = view.findViewById(R.id.btnEnviar);

        String[] ubicaciones = {"LAB-01", "LAB-02", "AU-01", "AU-02"};
        String[] carreras = {"Ingenieria en Sistemas", "Ingenieria Industrial", "Periodismo", "Derecho"};

        ArrayAdapter<String> adapterUbicacion = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ubicaciones);
        adapterUbicacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicacion.setAdapter(adapterUbicacion);

        ArrayAdapter<String> adapterCarrera = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carreras);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapterCarrera);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ubicacionSeleccionada = spinnerUbicacion.getSelectedItem().toString();
                String carreraSeleccionada = spinnerCarrera.getSelectedItem().toString();
                String descripcion = txtDescripcion.getText().toString();

                SolicitudRequest solicitudRequest = new SolicitudRequest();
                solicitudRequest.setNombrePersona("Carlos");
                solicitudRequest.setCarrera(carreraSeleccionada);
                solicitudRequest.setDescripcion(descripcion);
                solicitudRequest.setUbicacionPeticion(ubicacionSeleccionada);

                enviarSolicitud(solicitudRequest);
            }
        });

        return view;
    }

    private void enviarSolicitud(@NonNull SolicitudRequest solicitud) {
        APIService apiService = ApiClient.getApiService();

        Call<ResponseBody> call = apiService.insertarSolicitud(solicitud);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    showSuccessDialog("Solicitud enviada con éxito");
                } else {
                    showErrorDialog("Error al insertar la solicitud");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                showErrorDialog("Error de red: " + t.getMessage());
            }
        });
    }

    private void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Éxito");
        builder.setMessage(message);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Aquí puedes agregar alguna acción si es necesario
            }
        });
        builder.show();
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Aquí puedes agregar alguna acción si es necesario
            }
        });
        builder.show();
    }
}