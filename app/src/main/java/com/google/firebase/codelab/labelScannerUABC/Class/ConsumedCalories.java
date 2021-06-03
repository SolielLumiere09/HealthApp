package com.google.firebase.codelab.labelScannerUABC.Class;

import java.io.Serializable;
import java.util.ArrayList;

public class ConsumedCalories implements Serializable {

    private int calories;
    private int fat;
    private int protein;
    private int carbs;

    private ArrayList<FoodItem> products;


    public ConsumedCalories() {
        products = new ArrayList<>();
        calories = 0;
        fat = 0;
        protein = 0;
        carbs = 0;
    }

    public void addCalories(FoodItem product){
        this.calories+=product.getCalories();
        this.fat+=product.getTotalFat();
        this.protein+=product.getProtein();
        this.carbs+=product.getCarbs();
        products.add(product);
    }

    public void removeCalories(FoodItem product){
        this.calories-=product.getCalories();
        this.fat-=product.getTotalFat();
        this.protein-=product.getProtein();
        this.carbs-=product.getCarbs();
        products.remove(product);
    }

    public ArrayList<FoodItem> getProducts() {
        return products;
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
