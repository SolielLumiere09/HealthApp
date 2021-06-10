package com.google.firebase.codelab.labelScannerUABC.Class;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private int id;
    private String product_name;
    private float portion_size;
    private float portions;
    private float calories;
    private float totalFat;
    private float saturatedFat;
    private float transFat;
    private float carbs;
    private float sugar;
    private float cholesterol;
    private float sodium;
    private float protein;
    private long dateAdded;
    private long dateModified;

    public FoodItem(String name, float calories, float totalFat, float carbs, float protein){
        this.product_name = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public FoodItem(String name, float calories, float totalFat, float carbs, float protein, float sodium, float sugar, float serving_size){
        this.product_name = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.carbs = carbs;
        this.protein = protein;
        this.sodium = sodium;
        this.sugar = sugar;
        this.portion_size = serving_size;
    }

    public FoodItem(
            int id,
            String product_name,
            float portion_size,
            float portions,
            float calories,
            float totalFat,
            float saturatedfat,
            float transfat,
            float carbs,
            float sugar,
            float cholesterol,
            float sodium,
            float protein,
            long dateAdded,
            long dateModified
    ) {
        this.id = id;
        this.product_name = product_name;
        this.portion_size = portion_size;
        this.portions = portions;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedfat;
        this.transFat = transfat;
        this.carbs = carbs;
        this.sugar = sugar;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.protein = protein;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    public FoodItem(){
        this.id = -1; //valor temporal, no se almacena en la base de datos
        this.product_name = "";
        this.portion_size = 0;
        this.portions = 1;
        this.calories = 0;
        this.sodium = 0;
        this.carbs = 0;
        this.totalFat = 0;
        this.sugar = 0;
        this.transFat = 0;
        this.protein = 0;
        this.dateAdded = System.currentTimeMillis();
        this.dateModified = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "product_name='" + product_name + '\'' +
                ", calories=" + calories +
                ", totalFat=" + totalFat +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}';
    }

    public String getMeasurementUnit() {
        return "g";
    }

    //getters & setters
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getPortion_size() {
        return portion_size;
    }

    public void setPortion_size(float portion_size) {
        this.portion_size = portion_size;
    }

    public float getPortions() {
        return portions;
    }

    public void setPortions(float portions) {
        this.portions = portions;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getTransFat() {
        return transFat;
    }

    public void setTransFat(float transFat) {
        this.transFat = transFat;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

