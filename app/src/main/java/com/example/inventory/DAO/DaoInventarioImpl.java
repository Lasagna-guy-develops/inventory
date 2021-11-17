package com.example.inventory.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.widget.Toast;

import com.example.inventory.Objects.Inventario;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoInventarioImpl implements DAOInventario {

    public Connection conexionDB(){
        Connection cnn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/varDt01HvK","varDt01HvK","gQa4TK4SvA");
        }catch(Exception e){
            System.out.println(e);
        }
        return cnn;

    }

    @Override
    public Inventario getInventario(int id) {
        try{
            Statement stm = conexionDB().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Inventory WHERE id='"+id+"'");
            if(rs.next()){
                Inventario inventory = new Inventario(Integer.parseInt(rs.getString(0)),rs.getString(1),
                        rs.getInt(2),rs.getFloat(3));
                stm.close();
                return inventory;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Error in initializing a connection to MYSQL DB");
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean addInventario(Inventario Inventario) {
        try{
            Statement stm = conexionDB().createStatement();
            String NOMBRE =  Inventario.getName();
            int CANTIDAD = Inventario.getCant();
            float PRECIO = Inventario.getPrice();
            ResultSet rs = stm.executeQuery("INSERT INTO Inventory (nombre,cantidad precio) values ("+NOMBRE+","+CANTIDAD+","+PRECIO+")");
            rs.close();
            return true;
        }catch(Exception e) {
            System.out.println("Error in initializing a connection to MYSQL DB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Inventario getInventario(String Name) {
        try{
            Statement stm = conexionDB().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Inventory WHERE NOMBRE='"+Name+"'");
            if(rs.next()){
                Inventario inventory = new Inventario(Integer.parseInt(rs.getString(0)),rs.getString(1),
                        rs.getInt(2),rs.getFloat(3));
                stm.close();
                return inventory;
            }
        }catch(Exception e){
            System.out.println("Error in initializing a connection to MYSQL DB");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAllProducts() {
        try{
            Statement stm = conexionDB().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Inventory");
            List<String> InventoryList = new ArrayList<>();
            if (rs.first()) {
                do {
                    InventoryList.add(rs.getString(0) + " - " + rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
                } while (rs.next());
            }
            stm.close();
            // return country list
            return InventoryList;
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateInventory(Inventario Inventario) {
        try{
            Statement stm = conexionDB().createStatement();
            String NOMBRE =  Inventario.getName();
            int CANTIDAD = Inventario.getCant();
            float PRECIO = Inventario.getPrice();
            byte[] BITMAP = Inventario.getArr();
            ResultSet rs = stm.executeQuery("INSERT INTO Inventory (nombre,cantidad precio) values ("+NOMBRE+","+CANTIDAD+","+PRECIO+")");
            rs.close();
            return true;
        }catch(Exception e) {
            System.out.println("Error in initializing a connection to MYSQL DB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInventory(Inventario Inventario) {
        return false;
    }

    @Override
    public boolean deleteAllInventory() {
        return false;
    }

    /*private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Inventory.db";
    private static final String TABLE_INVENTORY = "Inventory";

    //Inventory Table Columns names
    private static final String KEY_ID = "iD";
    private static final String NOMBRE = "Nombre";
    private static final String CANTIDAD = "Cantidad";
    private static final String PRECIO = "Precio";
    private static final String BITMAP = "Bitmap";

    public DaoInventarioImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INVENTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_INVENTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NOMBRE + " VARCHAR(45),"
                + CANTIDAD + " INTEGER," + PRECIO + " DOUBLE," + BITMAP + " BLOB" + ")";
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
        values.put(BITMAP, Inventario.getArr());
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

    public Inventario getInventario2(Inventario inv) {
        if(inv.getName()=="Coca-Cola"){
            return inv;
        }else{
            return null;
        }
    }*/

}
