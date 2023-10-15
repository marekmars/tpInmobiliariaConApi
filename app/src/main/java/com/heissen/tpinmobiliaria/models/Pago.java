package com.heissen.tpinmobiliaria.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pago implements Serializable {

        private int id;
        private int nroPago;

        private int idContrato;
        private LocalDateTime fechaPago;
        private double importe;
        private Contrato Contrato;

    public Pago() {
    }

    public Pago(int id, int nroPago, int idContrato, LocalDateTime fechaPago, double importe, com.heissen.tpinmobiliaria.models.Contrato contrato) {
        this.id = id;
        this.nroPago = nroPago;
        this.idContrato = idContrato;
        this.fechaPago = fechaPago;
        this.importe = importe;
        Contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public com.heissen.tpinmobiliaria.models.Contrato getContrato() {
        return Contrato;
    }

    public void setContrato(com.heissen.tpinmobiliaria.models.Contrato contrato) {
        Contrato = contrato;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "Id=" + id +
                ", nroPago=" + nroPago +
                ", idContrato=" + idContrato +
                ", fechaPago=" + fechaPago +
                ", importe=" + importe +
                ", Contrato=" + Contrato +
                '}';
    }
}
