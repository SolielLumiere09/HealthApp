package com.google.firebase.codelab.labelScannerUABC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.Serializable;

import com.google.firebase.codelab.labelScannerUABC.Class.*;
import com.google.firebase.codelab.labelScannerUABC.databinding.ActivityDataEntryBinding;
import com.google.firebase.codelab.mlkitUABC.*;
import com.google.firebase.codelab.textExtractor.analyzer.LabelAnalyzer;
/*
*   Esta clase recibe los datos obtenidos de la etiqueta escaneada, o permite al usuario ingresar
*   los datos de manera manual. Los campos se almacenan en un FoodItem que es enviado a NutrientsACtivity
*   como un extra.
*
*/

public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityDataEntryBinding binding;
    private FoodItem foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        binding = ActivityDataEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.root1);

        float [] nutrientes = getIntent().getFloatArrayExtra("nutrientes");
        if(nutrientes != null)
            setDataFromCamaraCapture(nutrientes);

        binding.acceptButton.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
    }

    //recibir los nutrientes capturados
    public void setDataFromCamaraCapture(float [] nutrientes){
        binding.cuerpoTamPorcion.setText(String.valueOf(nutrientes[LabelAnalyzer.TAM_PORCION]));
        binding.cuerpoPorciones.setText(String.valueOf(nutrientes[LabelAnalyzer.PORCIONES]));
        binding.cuerpoCalorias.setText(String.valueOf(nutrientes[LabelAnalyzer.CALORIAS]));
        binding.cuerpoGrasaTotal.setText(String.valueOf(nutrientes[LabelAnalyzer.GRASAS_TOTALES]));
        binding.cuerpoGrasaSaturada.setText(String.valueOf(nutrientes[LabelAnalyzer.GRASAS_SATURADAS]));
        binding.cuerpoGrasaTrans.setText(String.valueOf(nutrientes[LabelAnalyzer.GRASAS_TRANS]));
        binding.cuerpoCarbohidratos.setText(String.valueOf(nutrientes[LabelAnalyzer.CARBOHIDRATOS]));
        binding.cuerpoAzucar.setText(String.valueOf(nutrientes[LabelAnalyzer.AZUCARES]));
        binding.cuerpoColesterol.setText(String.valueOf(nutrientes[LabelAnalyzer.COLESTEROL] * 1000.f));
        binding.cuerpoSodio.setText(String.valueOf(nutrientes[LabelAnalyzer.SODIO] * 1000.f));
        binding.cuerpoProteina.setText(String.valueOf(nutrientes[LabelAnalyzer.PROTEINAS]));

        Log.d("SETDATA", "setDataFromCamaraCapture: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acceptButton:
                String product_name = binding.cuerpoNombreProducto.getText().toString();
                String portion_size = binding.cuerpoTamPorcion.getText().toString();
                boolean next = true;
                if (product_name.trim().equals("")){
                    binding.tituloNombreProducto.setTextColor(Color.rgb(255, 0, 55));
                    Toast.makeText(this, "Ingrese un nombre de producto", Toast.LENGTH_SHORT).show();
                    next = false;
                }
                if(portion_size.equals("")){
                    portion_size = "0";
                }
                if (Float.parseFloat(portion_size) == 0){
                    binding.tituloTamPorcion.setTextColor(Color.rgb(255, 0, 55));
                    Toast.makeText(this, "Ingrese un tamaño de porción", Toast.LENGTH_SHORT).show();
                    next = false;
                }

                if(next){
                    Intent intent = new Intent(this, NutrientsActivity.class);
                    foodItem = new FoodItem();
                    foodItem.setProduct_name(binding.cuerpoNombreProducto.getText().toString());
                    if(binding.cuerpoTamPorcion.getText().toString().length() > 0){

                        foodItem.setPortion_size(Float.parseFloat(binding.cuerpoTamPorcion.getText().toString()));
                    }
                    if(binding.cuerpoPorciones.getText().toString().length() > 0)
                        foodItem.setPortions(Float.parseFloat(binding.cuerpoPorciones.getText().toString()));
                    if(binding.cuerpoCalorias.getText().toString().length() > 0)
                        foodItem.setCalories(Float.parseFloat(binding.cuerpoCalorias.getText().toString()));
                    if(binding.cuerpoGrasaTotal.getText().toString().length() > 0)
                        foodItem.setTotalFat(Float.parseFloat(binding.cuerpoGrasaTotal.getText().toString()));
                    if(binding.cuerpoGrasaSaturada.getText().toString().length() > 0)
                        foodItem.setSaturatedFat(Float.parseFloat(binding.cuerpoGrasaSaturada.getText().toString()));
                    if(binding.cuerpoGrasaTrans.getText().toString().length() > 0)
                        foodItem.setTransFat(Float.parseFloat(binding.cuerpoGrasaTrans.getText().toString()));
                    if(binding.cuerpoCarbohidratos.getText().toString().length() > 0)
                        foodItem.setCarbs(Float.parseFloat(binding.cuerpoCarbohidratos.getText().toString()));
                    if(binding.cuerpoAzucar.getText().toString().length() > 0)
                        foodItem.setSugar(Float.parseFloat(binding.cuerpoAzucar.getText().toString()));
                    if(binding.cuerpoColesterol.getText().toString().length() > 0)
                        foodItem.setCholesterol(Float.parseFloat(binding.cuerpoColesterol.getText().toString()));
                    if(binding.cuerpoSodio.getText().toString().length() > 0)
                        foodItem.setSodium(Float.parseFloat(binding.cuerpoSodio.getText().toString()));
                    if(binding.cuerpoProteina.getText().toString().length() > 0)
                        foodItem.setProtein(Float.parseFloat(binding.cuerpoProteina.getText().toString()));
                    intent.putExtra("foodItem", (Serializable) foodItem);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //Insertamos el alimento en la base de datos
                    //insertFood(foodItem);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.backButton:
                finish();
                break;
        }
    }


}