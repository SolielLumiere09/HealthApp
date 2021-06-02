package com.google.firebase.codelab.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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


class WebAppInterfaceAddFood {
    Context mContext;
    String userId;

    public void setConsumedCalories(ConsumedCalories consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    private ConsumedCalories consumedCalories;

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    private ArrayList<FoodItem> foodItems;



    /** Instantiate the interface and set the context */
    WebAppInterfaceAddFood(Context c) {
        mContext = c;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public String getUserId(){

        return userId;
    }

    @JavascriptInterface
    public void addFood(String productName){

        for(FoodItem item : foodItems){

            if(item.getProduct_name().equals(productName)){
                consumedCalories.addCalories(item);
                CaloriesLoader.writeConsumedCalories(mContext, consumedCalories);
                break;
            }
        }

        Toast.makeText(mContext, productName, Toast.LENGTH_LONG).show();
    }
}

public class AddFoodActivity extends AppCompatActivity {
    private String URL_GET_PRODUCTS = "http://conisoft.org/HealthAppV2/getProducts.php";
    private ProgressDialog progressDialog;
    private JsonParser parser;
    private ArrayList<FoodItem> foodItems;
    private User user;
    private SharedPreferences preferences;
    private ConsumedCalories consumedCalories;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();
        foodItems = new ArrayList<>();


        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());

        webView = findViewById(R.id.afa_webView);
        WebAppInterfaceAddFood webAppInterfaceAddFood = new WebAppInterfaceAddFood(this);
        webAppInterfaceAddFood.setUserId(user.getId());

        webView.loadUrl("http://health-app.conisoft.org/html/addProducts.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(webAppInterfaceAddFood, "Android");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        getProducts();

        webAppInterfaceAddFood.setFoodItems(foodItems);
        webAppInterfaceAddFood.setConsumedCalories(consumedCalories);
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