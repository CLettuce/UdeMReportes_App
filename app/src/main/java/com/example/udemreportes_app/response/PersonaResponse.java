package com.example.udemreportes_app.response;

import com.google.gson.annotations.SerializedName;

public class PersonaResponse {
    @SerializedName("nombreCompleto")
    private String nombreCompleto;

    @SerializedName("username")
    private String username;

    @SerializedName("correoElectronico")
    private String correoElectronico;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    @SerializedName("numeroIdentificacion")
    private String numeroIdentificacion;


}
