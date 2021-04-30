package com.google.firebase.codelab.labelScannerUABC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.google.firebase.codelab.labelScannerUABC.databinding.ActivityProductListBinding;
import com.google.firebase.codelab.mlkitUABC.NutrientsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity{

    private SharedPreferences preferences;
    private String URL = "http://conisoft.org/HealthAppV2/getProducts.php";
    private  User user;

    private RecyclerView recyclerView;
    public ArrayList<String> productNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = LoadSharedPreferences();
        recyclerView = findViewById(R.id.recyclerView);
        productNames = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getProducts();
    }

    private void setAdapter(){
        ProductAdapter productAdapter = new ProductAdapter(productNames, getApplicationContext());
        recyclerView.setAdapter(productAdapter);
    }

    private void getProducts(){

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
                            productNames.add((String) product.get("Nombre"));
                        }

                        Log.d("NAMES_PRODUCT", productNames.toString());
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
