package com.google.firebase.codelab.labelScannerUABC;

public class TipoPorcion{
    String nombrePorcion = "";
    float valorEnGramos = 0;

    public TipoPorcion(String nombrePorcion, float valorEnGramos) {
        this.nombrePorcion = nombrePorcion;
        this.valorEnGramos = valorEnGramos;
    }

    public String getNombrePorcion() {
        return nombrePorcion;
    }

    public void setNombrePorcion(String nombrePorcion) {
        this.nombrePorcion = nombrePorcion;
    }

    public float getValorEnGramos() {
        return valorEnGramos;
    }

    public void setValorEnGramos(int valorEnGramos) {
        this.valorEnGramos = valorEnGramos;
    }

    public String toString() {
        return nombrePorcion;
    }
}