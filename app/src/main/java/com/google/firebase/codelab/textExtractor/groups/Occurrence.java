package com.google.firebase.codelab.textExtractor.groups;

import java.io.Serializable;

public class Occurrence implements Serializable {
    private int number = -1;
    private int ocurrences = 1;

    public Occurrence(){
    }

    public void setOcurrences(int ocurrences) {
        this.ocurrences = ocurrences;
    }

    public int getNumber() {
        return number;
    }

    public int getOcurrences() {
        return ocurrences;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void incrementOcurrencies() {
        this.ocurrences++;
    }

}
