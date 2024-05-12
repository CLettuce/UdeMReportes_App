package com.example.udemreportes_app.response;

public class SolicitudRequest {
    private String nombrePersona;
    private String carrera;
    private String descripcion;
    private String ubicacionPeticion;

    public SolicitudRequest() {
        // Constructor vacío necesario para la deserialización
    }

    public SolicitudRequest(String nombrePersona, String carrera, String descripcion, String ubicacionPeticion) {
        this.nombrePersona = nombrePersona;
        this.carrera = carrera;
        this.descripcion = descripcion;
        this.ubicacionPeticion = ubicacionPeticion;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacionPeticion() {
        return ubicacionPeticion;
    }

    public void setUbicacionPeticion(String ubicacionPeticion) {
        this.ubicacionPeticion = ubicacionPeticion;
    }
}
