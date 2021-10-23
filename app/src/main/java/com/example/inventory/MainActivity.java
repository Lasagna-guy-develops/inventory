package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.view.Display;

import com.example.inventory.BO.BOInventario;
import com.example.inventory.Ctrl.CtrlInventario;
import com.example.inventory.DAO.DaoInventarioImpl;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public DaoInventarioImpl db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DaoInventarioImpl(this);
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