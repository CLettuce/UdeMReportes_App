package com.example.udemreportes_app.response;

import com.google.gson.annotations.SerializedName;

public class SolicitudResponse {

    @SerializedName("estado")
    private String estado;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("fechaRegistro")
    private String fechaRegistro;

    // Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
