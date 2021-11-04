package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.inventory.DescripcionProducto;

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

        Intent i = new Intent(this, DescripcionProducto.class);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lista selItem = (Lista) lv1.getSelectedItem(); //
                String value= lv1.getItemAtPosition(position).toString(); //getter method
                System.out.println(value);
                i.putExtra("name", value);
                startActivity(i);
            }
        });

        ArrayList<String> ranking = new ArrayList<>();

        SQLiteDatabase bd = db.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from Inventory", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1) + " - " + fila.getString(2) + " - " + fila.getString(3));
            }while(fila.moveToNext());
        }

        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);
    }

}