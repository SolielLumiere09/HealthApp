package com.google.firebase.codelab.labelScannerUABC.Class;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CaloriesLoader {

    public static final String FILENAME = "CONSUMED_CALORIES.TXT";

    public static void writeConsumedCalories(Context context, ConsumedCalories consumedCalories){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(consumedCalories);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConsumedCalories readConsumedCalories(Context context){
        FileInputStream fis = null;
        ConsumedCalories consumedCalories = null;
        try {
            fis = context.openFileInput(FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            consumedCalories = (ConsumedCalories) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return consumedCalories;
    }
}
