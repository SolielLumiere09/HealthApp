package com.google.firebase.codelab.labelScannerUABC;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.codelab.UI.DailyIntakeActivity;
import com.google.firebase.codelab.UI.DiaryActivity;
import com.google.firebase.codelab.UI.LoginActivity;
import com.google.firebase.codelab.labelScannerUABC.Class.CaloriesLoader;
import com.google.firebase.codelab.labelScannerUABC.Class.ConsumedCalories;
import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.Class.SharedPreference;
import com.google.firebase.codelab.labelScannerUABC.Class.User;
import com.google.firebase.codelab.labelScannerUABC.databinding.ActivityMainMenuBinding;
import com.google.firebase.codelab.textExtractor.BarcodeAnalyzer.JsonParser;
import com.google.firebase.codelab.textExtractor.analyzer.CameraActivity;
import com.google.firebase.codelab.textExtractor.analyzer.LabelAnalyzer;
import com.google.firebase.codelab.textExtractor.analyzer.LabelCleaner;
import com.google.firebase.codelab.textExtractor.groups.TextElements;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Hola omar, whamuuuu mezame tame waga aruji tachi!
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private final int PICK_IMAGE_REQUEST= 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private final int REQUEST_BARCODE_CAPTURE = 3;


    private SharedPreferences preferences;
    private LabelAnalyzer labelAnalyzer;
    private JsonParser jsonParser;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        preferences = getSharedPreferences(SharedPreference.namePreference, MODE_PRIVATE);
        User user = LoadSharedPreferences();
        labelAnalyzer = new LabelAnalyzer();
        

        //Checamos permisos de la camara
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Pedimos permiso si no lo tenemos
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }

        Log.d("name",user.getName());
        Log.d("email",user.getEmail());

        com.google.firebase.codelab.labelScannerUABC.databinding.ActivityMainMenuBinding binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.root);
        binding.productoButton.setOnClickListener(this);
        binding.cameraButton.setOnClickListener(this);
        binding.inputButton.setOnClickListener(this);
        binding.galleryButton.setOnClickListener(this);
        binding.buttonLogout.setOnClickListener(this);
        binding.toolButton.setOnClickListener(this);
        binding.textView14.setText(user.getEmail());
        binding.textView15.setText(user.getName());

    }

    public void openCamera(View view) {
        //Abrimos la camara
        new IntentIntegrator(this).initiateScan();//initialize camera bar scanner
        //Intent intent = new Intent(this, CameraActivity.class);
        //startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.productoButton:
                intent = new Intent(this, ProductListActivity.class);
                startActivity(intent);
                break;
            case R.id.inputButton:
                intent = new Intent(this, DiaryActivity.class);
                startActivity(intent);
                break;
            case R.id.cameraButton:
                openCamera(view);
                break;
            case R.id.galleryButton:
                getImage();
                break;
            case R.id.button_logout:
                logout();
                break;
            case R.id.toolButton:
                 startActivity(new Intent(this, DailyIntakeActivity.class));
                 break;
        }

    }

    void getImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult resultBarcode = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//get the bar code scanned


        if ((requestCode == PICK_IMAGE_REQUEST) && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                InputImage image = InputImage.fromFilePath(this, filePath);
                TextRecognizer recognizer = TextRecognition.getClient(); //Obtener reconocedor de texto


                Log.d("EXITO", "onActivityResult: IMAGE");

                Task<Text> result =
                        recognizer.process(image)
                                .addOnSuccessListener(new OnSuccessListener<Text>() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onSuccess(Text visionText) {
                                        String text = extractText(visionText);
                                        Log.d("SuperTexto", text);
                                        analizeString(text);
                                    }
                                })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Task failed with an exception
                                                // ...
                                            }
                                        });

            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        if(resultBarcode != null){//check if there's data in result

            if(resultBarcode.getContents() != null){//check the data is not empty
                //codigo de barras escaneado
                Toast.makeText(this, resultBarcode.getContents(), Toast.LENGTH_LONG).show();
                jsonParser = new JsonParser();
                jsonParser.setBarUrl(resultBarcode.getContents());
                jsonParser.start();

                try {
                    jsonParser.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Intent dataEntryActivity = new Intent(this, DataEntryActivity.class);
                dataEntryActivity.putExtra("nutrientes", jsonParser.getLabel_data());
                startActivity(dataEntryActivity);

            }

            else{

                Toast.makeText(this, "No se escaneo", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String extractText(Text result) {

        ArrayList<TextElements> elements = new ArrayList<>();
        StringBuilder extractedText = new StringBuilder();

        String resultText = result.getText();
        for (Text.TextBlock block : result.getTextBlocks()) {
            String blockText = block.getText();
            Point[] blockCornerPoints = block.getCornerPoints();
            Rect blockFrame = block.getBoundingBox();
            for (Text.Line line : block.getLines()) {
                String lineText = line.getText();
                Point[] lineCornerPoints = line.getCornerPoints();
                Rect lineFrame = line.getBoundingBox();
                for (Text.Element element : line.getElements()) {
                    String elementText = element.getText();
                    Point[] elementCornerPoints = element.getCornerPoints();
                    Rect elementFrame = element.getBoundingBox();


                    elements.add(new TextElements(elementText, elementFrame));
                }
            }
        }

        sortElements(elements);

        for (TextElements element : elements) {
            extractedText.append(element.getText());
        }

        return extractedText.toString();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortElements(ArrayList<TextElements> elements) {
        int i = 0;
        TextElements aux;
        ArrayList<TextElements> group = new ArrayList<>(); //arraylist para guardar pequeños renglones
        ArrayList<TextElements> sortedGroups = new ArrayList<>();//arraylist para guardar los grupos ordenados

        elements.sort((o1, o2) -> Integer.compare(o1.getFrame().top, o2.getFrame().top)); //ordenamiento por Y a los elementos
        /*
            Funcion lambda, o1 y o2 son objetos tipo TextElement y se agrega la condicion de que quieres comparar
            En este caso se esta tomando el top del rect de ambos objetos para que el arraylist los ordene usando QuickSort
        */

        while(i < elements.size() - 1){
            aux = elements.get(i);
            group.add(aux);

            try{
                int resta = Math.abs(aux.getFrame().top - elements.get(i + 1).getFrame().top);
                if(resta <= 5){
                    do{
                        group.add(elements.get(i + 1));
                        i++;
                        resta = Math.abs(aux.getFrame().top - elements.get(i + 1).getFrame().top);
                    }while (resta <= 5);
                    i++;

                    group.sort((o1, o2) -> Integer.compare(o1.getFrame().left, o2.getFrame().left)); //ordenamiento por X a los elementos
                    sortedGroups.addAll(group);
                    group.clear();
                }
                else
                    i++;

            }catch (Exception ignored){
            }
        }

        elements.clear();
        elements.addAll(sortedGroups);
    }

    public void analizeString(String labelText){
        labelAnalyzer.analyze(LabelCleaner.cleanLabelText(labelText));
        Intent dataEntryActivity = new Intent(this, DataEntryActivity.class);
        dataEntryActivity.putExtra("nutrientes", labelAnalyzer.getAmountNutrients());
        startActivity(dataEntryActivity);
        labelAnalyzer.resetFilters();

    }

    private void logout(){
        preferences.edit().clear().apply();
        startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
        finish();
    }

    private User LoadSharedPreferences(){
        String name, lastname, email, id, pass, gen;
        name = preferences.getString(SharedPreference.KeyName,null);
        lastname = preferences.getString(SharedPreference.KeyLastname,null);
        email = preferences.getString(SharedPreference.KeyEmail,null);
        id = preferences.getString(SharedPreference.KeyId,null);
        pass = preferences.getString(SharedPreference.KeyPassword,null);
        return new User(id,name,lastname,email,pass);
    }
}
