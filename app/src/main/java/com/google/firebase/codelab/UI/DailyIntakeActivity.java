package com.google.firebase.codelab.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.labelScannerUABC.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DailyIntakeActivity extends AppCompatActivity{
    private String URL_GET_PERCENTAGES = "http://conisoft.org/HealthAppV2/getPercentages.php";
    private String URL_SET_PERCENTAGES = "http://conisoft.org/HealthAppV2/setPercentages.php";

    private EditText edtCal, edit_fatGrams, edit_carbsGrams, edit_proteinGrams;
    private Spinner sp_carbs, sp_prot, sp_fat;
    private SharedPreferences preferences;
    private String calorias, carbs, protein, fat;
    private int suma;
    private User user;
    private int dailyFatPercentage, dailyCarbsPercentage, dailyProteinPercentage, dailyCalories;

    @Override
    protected void onStart() {
        super.onStart();
        getPercentages();

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_intake);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        user = getUser();


        //Get the EditText from the layout
        edtCal = findViewById(R.id.edit_cal);
        edit_fatGrams = findViewById(R.id.edit_fatGrams);
        edit_fatGrams.setKeyListener(null);

        edit_carbsGrams = findViewById(R.id.edit_carbsGrams);
        edit_carbsGrams.setKeyListener(null);

        edit_proteinGrams = findViewById(R.id.edit_proteinGrams);
        edit_proteinGrams.setKeyListener(null);


        //Get the Spinners from the layout
        sp_carbs = findViewById(R.id.sp_carbs);
        sp_prot = findViewById(R.id.sp_proteina);
        sp_fat = findViewById(R.id.sp_fat);

        String[] values = new String[101];

        for(int i = 0; i < 101; i++){
            values[i] = String.valueOf(i);
        }

        ArrayList<String> arrayListVal = new ArrayList<>(Arrays.asList(values));
        ArrayAdapter<String> arrayAdapterVal = new ArrayAdapter<>(this, R.layout.style_spinner,arrayListVal);
        sp_carbs.setAdapter(arrayAdapterVal);
        sp_prot.setAdapter(arrayAdapterVal);
        sp_fat.setAdapter(arrayAdapterVal);

        sp_carbs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                carbs = sp_carbs.getItemAtPosition(i).toString();
                updateNutrients();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp_prot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                protein = sp_prot.getItemAtPosition(i).toString();
                updateNutrients();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp_fat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fat = sp_fat.getItemAtPosition(i).toString();
                updateNutrients();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Get the BUTTONS from the Layout
        Button btnSave = findViewById(R.id.button_save);

        edtCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateNutrients();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                calorias = edtCal.getText().toString();
                suma = 0;
                suma = Integer.parseInt(carbs) + Integer.parseInt(protein) + Integer.parseInt(fat);
                if(calorias.isEmpty()) {
                    Toast.makeText(DailyIntakeActivity.this,"Rellenar todos los campos",Toast.LENGTH_SHORT).show();
                }
                else if(suma != 100){
                    Toast.makeText(DailyIntakeActivity.this,"La suma de los porcentajes debe de ser 100%",Toast.LENGTH_SHORT).show();
                }else if(suma == 100){
                    setPercentages();
                }

            }
        });
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

                        int dailyFat = Integer.parseInt((String) nutrients.get("dailyFat"));
                        int dailyCarbs = Integer.parseInt((String) nutrients.get("dailyCarbs"));
                        int dailyProtein = Integer.parseInt ((String) nutrients.get("dailyProtein"));

                        edit_carbsGrams.setText(String.valueOf(dailyCarbs));
                        edit_fatGrams.setText(String.valueOf(dailyFat));
                        edit_proteinGrams.setText(String.valueOf(dailyProtein));


                        dailyCalories = Integer.parseInt((String) nutrients.get("dailyCalories"));
                        dailyFatPercentage = (int) Math.ceil(((dailyFat * 9.f) / dailyCalories) * 100);
                        dailyCarbsPercentage = (int) Math.ceil(((dailyCarbs * 4.f) / dailyCalories) * 100);
                        dailyProteinPercentage = (int) Math.ceil(((dailyProtein * 4.f) / dailyCalories) * 100);

                        sp_carbs.setSelection(dailyCarbsPercentage);
                        sp_fat.setSelection(dailyFatPercentage);
                        sp_prot.setSelection(dailyProteinPercentage);
                        edtCal.setText(String.valueOf(dailyCalories));


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

    private void setPercentages(){

        StringRequest request = new StringRequest(Request.Method.POST, URL_SET_PERCENTAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("0")) {

                    Toast.makeText(getApplicationContext(), "ACTUALIZADO", Toast.LENGTH_SHORT).show();
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

                int newCalories = Integer.parseInt(edtCal.getText().toString());
                int newDailyFat = (int) (((sp_fat.getSelectedItemPosition() / 100.f) * newCalories)/9.f);
                int newDailyCarbs = (int) (((sp_carbs.getSelectedItemPosition() / 100.f) * newCalories)/4.f);
                int newDailyProtein = (int) (((sp_prot.getSelectedItemPosition() / 100.f) * newCalories)/4.f);


                parametros.put("id", user.getId());
                parametros.put("dailyFat", String.valueOf(newDailyFat));
                parametros.put("dailyCarbs", String.valueOf(newDailyCarbs));
                parametros.put("dailyProtein", String.valueOf(newDailyProtein));
                parametros.put("dailyCalories", String.valueOf(newCalories));


                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private User getUser(){

        String id = preferences.getString(SharedPreference.KeyId, null);
        String name = preferences.getString(SharedPreference.KeyName, null);
        String lastname = preferences.getString(SharedPreference.KeyLastname, null);
        String email = preferences.getString(SharedPreference.KeyEmail, null);
        String pass = preferences.getString(SharedPreference.KeyPassword, null);


        return new User(id, name, lastname ,email, pass);

    }

    private void updateNutrients(){
        try {
            int newCalories = Integer.parseInt(edtCal.getText().toString());
            int newDailyFat = (int) (((sp_fat.getSelectedItemPosition() / 100.f) * newCalories) / 9.f);
            int newDailyCarbs = (int) (((sp_carbs.getSelectedItemPosition() / 100.f) * newCalories) / 4.f);
            int newDailyProtein = (int) (((sp_prot.getSelectedItemPosition() / 100.f) * newCalories) / 4.f);


            edit_fatGrams.setText(String.valueOf(newDailyFat));
            edit_proteinGrams.setText(String.valueOf(newDailyProtein));
            edit_carbsGrams.setText(String.valueOf(newDailyCarbs));
        }catch (Exception e){

        }
    }

    public static float roundToHalf(float x) {
        return (float) (Math.ceil(x * 2) / 2);
    }

}
