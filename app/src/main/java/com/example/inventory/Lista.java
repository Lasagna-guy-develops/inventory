package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.inventory.DAO.DaoInventarioImpl;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    public DaoInventarioImpl db;
    ListView lv1;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        db = new DaoInventarioImpl(this);
        lv1 = (ListView)findViewById(R.id.lista);

        ArrayList<String> ranking = new ArrayList<>();

        SQLiteDatabase bd = db.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from Inventory", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1));
            }while(fila.moveToNext());
        }

        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);
    }
}