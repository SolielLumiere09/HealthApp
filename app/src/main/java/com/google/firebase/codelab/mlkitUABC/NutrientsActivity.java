package com.google.firebase.codelab.mlkitUABC;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.codelab.UI.RegisterActivity;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.labelScannerUABC.DataEntryActivity;
import com.google.firebase.codelab.labelScannerUABC.MainMenuActivity;
import com.google.firebase.codelab.labelScannerUABC.R;
import com.google.firebase.codelab.labelScannerUABC.databinding.ActivityNutrientsBinding;
import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.Porciones;
import com.google.firebase.codelab.labelScannerUABC.TipoPorcion;
import com.google.firebase.codelab.textExtractor.BarcodeAnalyzer.JsonParser;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.view.View.GONE;

public class NutrientsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String URL = "http://conisoft.org/HealthAppV2/insertProduct.php";
    private ActivityNutrientsBinding binding;
    private FoodItem foodItem;
    private ProgressDialog progressDialog;

    private final String cantidadBajaString = "Este alimento contiene una cantidad baja de ";
    private final String cantidadRegularString = "Este alimento contiene una cantidad regular de ";
    private final String cantidadAltaString = "Este alimento contiene una cantidad alta de ";
    private final String [] nutrimentNotes = new String[]{cantidadBajaString, cantidadRegularString, cantidadAltaString};
    private int [] statusColors = new int[]{Color.GREEN, Color.YELLOW, Color.RED};
    private int [] proteinStatusColors = new int[]{Color.rgb(152, 251, 152), Color.rgb(0, 250, 14), Color.rgb(0, 255, 0)};


    private SharedPreferences preferences;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodItem = new FoodItem();
        boolean ableToSend = false;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            foodItem = (FoodItem) extras.getSerializable("foodItem");
            ableToSend = extras.getBoolean("ableToSend");
        }

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        id = preferences.getString(SharedPreference.KeyId,null);

        binding = ActivityNutrientsBinding.inflate(getLayoutInflater());
        setContentView(binding.root3);

        if(ableToSend){
            binding.acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerProduct();
                }
            });
        }

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowLabelInfoActivity.class);
                intent.putExtra("fooditem", foodItem);

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        setBindingStatus();
    }

    private void showProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    private void registerProduct(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.hide();
                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(), "Insertado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{

                    Toast.makeText(getApplicationContext(), "No se pudo insertar" + response, Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE_SQL", response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Log.d("STATUS_REQUEST", "ERROR ");

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parametros = new HashMap<>();


                parametros.put("id", id);
                parametros.put("barcode", JsonParser.url);
                parametros.put("Nombre", foodItem.getProduct_name());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        showProgress();

    }

    @Override
    public void onResume(){
        super.onResume();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            foodItem = (FoodItem) extras.getSerializable("foodItem");


        }

    }


    @SuppressLint("SetTextI18n")
    private void setBindingStatus(){

        float servingSize = foodItem.getPortion_size();
        float sodiumPerDay = 2300;
        String productName = foodItem.getProduct_name();
        int fatPercentage = (int) getNutrimentPercentage(foodItem.getTotalFat(), servingSize);
        int carbsPercentage = (int)getNutrimentPercentage(foodItem.getCarbs(), servingSize);
        int sugarPercentage = (int)getNutrimentPercentage(foodItem.getSugar(), servingSize);
        int proteinPercentage = (int)getNutrimentPercentage(foodItem.getProtein(), servingSize);
        float sodiumPercentage = getNutrimentPercentage(foodItem.getSodium(), sodiumPerDay);

        //Set de calorias en base a los etiquetados de aliments

        //Mostramos calorias
        binding.caloriasAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getCalories()));

        //Un producto que por cada 100g contiene 275kcal o mas es alto en calorias
        //Un producto que por cada 100ml contiene 70kcal o mas es alto en calorias
        //Un producto que por cada 100g no supera las 40 kcal es bajo en calorias
        //Un producto que por cada 100ml no supera las 20kcal es bajo en calorias

        binding.nombreAlimento.setText(productName);
        //Si el producto se mide en gramos
        if(foodItem.getMeasurementUnit() == "g") {
            //Calculamos las calorias por cada 100g para categorizar
            int caloriesPer100g = (int) ((foodItem.getCalories()*100) / foodItem.getPortion_size());
            Log.d("CALORIAS", caloriesPer100g+"");
            //Si el product excede o es equivalente a 275 calorias
            if(caloriesPer100g >= 275) {
                binding.caloriasNotes.setText("Este producto tiene una cantidad alta de calorías.");
                binding.caloriasPercentage.setText("ALTO");
                setProgressBarStatus(binding.caloriasBar, 275,275);
            //Si el producto excede o es equivalente a 40 calorias
            } else if(caloriesPer100g >= 40) {
                binding.caloriasNotes.setText("Este producto tiene una cantidad regular de calorías.");
                binding.caloriasPercentage.setText("REGULAR");
                setProgressBarStatus(binding.caloriasBar, caloriesPer100g,275);
            //Si el producto no califica en las anteriores es bajo en calorias
            } else {
                binding.caloriasNotes.setText("Este producto tiene una cantidad baja de calorías.");
                binding.caloriasPercentage.setText("Baja");
                setProgressBarStatus(binding.caloriasBar, caloriesPer100g,275);
            }
        } else if(foodItem.getMeasurementUnit() == "ml") {
            //Calculamos las calorias por cada 100ml para categorizar
            int caloriesPer100ml = (int) ((foodItem.getCalories()*foodItem.getPortion_size()) / 100);
            //Si el product excede o es equivalente a 70 calorias
            if(caloriesPer100ml >= 275) {
                binding.caloriasNotes.setText("Este producto tiene una cantidad alta de calorías.");
                binding.caloriasPercentage.setText("ALTO");
                setProgressBarStatus(binding.caloriasBar, 70,70);
                //Si el producto excede o es equivalente a 20 calorias
            } else if(caloriesPer100ml >= 20) {
                binding.caloriasNotes.setText("Este producto tiene una cantidad regular de calorías.");
                binding.caloriasPercentage.setText("REGULAR");
                setProgressBarStatus(binding.caloriasBar, caloriesPer100ml,275);
                //Si el producto no califica en las anteriores es bajo en calorias
            } else {
                binding.caloriasNotes.setText("Este producto tiene una cantidad baja de calorías.");
                binding.caloriasPercentage.setText("Baja");
                setProgressBarStatus(binding.caloriasBar, caloriesPer100ml,275);
            }
        }

        binding.lipidosNotes.setText(nutrimentNotes[getNutrimentStatus(fatPercentage, 20)]+"grasa.");
        binding.carbsNotes.setText(nutrimentNotes[getNutrimentStatus(carbsPercentage, 49)]+"carbohidratos.");
        binding.azucarNotes.setText(nutrimentNotes[getNutrimentStatus(sugarPercentage, 10)]+"azucar.");
        binding.proteinaNotes.setText(nutrimentNotes[getNutrimentStatus(proteinPercentage, 14)]+"proteina.");
        binding.sodioNotes.setText(nutrimentNotes[getNutrimentStatus(sodiumPercentage, 20)]+"sodio.");


        binding.lipidosPercentage.setText(String.format(Locale.getDefault(), "%d",fatPercentage) + "%");
        binding.carbsPercentage.setText(String.format(Locale.getDefault(), "%d",carbsPercentage) + "%");
        binding.azucarPercentage.setText(String.format(Locale.getDefault(), "%d",sugarPercentage) + "%");
        binding.proteinaPercentage.setText(String.format(Locale.getDefault(), "%d",proteinPercentage) + "%");
        binding.sodioPercentage.setText(String.format(Locale.getDefault(), "%d", (int)Math.ceil(sodiumPercentage)) + "%");

        setProgressBarStatus(binding.lipidosBar, fatPercentage, 20);
        setProgressBarStatus(binding.carbsBar, carbsPercentage, 49);
        setProgressBarStatus(binding.azucarBar, sugarPercentage, 10);
        setProgressBarStatus(binding.sodioBar, (int) sodiumPercentage, 20);
        binding.proteinaBar.setProgressBarColor(proteinStatusColors[getNutrimentStatus(proteinPercentage, 14)]);
        binding.proteinaBar.setProgress(proteinPercentage);


        binding.lipidosAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getTotalFat()));
        binding.carbsAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getCarbs()));
        binding.azucarAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getSugar()));
        binding.proteinaAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getProtein()));
        binding.sodioAmount.setText(String.format(Locale.getDefault(), "%d", (int)foodItem.getSodium()));





    }

    private void setProgressBarStatus(CircularProgressBar nutrientBar, int percentage, int limit){
        nutrientBar.setProgress(percentage);
        nutrientBar.setProgressBarColor(statusColors[getNutrimentStatus(percentage, limit)]);
    }


    //recibe gramos de los nutrientes y el tamaño de la porcion y retorna el porcentaje
    private float getNutrimentPercentage(float nutrientG, float serving){


        return (nutrientG / serving) * 100;
    }

    private int getNutrimentStatus(float percentage, float limit){
        // 0 bajo
        // 1 moderado
        // 2 alto
        Log.d("FOOD_ITEM", percentage + "<---" + " | " + limit + "<--");
        if(percentage >= limit){
            return 2;
        }
        else if(percentage >= limit / 2){
            return 1;
        }
        else{
            return 0;
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
