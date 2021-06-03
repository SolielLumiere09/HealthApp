package com.google.firebase.codelab.labelScannerUABC;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.codelab.labelScannerUABC.Class.CaloriesLoader;
import com.google.firebase.codelab.labelScannerUABC.Class.ConsumedCalories;
import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class AddCustomProductActivity extends AppCompatActivity {

    private WebView webView;
    private WebAppInterface webAppInterface;
    private User user;
    private SharedPreferences preferences;
    private ConsumedCalories consumedCalories;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_product);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();
        consumedCalories = CaloriesLoader.readConsumedCalories(getApplicationContext());


        webAppInterface = new WebAppInterface(this);
        webAppInterface.setConsumedCalories(consumedCalories);


        webView = findViewById(R.id.acpa_webview);
        webView.loadUrl("file:////android_asset/WebViewContent/html/registerCustomProduct.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.addJavascriptInterface(webAppInterface, "Android");

    }

    class WebAppInterface {
        Context mContext;
        GsonBuilder gsonBuilder;
        Gson gson;

        public void setConsumedCalories(ConsumedCalories consumedCalories) {
            this.consumedCalories = consumedCalories;
        }

        private ConsumedCalories consumedCalories;


        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();

        }

        @JavascriptInterface
        public void showToast(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }


        @JavascriptInterface
        public void addFoodItem(String name, String calorias, String grasasTotales, String carbs, String proteinas){
            FoodItem item = new FoodItem(name, Float.parseFloat(calorias), Float.parseFloat(grasasTotales), Float.parseFloat(carbs), Float.parseFloat(proteinas));

            consumedCalories.addCalories(item);
            CaloriesLoader.writeConsumedCalories(mContext, consumedCalories);

            Toast.makeText(mContext, item.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private User getUser(){

        String id = preferences.getString(SharedPreference.KeyId, null);
        String name = preferences.getString(SharedPreference.KeyName, null);
        String lastname = preferences.getString(SharedPreference.KeyLastname, null);
        String email = preferences.getString(SharedPreference.KeyEmail, null);
        String pass = preferences.getString(SharedPreference.KeyPassword, null);

        return new User(id, name, lastname ,email, pass);
    }


}