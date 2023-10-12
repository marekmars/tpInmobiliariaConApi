package com.heissen.tpinmobiliaria.models;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String lat;
    private String lon;
    private Integer uso;
    private Integer tipo;
    private Integer ambientes;
    private boolean disponible;
    private String direccion;
    private double precio;
    private String foto;
    private int propietarioId;
    private Propietario propietario;

    public Inmueble() {
    }

    public Inmueble(int id, String lat, String lon, Integer uso, Integer tipo, Integer ambientes, boolean disponible, String direccion, double precio, String foto, int propietarioId, Propietario propietario) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.disponible = disponible;
        this.direccion = direccion;
        this.precio = precio;
        this.foto = foto;
        this.propietarioId = propietarioId;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Integer getUso() {
        return uso;
    }

    public void setUso(Integer uso) {
        this.uso = uso;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(Integer ambientes) {
        this.ambientes = ambientes;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", uso=" + UsoInmueble.values()[uso] +
                ", tipo=" + TipoInmueble.values()[tipo]+
                ", ambientes=" + ambientes +
                ", disponible=" + disponible +
                ", direccion='" + direccion + '\'' +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                ", propietarioId=" + propietarioId +
                ", propietario=" + propietario +
                '}';
    }
}

