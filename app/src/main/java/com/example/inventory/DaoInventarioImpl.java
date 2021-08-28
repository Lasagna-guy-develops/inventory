package com.example.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaoInventarioImpl extends SQLiteOpenHelper implements DAOInventario{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Inventory";
    private static final String TABLE_INVENTORY = "Inventory";

    //Inventory Table Columns names
    private static final String KEY_ID = "iD";
    private static final String NOMBRE = "Nombre";
    private static final String CANTIDAD = "Cantidad";
    private static final String PRECIO = "Precio";

    public DaoInventarioImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + TABLE_INVENTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NOMBRE + " VARCHAR(45),"
                + CANTIDAD + " INTEGER," + PRECIO + " DOUBLE" + ")";
        db.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        // Create tables again
        onCreate(db);
    }

    public boolean addInventario(Inventario Inventario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE, Inventario.getName());
        values.put(CANTIDAD, Inventario.getCant());
        values.put(PRECIO, Inventario.getPrice());
        try {
            db.insert(TABLE_INVENTORY, null, values);
            db.close();
            return true;
        } catch (SQLiteException e) {
            return false;
        }
    }

    public Inventario getInventario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INVENTORY, new String[]{KEY_ID,NOMBRE,CANTIDAD,PRECIO},
                KEY_ID + "=?",new String[]{String.valueOf(id)},
                null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Inventario inventory = new Inventario(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getInt(2),cursor.getFloat(3));
        db.close();
        return inventory;
    }

    public Inventario getInventario(String Name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INVENTORY, new String[]{KEY_ID,NOMBRE,CANTIDAD,PRECIO},
                NOMBRE + "=?",new String[]{String.valueOf(Name)},
                null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        try{
        Inventario inventory = new Inventario(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getInt(2),cursor.getFloat(3));
            db.close();
        return inventory;}
        catch(Exception e){
            db.close();
            return null;
        }

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
        db.close();
        // return country list
        return InventoryList;
    }

    public boolean updateInventory(Inventario Inventario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE,Inventario.getName());
        values.put(CANTIDAD, Inventario.getCant());
        values.put(PRECIO,Inventario.getPrice());
        try{
            db.update(TABLE_INVENTORY, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(Inventario.getId()) });
            db.close();
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public boolean deleteInventory(Inventario Inventario) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_INVENTORY, KEY_ID + " = ?",
                    new String[]{String.valueOf(Inventario.getId())});
            db.close();
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public boolean deleteAllInventory() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_INVENTORY, null, null);
            db.close();
            return true;
        } catch (SQLiteException e) {
            return false;
        }
    }
}
