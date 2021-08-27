package com.example.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SQLiteDataBaseHandler extends SQLiteOpenHelper {

    //All Static Variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Inventory";
    private static final String TABLE_INVENTORY= "Inventory";

    //Inventory Table Columns names
    private static final String KEY_ID = "iD";
    private static final String NOMBRE = "Nombre";
    private static final String CANTIDAD = "Cantidad";
    private static final String PRECIO = "Precio";

    public SQLiteDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + TABLE_INVENTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NOMBRE + " VARCHAR(45),"
                + CANTIDAD + " INTEGER," + PRECIO + " DOUBLE"+")";
        db.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        // Create tables again
        onCreate(db);
    }

    void addInventory(Inventario Inventario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE,Inventario.getName());
        values.put(CANTIDAD, Inventario.getCant());
        values.put(PRECIO,Inventario.getPrice());

//        String q = "INSERT INTO Inventory(Nombre, Cantidad, Precio) VALUES %s, %s, %s";
//        db.execSQL(q, values);
        db.insert(TABLE_INVENTORY,null,values);
        db.close();
    }

    Inventario getInventario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INVENTORY, new String[]{KEY_ID,NOMBRE,CANTIDAD,PRECIO},
                                KEY_ID + "=?",new String[]{String.valueOf(id)},
                                null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Inventario inventory = new Inventario(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                                                cursor.getInt(2),cursor.getFloat(3));
        return inventory;
    }

    public List getAllProducts() {
        List InventoryList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_INVENTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Inventario Inventario = new Inventario();
                Inventario.setId(Integer.parseInt(cursor.getString(0)));
                Inventario.setName(cursor.getString(1));
                Inventario.setCant(cursor.getInt(2));
                Inventario.setPrice(cursor.getFloat(3));
                // Adding country to list
                InventoryList.add(Inventario);
            } while (cursor.moveToNext());
        }

        // return country list
        return InventoryList;
    }

    public int updateInventory(Inventario Inventario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE,Inventario.getName());
        values.put(CANTIDAD, Inventario.getCant());
        values.put(PRECIO,Inventario.getPrice());

        return db.update(TABLE_INVENTORY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(Inventario.getId()) });
    }

    public void deleteCountry(Inventario Inventario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INVENTORY, KEY_ID + " = ?",
                new String[] { String.valueOf(Inventario.getId()) });
        db.close();
    }

    // Deleting all countries
    public void deleteAllCountries() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INVENTORY,null,null);
        db.close();
    }
}
