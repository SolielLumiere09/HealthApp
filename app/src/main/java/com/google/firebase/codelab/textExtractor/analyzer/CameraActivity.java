package com.google.firebase.codelab.textExtractor.analyzer;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.OrientationEventListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.codelab.labelScannerUABC.DataEntryActivity;
import com.google.firebase.codelab.labelScannerUABC.R;
import com.google.firebase.codelab.textExtractor.groups.TextElements;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private TextView textView;
    private LabelAnalyzer labelAnalyzer;
    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        previewView = findViewById(R.id.previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(11);
        progressBar.setScaleY(2f);


        if(savedInstanceState == null)
            labelAnalyzer = new LabelAnalyzer(true);
        else
            labelAnalyzer = (LabelAnalyzer) savedInstanceState.getSerializable("LabelAnalyzer");

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindImageAnalysis(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindImageAnalysis(@NonNull ProcessCameraProvider cameraProvider) {
        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new ImageAnalysis.Analyzer() {
            @androidx.camera.core.ExperimentalGetImage
            @Override
            public void analyze(@NonNull ImageProxy image) {

                Image mediaImage = image.getImage();

                if (mediaImage != null) {
                    InputImage inputImage = InputImage.fromMediaImage(mediaImage, image.getImageInfo().getRotationDegrees());

                    // Pass image to an ML Kit Vision API
                    //Aplicacion ML Kit Vision API de la Firebase de Google
                    TextRecognizer recognizer = TextRecognition.getClient(); //Obtener reconocedor de texto

                    Task<Text> result = recognizer.process(inputImage)
                        //Procesar la Imagen para obtener texto
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onSuccess(Text visionText) { //visionText es el texto que regresa la app
                                String text = extractText(visionText);
                                Log.d("SuperTexto", text);
                                analyzeString(text);
                                image.close();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        Log.d("TEXTO_SALIDA", "lo intentamos pero no funciono :c");
                                    }
                        });
                }



            }
        });
        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                //textView.setText(String.format(Locale.getDefault(),"%d", orientation));
            }
        };
        orientationEventListener.enable();
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector,
                imageAnalysis, preview);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("LabelAnalyzer", labelAnalyzer);

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
                if(resta <= 9){
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void analyzeString(String labelText){

        if(labelAnalyzer.analyze(LabelCleaner.cleanLabelText(labelText), progressBar)){
            int [] array = new int[LabelAnalyzer.getSIZE()];

            for(int i = 0; i < array.length; i++){
                array[i] = labelAnalyzer.getAmountNutrients()[i];
            }

            Intent dataEntryActivity = new Intent(this, DataEntryActivity.class);
            dataEntryActivity.putExtra("nutrientes", array);

            labelAnalyzer.resetFilters();
            progressBar.setProgress(0, true);
            startActivity(dataEntryActivity);

        }


    }
}