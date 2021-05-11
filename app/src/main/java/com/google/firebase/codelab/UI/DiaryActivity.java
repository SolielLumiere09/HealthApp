package com.google.firebase.codelab.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.codelab.labelScannerUABC.Class.CaloriesLoader;
import com.google.firebase.codelab.labelScannerUABC.Class.ConsumedCalories;
import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.Class.ProductAdapter;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.labelScannerUABC.R;
import com.google.firebase.codelab.textExtractor.BarcodeAnalyzer.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiaryActivity extends AppCompatActivity {
    private String URL_GET_PERCENTAGES = "http://conisoft.org/HealthAppV2/getPercentages.php";
    private String URL_GET_PRODUCTS = "http://conisoft.org/HealthAppV2/getProducts.php";
    private User user;
    private SharedPreferences preferences;
    private TextView da_tv_dailyCalories, da_tv_consumedCalories, da_tv_remainingCalories;
    private ConsumedCalories consumedCalories;

    private int dailyCalories;
    private int dailyFat;
    private int dailyCarbs;
    private int dailyProtein;

    private RecyclerView da_tv_recyclerView;

    private ArrayList<FoodItem> foodItems;
    JsonParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();

        foodItems = new ArrayList<>();

        da_tv_consumedCalories = findViewById(R.id.da_tv_consumedCalories);
        da_tv_dailyCalories = findViewById(R.id.da_tv_dailyCalories);
        da_tv_remainingCalories = findViewById(R.id.da_tv_remainingCalories);
        da_tv_recyclerView = findViewById(R.id.da_tv_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        da_tv_recyclerView.setLayoutManager(linearLayoutManager);

        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());
        if(consumedCalories == null){
            consumedCalories = new ConsumedCalories();
            CaloriesLoader.writeConsumedCalories(getApplicationContext(), consumedCalories);
        }
        getPercentages();
        getProducts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        CaloriesLoader.writeConsumedCalories(getApplicationContext(), consumedCalories);
    }

    private void setAdapter(){
        ProductAdapter productAdapter = new ProductAdapter(foodItems, getApplicationContext(), consumedCalories);
        da_tv_recyclerView.setAdapter(productAdapter);
    }

    private User getUser(){

        String id = preferences.getString(SharedPreference.KeyId, null);
        String name = preferences.getString(SharedPreference.KeyName, null);
        String lastname = preferences.getString(SharedPreference.KeyLastname, null);
        String email = preferences.getString(SharedPreference.KeyEmail, null);
        String pass = preferences.getString(SharedPreference.KeyPassword, null);

        return new User(id, name, lastname ,email, pass);
    }
    private void getProducts(){

        StringRequest request = new StringRequest(Request.Method.POST, URL_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("0")) {

                    try {

                        response = response.replace("[", "");
                        response = response.replace("]", "");

                        String jsonHeader = addJsonHeader(response, "products");
                        JSONObject jsonObj = new JSONObject(jsonHeader);
                        JSONArray products = jsonObj.getJSONArray("products");


                        for(int i = 0; i < products.length(); i++){
                            JSONObject product = products.getJSONObject(i);
                            parser = new JsonParser();

                            parser.setCompleteUrl(product.getString("Barcode"));
                            parser.start();

                            try {
                                parser.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            foodItems.add(new FoodItem(
                                    product.getString("Nombre"),
                                    parser.getLabel_data()[parser.CALORIAS],
                                    parser.getLabel_data()[parser.GRASAS_TOTALES],
                                    parser.getLabel_data()[parser.CARBOHIDRATOS],
                                    parser.getLabel_data()[parser.PROTEINAS]
                                    ));
                        }

                        Log.d("FOOD_ITEMS", foodItems.toString());
                        setAdapter();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.errorUser, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {             //parametros que se envian con metodo POST
                Map<String,String> parametros = new HashMap<>();
                parametros.put("id", user.getId());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private String addJsonHeader(String response, String header){

        return "{ \""+ header + "\":[" + response + "] }";
    }

    private void getPercentages(){

        StringRequest request = new StringRequest(Request.Method.POST, URL_GET_PERCENTAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("0")) {

                    response = response.replace("[", "");
                    response = response.replace("]", "");

                    try {

                        JSONObject nutrients = new JSONObject(response);
                        dailyCalories = Integer.parseInt((String) nutrients.get("dailyCalories"));
                        dailyFat = Integer.parseInt((String) nutrients.get("dailyFat"));
                        dailyCarbs = Integer.parseInt((String) nutrients.get("dailyCarbs"));
                        dailyProtein = Integer.parseInt ((String) nutrients.get("dailyProtein"));

                        da_tv_dailyCalories.setText(String.valueOf(dailyCalories));
                        da_tv_consumedCalories.setText(String.valueOf(consumedCalories.getCalories()));
                        da_tv_remainingCalories.setText(String.valueOf(dailyCalories - consumedCalories.getCalories()));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.errorUser, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }


        }){
            @Override
            protected Map<String, String> getParams() {             //parametros que se envian con metodo POST
                Map<String,String> parametros = new HashMap<>();
                parametros.put("id", user.getId());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void onAddFood(){

    }

    public void updateDiaryTextViews(){
        da_tv_consumedCalories.setText(String.valueOf(consumedCalories.getCalories()));
        da_tv_remainingCalories.setText(String.valueOf(dailyCalories - consumedCalories.getCalories()));
    }
}