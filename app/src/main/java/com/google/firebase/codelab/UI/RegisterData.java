package com.google.firebase.codelab.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.labelScannerUABC.MainMenuActivity;
import com.google.firebase.codelab.labelScannerUABC.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class RegisterData extends AppCompatActivity {
    private EditText edtEst, edtEdad, edtPeso;
    private String estatura, edad, peso, actividad;
    private String gen;
    private static final String URL = "http://conisoft.org/HealthAppV2/updateUserData.php";
    private SharedPreferences preferences;
    private User user;
    private ProgressDialog progressDialog;
    private Spinner sp_genero;
    private Spinner sp_actividad;
    private HashMap<String, Integer> activity_map;
    private int[] dailyMacros;
    private String calories, fats, carbs, proteins;

    private final int FAT = 0;
    private final int CARBS = 1;
    private final int PROTEIN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        //Get the EditTexts from the Layout
        edtEst = findViewById(R.id.edit_est);
        edtEdad = findViewById(R.id.edit_edad);
        edtPeso = findViewById(R.id.edit_peso);

        //Get the Spinners from the Layout
        sp_genero = findViewById(R.id.sp_genero);
        sp_actividad = findViewById(R.id.sp_actividad);

        //Daily Intakes of calories and macros
        dailyMacros = new int[3];

        //prepare the hasmap
        activity_map = new HashMap<String, Integer>();
        activity_map.put("Sedentario", 0);
        activity_map.put("Ligero", 1);
        activity_map.put("Moderado", 2);
        activity_map.put("Intenso", 3);



        String[] valueGen = {"Hombre", "Mujer"};
        ArrayList<String> arrayListGen = new ArrayList<>(Arrays.asList(valueGen));
        ArrayAdapter<String> arrayAdapterGen = new ArrayAdapter<>(this, R.layout.style_spinner,arrayListGen);
        sp_genero.setAdapter(arrayAdapterGen);

        String[] valueAct = {"Sedentario", "Ligero", "Moderado", "Intenso"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(valueAct));
        ArrayAdapter<String> arrayAdapterAct = new ArrayAdapter<>(this, R.layout.style_spinner,arrayList);
        sp_actividad.setAdapter(arrayAdapterAct);

        //Get the BUTTONS from the Layout
        Button btnRegister = findViewById(R.id.button_registro);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);

        this.user = getUser(); //obtener el usuario registrado

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //obtener todos los datos necesarios
                estatura = edtEst.getText().toString();
                edad = edtEdad.getText().toString();
                peso = edtPeso.getText().toString();
                gen = sp_genero.getSelectedItem().toString().charAt(0) + "";
                actividad = activity_map.get(sp_actividad.getSelectedItem().toString()).toString();

                calories = String.valueOf(calculateDailyCalories(gen.charAt(0),Integer.parseInt(peso),Integer.parseInt(estatura),Integer.parseInt(edad)));
                calculateDailyIntakes(Integer.parseInt(calories), Integer.parseInt(peso));
                fats = String.valueOf(dailyMacros[FAT]);
                carbs = String.valueOf(dailyMacros[CARBS]);
                proteins = String.valueOf(dailyMacros[PROTEIN]);

                if(estatura.isEmpty()  || edad.isEmpty() || peso.isEmpty())
                    Toast.makeText(RegisterData.this,R.string.error1,Toast.LENGTH_SHORT).show();
                else{
                    progressDialog = new ProgressDialog(RegisterData.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_layout);
                    progressDialog.setCancelable(false);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RegisterInfo(estatura, edad, peso);
                        }
                    }, 1000);
                }
            }
        });
    }

    private void RegisterInfo(final String estatura, final String edad, final String peso){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", user.toString() + "  |" + response);
                progressDialog.dismiss();
                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(), "Registrado correctamente",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "No se pudo registrar",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(RegisterData.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("genero", gen);
                parametros.put("estatura",estatura);
                parametros.put("edad",edad);
                parametros.put("peso",peso);
                parametros.put("actividad", actividad);
                parametros.put("id", user.getId());
                parametros.put("calories", calories);
                parametros.put("dailyFat", fats);
                parametros.put("dailyCarbs", carbs);
                parametros.put("dailyProtein", proteins);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void SaveSharedPreferences(){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(SharedPreference.KeyEstatura, user.getEstatura());
        edit.putString(SharedPreference.KeyEdad, user.getEdad());
        edit.putString(SharedPreference.KeyPeso, user.getPeso());
        edit.apply();
    }

    private User getUser(){

        String id = preferences.getString(SharedPreference.KeyId, null);
        String name = preferences.getString(SharedPreference.KeyName, null);
        String lastname = preferences.getString(SharedPreference.KeyLastname, null);
        String email = preferences.getString(SharedPreference.KeyEmail, null);
        String pass = preferences.getString(SharedPreference.KeyPassword, null);


        return new User(id, name, lastname ,email, pass);

    }

    private int calculateDailyCalories(char gender, int weight, int height, int age) {
        //Log.d("daily","Gender: " +gender);
        //Log.d("daily","Weight: " +weight);
        //Log.d("daily","Height: " +height);
        //Log.d("daily","Age: " +age);
        int calories = (int) ((10 * weight) + (6.25 * height) - (5 * age));
        if(gender == 'H') {
            //Log.d("daily","Calories: "+calories);
            return calories+5;
        } else if(gender == 'M') {
            return calories-161;
        } else
            return 0;
    }

    private void calculateDailyIntakes(int calories, int weight) {
        dailyMacros[PROTEIN] = weight;
        dailyMacros[FAT] = (int) ((calories*0.25) / 9) ;
        dailyMacros[CARBS] = (calories - (dailyMacros[PROTEIN]*4) - (dailyMacros[FAT]*9)) / 4;
        //Log.d("daily","Fat: " + dailyMacros[FAT]);
        //Log.d("daily","Carbs: " + dailyMacros[CARBS]);
        //Log.d("daily","Protein: " + dailyMacros[PROTEIN]);
    }

}
