package com.google.firebase.codelab.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.codelab.labelScannerUABC.Class.AddProductAdapter;
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

public class AddFoodActivity extends AppCompatActivity {
    private String URL_GET_PERCENTAGES = "http://conisoft.org/HealthAppV2/getPercentages.php";
    private String URL_GET_PRODUCTS = "http://conisoft.org/HealthAppV2/getProducts.php";
    private ProgressDialog progressDialog;
    private JsonParser parser;
    private ArrayList<FoodItem> foodItems;
    private User user;
    private SharedPreferences preferences;
    private RecyclerView af_tv_recyclerView;
    private ConsumedCalories consumedCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();
        foodItems = new ArrayList<>();

        af_tv_recyclerView = findViewById(R.id.af_tv_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        af_tv_recyclerView.setLayoutManager(linearLayoutManager);

        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());

        getProducts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());
        Log.d("Comer", "AddFoodActivity onStart" + consumedCalories.getCalories());
    }

    @Override
    protected void onStop() {
        super.onStop();
        //CaloriesLoader.writeConsumedCalories(getApplicationContext(), consumedCalories);
        Log.d("Comer", "AddFoodActivity onStop" + consumedCalories.getCalories());
    }

    private void setAdapter(){
        AddProductAdapter addProductAdapter = new AddProductAdapter(foodItems, getApplicationContext(), consumedCalories);
        af_tv_recyclerView.setAdapter(addProductAdapter);
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

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        StringRequest request = new StringRequest(Request.Method.POST, URL_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();
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
}