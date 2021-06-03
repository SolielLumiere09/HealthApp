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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class DiaryActivity extends AppCompatActivity {
    private String URL_GET_PERCENTAGES = "http://conisoft.org/HealthAppV2/getPercentages.php";
    private User user;
    private SharedPreferences preferences;
    private TextView da_tv_dailyCalories, da_tv_consumedCalories, da_tv_remainingCalories;
    private ConsumedCalories consumedCalories;
    private Button addButton;
    private int dailyCalories;
    private WebView webView;
    private WebAppInterface webAppInterface;
    private Handler handler;
    private Runnable runnable;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();

        da_tv_consumedCalories = findViewById(R.id.da_tv_consumedCalories);
        da_tv_dailyCalories = findViewById(R.id.da_tv_dailyCalories);
        da_tv_remainingCalories = findViewById(R.id.da_tv_remainingCalories);
        addButton = findViewById(R.id.diaryButton);

        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());
        if(consumedCalories == null){
            consumedCalories = new ConsumedCalories();
            CaloriesLoader.writeConsumedCalories(getApplicationContext(), consumedCalories);
        }


        webAppInterface = new WebAppInterface(this);
        webAppInterface.setConsumedCalories(consumedCalories);
        webAppInterface.setFoodItems(consumedCalories.getProducts());
        webView = findViewById(R.id.da_webView);
        webView.loadUrl("file:////android_asset/WebViewContent/html/showConsumedProducts.html");


        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(webAppInterface, "Android");

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                checkForChanges();
                handler.postDelayed(this, 1000);
            }
        };

        getPercentages();



        handler.post(runnable);
    }


    public void checkForChanges(){

        Log.d("CONSUMED", "checkForChanges: " + consumedCalories.getCalories());
        Log.d("CONSUMED", "checkForChanges: " + String.valueOf(dailyCalories - consumedCalories.getCalories()));
        consumedCalories = CaloriesLoader.readConsumedCalories(this);
        da_tv_consumedCalories.setText(String.valueOf(consumedCalories.getCalories()));
        da_tv_remainingCalories.setText(String.valueOf(dailyCalories - consumedCalories.getCalories()));
    }
    class WebAppInterface {
        Context mContext;
        GsonBuilder gsonBuilder;
        Gson gson;

        public void setConsumedCalories(ConsumedCalories consumedCalories) {
            this.consumedCalories = consumedCalories;
        }

        private ConsumedCalories consumedCalories;
        public void setFoodItems(ArrayList<FoodItem> foodItems) {
            this.foodItems = foodItems;
        }

        private ArrayList<FoodItem> foodItems;


        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();


        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public String getFoodItems(){

            return gson.toJson(foodItems);
        }

        @JavascriptInterface
        public void removeItem(String name){

            for(FoodItem item : foodItems){
                if(item.getProduct_name().equals(name)){
                    consumedCalories.removeCalories(item);

                    Log.d("CONSUMED", "removeItem: " + consumedCalories.getCalories());

                    CaloriesLoader.writeConsumedCalories(mContext, consumedCalories);

                    Log.d("CONSUMED", "removeItem: " + (dailyCalories - consumedCalories.getCalories()));



                    Log.d("CONSUMED", "removeItem: " + "I'M OK!");

                    Toast.makeText(mContext, "Removed " + name, Toast.LENGTH_LONG).show();

                    //webView.reload();
                    break;
                }
            }

        }

    }

    public void startAddFoodActivity(View view){
        Intent intent = new Intent(this, AddFoodActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());
        da_tv_consumedCalories.setText(String.valueOf(consumedCalories.getCalories()));
        da_tv_remainingCalories.setText(String.valueOf(dailyCalories - consumedCalories.getCalories()));
        Log.d("Comer", "DiaryActivity onStart" + consumedCalories.getCalories());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
        //CaloriesLoader.writeConsumedCalories(getApplicationContext(), consumedCalories);
    }


    private User getUser(){

        String id = preferences.getString(SharedPreference.KeyId, null);
        String name = preferences.getString(SharedPreference.KeyName, null);
        String lastname = preferences.getString(SharedPreference.KeyLastname, null);
        String email = preferences.getString(SharedPreference.KeyEmail, null);
        String pass = preferences.getString(SharedPreference.KeyPassword, null);

        return new User(id, name, lastname ,email, pass);
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


}