package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.inventory.IngresoExitoso;
import com.example.inventory.MainActivity;
import com.example.inventory.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.widget.Toast;
import android.os.Bundle;

public class Ingresar extends AppCompatActivity {

    public DaoInventarioImpl db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DaoInventarioImpl(this);
        setContentView(R.layout.activity_ingresar);
        TextInputEditText Cantidad = (TextInputEditText) findViewById(R.id.TextInputEditText);
        TextInputEditText Precio = (TextInputEditText) findViewById(R.id.TextInputEditText2);
        TextInputEditText Nombre = (TextInputEditText) findViewById(R.id.TextInputEditText3);
        Cantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cantidad.getText().clear();
                Cantidad.setHint("Cantidad");
            }
        });
        Precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Precio.getText().clear();
                Precio.setHint("Precio");
            }
        });
        Nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombre.getText().clear();
                Nombre.setHint("Nombre");
            }
        });
    }


    public void ButtonOnClick(View v) throws WriterException {

        Button button = (Button) v;
        TextInputEditText Cantidad = (TextInputEditText) findViewById(R.id.TextInputEditText);
        TextInputEditText Precio = (TextInputEditText) findViewById(R.id.TextInputEditText2);
        TextInputEditText Nombre = (TextInputEditText) findViewById(R.id.TextInputEditText3);

        String cantidad = Cantidad.getText().toString();
        String precio = Precio.getText().toString();
        String nombre = Nombre.getText().toString();

        Cantidad.getText().clear();
        Precio.getText().clear();
        Nombre.getText().clear();

        if(nombre.isEmpty()||cantidad.isEmpty()||precio.isEmpty()) {

            if (nombre.isEmpty()) {
                Nombre.setError("Nombre esta vacio");
                Nombre.setText("Nombre esta vacio");
            }
            if (cantidad.isEmpty()) {
                Cantidad.setError("Cantidad esta vacia");
                Cantidad.setText("Cantidad esta vacia");
            }
            if (precio.isEmpty()) {
                Precio.setError("Precio esta vacio");
                Precio.setText("Precio esta vacio");
            }
        }else{
            try {
                double value = Double.parseDouble(precio);
                if (value < 0) {
                    Precio.setError("El número es negativo");
                    Precio.setText("El número es negativo");
                }
            }catch(NumberFormatException e) {
                Precio.setError("No es un número");
                Precio.setText("No es un número");
            }
            try {
                double value = Double.parseDouble(cantidad);
                if (value < 0) {
                    Cantidad.setError("El número es negativo");
                    Cantidad.setText("El número es negativo");
                }
            } catch (NumberFormatException e) {
                Cantidad.setError("No es un número");
                Cantidad.setText("No es un número");
            }
            if(Precio.getError()==null&&Cantidad.getError()==null){
                String msg = Ingresar(cantidad, precio, nombre);
                Cantidad.setText("");
                Precio.setText("");
                Nombre.setText("");
                Intent i = new Intent(this, IngresoExitoso.class);
                i.putExtra("msg",msg);
                startActivity(i);
            }
        }
    }

    private Bitmap crearQr(int id){
        Bitmap bitmap = null;

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        String savePath = Environment.getExternalStoragePublicDirectory("").getPath() + "/QRCode/";
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        String x = id + "";
        QRGEncoder qrgEncoder = new QRGEncoder(x, null, QRGContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            Log.e("Tag", e.toString());
        }

        //saveImageStorage(bitmap, x);

        return bitmap;
    }

    private void saveImageStorage(Bitmap finalBitmap, String name) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        File myDir = new File(root + "/");
        if (!myDir.exists()) {
            myDir.mkdir();
        }

        String fname = "Image-" + name + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // inform to the media scanner about the new file so that it is immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }

    private String Ingresar(String Cantidad, String Precio, String Nombre) {
        String msg = CtrlInventario.addInventario(Nombre,
                Integer.parseInt(Cantidad),
                Float.parseFloat(Precio),
                new BOInventario(), db);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (msg.compareTo("El objeto a ingresar ya existe en el inventario") != 0) {

            int id = CtrlInventario.retrieveInsertedId(Nombre,
                    Integer.parseInt(Cantidad),
                    Float.parseFloat(Precio),
                    new BOInventario(), db);

            Bitmap qr = crearQr(id);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            qr.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] arr = baos.toByteArray();

            boolean b = CtrlInventario.addInventarioQr(id, Nombre,
                    Integer.parseInt(Cantidad),
                    Float.parseFloat(Precio),
                    arr, new BOInventario(), db);
        }
        return msg;
    }

}