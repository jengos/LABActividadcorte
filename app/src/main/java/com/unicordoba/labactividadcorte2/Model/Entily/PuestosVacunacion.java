package com.unicordoba.labactividadcorte2.Model.Entily;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class PuestosVacunacion implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("depa_nombre")
    private String depa_nombre;
    @SerializedName("muni_nombre")
    private String muni_nombre;
    @SerializedName("sede_nombre")
    private String sede_nombre;
    private String direccion;
    private String telefono;
    private String email;
    @SerializedName("naju_nombre")
    private String naju_nombre;
    @SerializedName("fecha_corte_resps")
    private String fecha_corte_resps;
    private String idPV;

    public String getDepa_nombre() {
        return depa_nombre;
    }

    public void setDepa_nombre(String depa_nombre) {
        this.depa_nombre = depa_nombre;
    }

    public String getMuni_nombre() {
        return muni_nombre;
    }

    public void setMuni_nombre(String muni_nombre) {
        this.muni_nombre = muni_nombre;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaju_nombre() {
        return naju_nombre;
    }

    public void setNaju_nombre(String naju_nombre) {
        this.naju_nombre = naju_nombre;
    }

    public String getFecha_corte_resps() {
        return fecha_corte_resps;
    }

    public void setFecha_corte_resps(String fecha_corte_resps) {
        this.fecha_corte_resps = fecha_corte_resps;
    }

    public String getIdPV() {
        return idPV;
    }

    public void setIdPV(String idPV) {
        this.idPV = idPV;
    }

    @Override
    public String toString() {
        return "{" +
                "depa_nombre='" + depa_nombre + '\'' +
                ", muni_nombre='" + muni_nombre + '\'' +
                ", sede_nombre='" + sede_nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", naju_nombre='" + naju_nombre + '\'' +
                ", fecha_corte_resp='" + fecha_corte_resps + '\'' +
                '}';
    }
   /* public PuestosVacunacion(int idPV, String depa_nombre, String muni_nombre, String sede_nombre, String direccion, String telefono, String email, String naju_nombre, String fecha_corte_resps) {
        this.depa_nombre = "";
        this.muni_nombre = "";
        this.sede_nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.email ="";
        this.naju_nombre = "";
        this.fecha_corte_resps = "";
        this.idPV="";
    }*/
    public PuestosVacunacion(int id, String depa_nombre, String muni_nombre, String sede_nombre, String direccion, String telefono, String email, String naju_nombre, String fecha_corte_resps) {
        this.id=id;
        this.depa_nombre = depa_nombre;
        this.muni_nombre = muni_nombre;
        this.sede_nombre = sede_nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.naju_nombre = naju_nombre;
        this.fecha_corte_resps = fecha_corte_resps;

    }
    public void Complementar(String it){
        this.setIdPV(UUID.randomUUID().toString());

    }

    public PuestosVacunacion() {
    }
}
