package com.heissen.tpinmobiliaria.models;

import java.io.Serializable;
import java.util.Objects;

public class Propietario implements Serializable {


    private int id;
    private String apellido;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    private String clave;

    private int avatar;

    public Propietario() {
    }

    public Propietario(int id, String apellido, String nombre, String dni, String telefono, String correo, String clave, int avatar) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.avatar = avatar;
    }



    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return apellido + ' ' + nombre;
    }
}
