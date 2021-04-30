package com.google.firebase.codelab.labelScannerUABC.Class;

import java.io.Serializable;
import java.util.ArrayList;

public class ConsumedCalories implements Serializable {

    private int calories;
    private int fat;
    private int protein;
    private int carbs;

    private ArrayList<String> products;


    public ConsumedCalories() {
        products = new ArrayList<>();
        calories = 0;
        fat = 0;
        protein = 0;
        carbs = 0;
    }

    public void addCalories(int calories, int fat, int protein, int carbs, String productName){
        this.calories+=calories;
        this.fat+=fat;
        this.protein+=protein;
        this.carbs+=carbs;
        products.add(productName);
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarbs() {
        return carbs;
    }
}
