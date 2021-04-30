package com.google.firebase.codelab.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_email, edt_pass;
    private String email, pass;
    private static final String URL = "http://conisoft.org/HealthAppV2/login.php";
    private SharedPreferences preferences;
    private User user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get the EditTexts from the Layout
        edt_email = findViewById(R.id.et_email);
        edt_pass = findViewById(R.id.et_password);

        //Get the BUTTONS from the Layout
        Button login_button = findViewById(R.id.button_login);
        Button register_button = findViewById(R.id.button_register);

        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        email = preferences.getString(SharedPreference.KeyEmail,null);

        if(email != null){
            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email.getText().toString();
                pass = edt_pass.getText().toString();

                if(email.isEmpty() || pass.isEmpty())
                    Toast.makeText(LoginActivity.this,R.string.error1,Toast.LENGTH_SHORT).show();
                else{
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_layout);
                    progressDialog.setCancelable(false);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ValidateUser();
                        }
                    },1000);
                }
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void ValidateUser(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("0")) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        user = new User(jsonObj.getString("id"), jsonObj.getString("name"),jsonObj.getString("lastname"), jsonObj.getString("email"),jsonObj.getString("pass"));
                        SaveSharedPreferences();
                        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, R.string.errorUser, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {             //parametros que se envian con metodo POST
                Map<String,String> parametros = new HashMap<>();
                parametros.put("email",email);
                parametros.put("pass",pass);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void SaveSharedPreferences(){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(SharedPreference.KeyId,user.getId());
        edit.putString(SharedPreference.KeyName,user.getName());
        edit.putString(SharedPreference.KeyLastname,user.getLastname());
        edit.putString(SharedPreference.KeyEmail,user.getEmail());
        edit.putString(SharedPreference.KeyPassword,user.getPassword());
        edit.putString(SharedPreference.KeyGen,user.getGen());
        edit.apply();
    }
}