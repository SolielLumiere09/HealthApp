package com.google.firebase.codelab.labelScannerUABC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.Class.ProductAdapter;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.textExtractor.BarcodeAnalyzer.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class WebAppInterface {
    Context mContext;
    String userId;


    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
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
}


public class ProductListActivity extends AppCompatActivity{

    private SharedPreferences preferences;
    private String URL = "http://conisoft.org/HealthAppV2/getProducts.php";
    private WebView webView;
    private  User user;
    private WebAppInterface webAppInterface;
    JsonParser parser;

    //private RecyclerView recyclerView;
    public ArrayList<FoodItem> foodItems;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = LoadSharedPreferences();
       // recyclerView = findViewById(R.id.recyclerView);
        foodItems = new ArrayList<>();

        webView = findViewById(R.id.webViewTest);
        webAppInterface = new WebAppInterface(this);
        webAppInterface.setUserId(user.getId());

        //webView.loadUrl("http://health-app.conisoft.org/html/showUserProducts.html");
        webView.loadUrl("file:////android_asset/WebViewContent/html/showUserProducts.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(webAppInterface, "Android");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


       // recyclerView.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //getProducts();
    }

    private void setAdapter(){
        ProductAdapter productAdapter = new ProductAdapter(foodItems, getApplicationContext());
       // recyclerView.setAdapter(productAdapter);
    }

   /* private void getProducts(){

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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

                        Log.d("FOOD_ITEMS", products.toString());
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
    }*/

    private User LoadSharedPreferences(){
        String name, lastname, email, id, pass, gen;
        name = preferences.getString(SharedPreference.KeyName,null);
        lastname = preferences.getString(SharedPreference.KeyLastname,null);
        email = preferences.getString(SharedPreference.KeyEmail,null);
        id = preferences.getString(SharedPreference.KeyId,null);
        pass = preferences.getString(SharedPreference.KeyLastname,null);
        return new User(id,name,lastname,email,pass);
    }

    private String addJsonHeader(String response, String header){

        return "{ \""+ header + "\":[" + response + "] }";
    }
}
