package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventory.DAO.DaoInventarioImpl;
import com.google.zxing.WriterException;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public DaoInventarioImpl db;
    public String response=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonOnClick(View v) throws WriterException {

        Intent intent = new Intent(MainActivity.this, Ingresar.class);
        startActivity(intent);
    }

    public void ButtonOnClick1(View v) throws WriterException {

        Intent intent = new Intent(MainActivity.this, Lista.class);
        startActivity(intent);
    }
}
