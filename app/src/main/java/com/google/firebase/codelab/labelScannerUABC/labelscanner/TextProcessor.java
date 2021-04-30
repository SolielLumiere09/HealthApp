package com.google.firebase.codelab.labelScannerUABC.labelscanner;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class TextProcessor {
    private Bitmap mSelectedImage;

    private static void runTextRecognition(Bitmap mSelectedImage) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage);
        FirebaseVisionTextRecognizer recognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        //mTextButton.setEnabled(false);
        recognizer.processImage(image)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                //mTextButton.setEnabled(true);
                                processTextRecognitionResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                e.printStackTrace();
                            }
                        });
    }


    private static String processTextRecognitionResult(FirebaseVisionText texts) {
        String content = "";
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            //showToast("No text found");
            return "";
        }
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {
                    //Graphic textGraphic = new TextGraphic(mGraphicOverlay, elements.get(k));
                    //mGraphicOverlay.add(textGraphic);
                    if(elements.get(k).getText().equals("Calorías")){
                        //System.out.println(elements.get(k).getText());

                        //GraphicOverlay.Graphic textGraphic = new TextGraphic(mGraphicOverlay, elements.get(k));
                        //mGraphicOverlay.add(textGraphic);
                    }
                    content = content + " " + elements.get(k).getText();
                    //return;
                }
            }
        }

        System.out.println(content);
        //PseudoLexer pseuLexer = new PseudoLexer(content);
        //System.out.println(pseuLexer.tokens);
        //PseudoParser parser = new PseudoParser(pseuLexer); // create parser
        //System.out.println(parser.calorias + "HEY");
        //showToast("Calorias " + parser.calorias);
        //parseText(content);
        return content;
    }
/*
    private static FoodItem parseText(String s) {
        FoodItem food = new FoodItem();
        String regex_cal = "()([\\s]+)?(?<calorias>\\d+)([\\s]+?)(kj|kJ|kcal)?";
        Pattern pattern = Pattern.compile(regex_cal);
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            food.setCalories(Float.valueOf(matcher.group(3)));
            System.out.println("calorías: " +food.getCalories());
        }

        String regex_protein = "()([\\s]+)?(?<calorias>\\d+)([\\s]+?)(g|G|gr|Gr|mg|Mg|MG)?";
        pattern = Pattern.compile(regex_protein);
        matcher = pattern.matcher(s);
        System.out.println("i");

        while (matcher.find()) {
            System.out.println("n");
            System.out.println(matcher.group(3));
        }
        return food;
    }
*/
}
