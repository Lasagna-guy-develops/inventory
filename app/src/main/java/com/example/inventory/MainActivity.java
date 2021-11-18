package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.os.Bundle;

import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.DAO.DaoWS;
import com.google.zxing.WriterException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public DaoInventarioImpl db;
    public String response=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            System.out.println(new DaoWS().owners());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
