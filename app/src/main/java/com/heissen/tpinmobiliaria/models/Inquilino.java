package com.heissen.tpinmobiliaria.models;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int id;
    private String apellido;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
/*    private String Garante;
    private String TelGarante;*/
    private boolean estado;

    public Inquilino() {
    }

  /*  public Inquilino(int id, String apellido, String nombre, String dni, String telefono, String correo, String garante, String telGarante, boolean estado) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        Garante = garante;
        TelGarante = telGarante;
        this.estado = estado;
    }
*/
/*    public String getGarante() {
        return Garante;
    }

    public void setGarante(String garante) {
        Garante = garante;
    }

    public String getTelGarante() {
        return TelGarante;
    }

    public void setTelGarante(String telGarante) {
        TelGarante = telGarante;
    }*/

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return apellido + ' ' +
                 nombre ;
    }
}