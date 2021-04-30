package com.google.firebase.codelab.labelScannerUABC;

import java.util.ArrayList;

public class Porciones {

    public static ArrayList<TipoPorcion> getTiposDePorciones(){
        ArrayList<TipoPorcion> tiposDePorcion = new ArrayList<TipoPorcion>();
        tiposDePorcion.add(new TipoPorcion("gr",1));
        tiposDePorcion.add(new TipoPorcion("taza",250));
        tiposDePorcion.add(new TipoPorcion("cucharada",22));
        return tiposDePorcion;
    }
}
