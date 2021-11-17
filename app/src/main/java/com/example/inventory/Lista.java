package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.inventory.DAO.DaoInventarioImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {

    public DaoInventarioImpl db;
    ListView lv1;
    TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        db = new DaoInventarioImpl();
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

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://inventorywebservices.herokuapp.com/webService/inventarioDisp.json?user=a";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        List<String> list = new ArrayList<String>();
                        System.out.println("Response is: "+response);
                        JSONObject obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("Products");
                        for(int n = 0 ; n < array.length() ; n++){
                            list.add(array.getJSONObject(n).getString("Cantidad")+" "
                                    +array.getJSONObject(n).getString("Id")+" "
                                    +array.getJSONObject(n).getString("Precio")+" "
                                    +array.getJSONObject(n).getString("Producto"));

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                        lv1.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );
        queue.add(stringRequest);
    }
}