package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

public class IngresoExitoso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_exitoso);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("msg");
            System.out.println(value);
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText(value);
        }
    }

    public void ButtonOnClick(View v) throws WriterException {

        finish();

    }
}