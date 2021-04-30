package com.google.firebase.codelab.labelScannerUABC;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.ArrayList;

import java.util.HashMap;
import android.database.DatabaseUtils;

import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LabelScannerDB.db";
    public static final String FOOD_TABLE_NAME = "food";
    public static final String FOOD_COLUMN_ID = "id";
    public static final String FOOD_COLUMN_NAME = "product_name";
    public static final String FOOD_COLUMN_PORTION_SIZE = "portion_size";
    public static final String FOOD_COLUMN_PORTIONS = "portions";
    public static final String FOOD_COLUMN_CALORIES = "calories";
    public static final String FOOD_COLUMN_SODIUM = "sodium";
    public static final String FOOD_COLUMN_CARBS = "carbs";
    public static final String FOOD_COLUMN_FAT = "fat";
    public static final String FOOD_COLUMN_TRANS_FAT = "trans_fat";
    public static final String FOOD_COLUMN_SUGAR = "sugar";
    public static final String FOOD_COLUMN_PROTEIN = "protein";
    public static final String FOOD_COLUMN_DATE_ADDED = "date_added";
    public static final String FOOD_COLUMN_DATE_MODIFIED = "date_modified";

    public static final int ALPHABETICAL = 1;
    public static final int ALPHABETICAL_INVERTED = 2;
    public static final int DATE_MODIFIED_RECENT = 3;
    public static final int DATE_MODIFIED_OLDER = 4;

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL(
                "create table  food" +
                        "(id integer primary key autoincrement, product_name text, portion_size integer, portions integer, calories real, sodium real, carbs real, fat real, sugar real, trans_fat real, protein real, date_added integer, date_modified integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }


    public boolean insertFood (String product_name, float portion_size, float portions, float calories, float sodium, float carbs, float fat, float sugar, float transfat, float protein) {
        Date now = new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_COLUMN_NAME, product_name);
        contentValues.put(FOOD_COLUMN_PORTION_SIZE, portion_size);
        contentValues.put(FOOD_COLUMN_PORTIONS, portions);
        contentValues.put(FOOD_COLUMN_CALORIES, calories);
        contentValues.put(FOOD_COLUMN_SODIUM, sodium);
        contentValues.put(FOOD_COLUMN_CARBS, carbs);
        contentValues.put(FOOD_COLUMN_FAT, fat);
        contentValues.put(FOOD_COLUMN_TRANS_FAT, transfat);
        contentValues.put(FOOD_COLUMN_SUGAR, sugar);
        contentValues.put(FOOD_COLUMN_PROTEIN, protein);
        contentValues.put(FOOD_COLUMN_DATE_ADDED, System.currentTimeMillis());
        contentValues.put(FOOD_COLUMN_DATE_MODIFIED, System.currentTimeMillis());
        db.insert(FOOD_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertFood (FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_COLUMN_NAME, foodItem.getProduct_name());
        contentValues.put(FOOD_COLUMN_PORTION_SIZE, foodItem.getPortion_size());
        contentValues.put(FOOD_COLUMN_PORTIONS, foodItem.getPortions());
        contentValues.put(FOOD_COLUMN_CALORIES, foodItem.getCalories());
        contentValues.put(FOOD_COLUMN_SODIUM, foodItem.getSodium());
        contentValues.put(FOOD_COLUMN_CARBS, foodItem.getCarbs());
        contentValues.put(FOOD_COLUMN_FAT, foodItem.getTotalFat());
        contentValues.put(FOOD_COLUMN_TRANS_FAT, foodItem.getTransFat());
        contentValues.put(FOOD_COLUMN_SUGAR, foodItem.getSugar());
        contentValues.put(FOOD_COLUMN_PROTEIN, foodItem.getProtein());
        contentValues.put(FOOD_COLUMN_DATE_ADDED, System.currentTimeMillis());
        contentValues.put(FOOD_COLUMN_DATE_MODIFIED, System.currentTimeMillis());
        db.insert(FOOD_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from food where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FOOD_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_COLUMN_NAME, foodItem.getProduct_name());
        contentValues.put(FOOD_COLUMN_PORTION_SIZE, foodItem.getPortion_size());
        contentValues.put(FOOD_COLUMN_PORTIONS, foodItem.getPortions());
        contentValues.put(FOOD_COLUMN_CALORIES, foodItem.getCalories());
        contentValues.put(FOOD_COLUMN_SODIUM, foodItem.getSodium());
        contentValues.put(FOOD_COLUMN_CARBS, foodItem.getCarbs());
        contentValues.put(FOOD_COLUMN_FAT, foodItem.getTotalFat());
        contentValues.put(FOOD_COLUMN_TRANS_FAT, foodItem.getTransFat());
        contentValues.put(FOOD_COLUMN_SUGAR, foodItem.getSugar());
        contentValues.put(FOOD_COLUMN_PROTEIN, foodItem.getProtein());
        contentValues.put(FOOD_COLUMN_DATE_ADDED, System.currentTimeMillis());
        contentValues.put(FOOD_COLUMN_DATE_MODIFIED, System.currentTimeMillis());
        db.update(FOOD_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(foodItem.getId()) } );
        return true;
    }

    public Integer deleteFood (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FOOD_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllFoodNames() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from food", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(FOOD_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<FoodItem> getAllFoods(int sortOrder) {
        ArrayList<FoodItem> array_list = new ArrayList<FoodItem>();
        Cursor res;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        switch(sortOrder) {
            case ALPHABETICAL:
                res = db.rawQuery("select * from food order by " + FOOD_COLUMN_NAME, null);
                break;
            case ALPHABETICAL_INVERTED:
                res = db.rawQuery("select * from food order by " + FOOD_COLUMN_NAME + " DESC", null);
                break;
            case DATE_MODIFIED_RECENT:
                res = db.rawQuery("select * from food order by " + FOOD_COLUMN_DATE_MODIFIED + " DESC", null);
                break;
            case DATE_MODIFIED_OLDER:
                res = db.rawQuery("select * from food order by " + FOOD_COLUMN_DATE_MODIFIED, null);
                break;
            default:
                res = db.rawQuery("select * from food", null);
                break;
        }
        res.moveToFirst();

        while(res.isAfterLast() == false){
            FoodItem foodItem = new FoodItem();
            foodItem.setId(res.getInt(res.getColumnIndex(FOOD_COLUMN_ID)));
            foodItem.setProduct_name(res.getString(res.getColumnIndex(FOOD_COLUMN_NAME)));
            foodItem.setPortion_size(res.getFloat(res.getColumnIndex(FOOD_COLUMN_PORTION_SIZE)));
            foodItem.setPortions(res.getFloat(res.getColumnIndex(FOOD_COLUMN_PORTIONS)));
            foodItem.setCalories(res.getFloat(res.getColumnIndex(FOOD_COLUMN_CALORIES)));
            foodItem.setSodium(res.getFloat(res.getColumnIndex(FOOD_COLUMN_SODIUM)));
            foodItem.setCarbs(res.getFloat(res.getColumnIndex(FOOD_COLUMN_CARBS)));
            foodItem.setTotalFat(res.getFloat(res.getColumnIndex(FOOD_COLUMN_FAT)));
            foodItem.setSugar(res.getFloat(res.getColumnIndex(FOOD_COLUMN_SUGAR)));
            foodItem.setTransFat(res.getFloat(res.getColumnIndex(FOOD_COLUMN_TRANS_FAT)));
            foodItem.setProtein(res.getFloat(res.getColumnIndex(FOOD_COLUMN_PROTEIN)));
            foodItem.setDateAdded(res.getLong(res.getColumnIndex(FOOD_COLUMN_DATE_ADDED)));
            foodItem.setDateModified(res.getLong(res.getColumnIndex(FOOD_COLUMN_DATE_MODIFIED)));

            array_list.add(foodItem);
            res.moveToNext();
        }
        return array_list;
    }
}