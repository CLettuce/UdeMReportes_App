package com.example.udemreportes_app.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udemreportes_app.R;
import com.example.udemreportes_app.response.SolicitudResponse;

import java.util.ArrayList;
import java.util.List;
public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.SolicitudViewHolder> {

    private List<SolicitudResponse> solicitudes = new ArrayList<>();

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitud, parent, false);
        return new SolicitudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudViewHolder holder, int position) {
        SolicitudResponse solicitud = solicitudes.get(position);
        if (solicitud != null) {
            holder.txtEstado.setText(solicitud.getEstado() != null ? solicitud.getEstado() : "Estado no disponible");
            holder.txtDescripcion.setText(solicitud.getDescripcion() != null ? solicitud.getDescripcion() : "Descripción no disponible");
        }
    }
    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    public void setSolicitudes(List<SolicitudResponse> solicitudes) {
        this.solicitudes = solicitudes;
        notifyDataSetChanged();
    }

    static class SolicitudViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView txtEstado;
        TextView txtDescripcion;

        public SolicitudViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcionReporte);
        }
    }
}
