package com.google.firebase.codelab.labelScannerUABC.nutrimentsCalculator;


//para las calorias diarias de la PERSONA
public class Calculator {
    private int[] caloriesPerNutrient;
    private int[] gramsPerNutrient;
    private final int nonSaturatedFat = 0;
    private final int saturatedFat = 1;
    private final int carbs = 2;
    private final int sugar = 3;
    private final int protein = 4;
    private int calories;

    public Calculator(int calories){
        this.calories = calories;
        caloriesPerNutrient = new int[5];
        gramsPerNutrient = new int[5];
        calculateCaloriesPerNutrient(calories);
        calculateGramsPerNutrient();
    }

    private void calculateCaloriesPerNutrient(int calories){
        caloriesPerNutrient[nonSaturatedFat] = (int)(calories*0.2); //20%
        caloriesPerNutrient[saturatedFat] = (int)(calories*0.07); //7%
        caloriesPerNutrient[carbs] = (int)(calories*0.49); //49%
        caloriesPerNutrient[sugar] = (int)(calories*0.1); //10%
        caloriesPerNutrient[protein] = (int)(calories*0.14); //14%
        //sodio por si las moscas no va
    }

    private void calculateGramsPerNutrient(){
        gramsPerNutrient[nonSaturatedFat] = caloriesPerNutrient[nonSaturatedFat]/9;
        gramsPerNutrient[saturatedFat] = caloriesPerNutrient[saturatedFat]/9;
        gramsPerNutrient[carbs] = caloriesPerNutrient[carbs]/4;
        gramsPerNutrient[sugar] = caloriesPerNutrient[sugar]/4;
        gramsPerNutrient[protein] = caloriesPerNutrient[protein]/4;
    }

    public int remainingCalories(int caloriesThatImGonnaEat){
        calories -= caloriesThatImGonnaEat;
        return calories;
    }

    public int[] remainingCaloriesPerNutrient(int[] caloriesThatImGonnaEat){
        for(int i = 0; i < caloriesPerNutrient.length; i++){
            caloriesPerNutrient[i] -= caloriesThatImGonnaEat[i];
        }
        return caloriesPerNutrient;
    }

    public int[] remainingGramsPerNutrient(int[] caloriesThatImGonnaEat){
        for(int i = 0; i < gramsPerNutrient.length; i++){
            gramsPerNutrient[i] -= caloriesThatImGonnaEat[i];
        }
        return gramsPerNutrient;
    }

    public int[] getCaloriesPerNutrient(){
        return caloriesPerNutrient;
    }

    public int[] getGramsPerNutrient(){
        return gramsPerNutrient;
    }
}
