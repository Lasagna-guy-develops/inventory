package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonOnClick(View v) throws WriterException {
        Button button = (Button) v;
        TextInputEditText Cantidad = (TextInputEditText) findViewById(R.id.TextInputEditText);
        TextInputEditText Precio = (TextInputEditText) findViewById(R.id.TextInputEditText2);
        TextInputEditText Nombre = (TextInputEditText) findViewById(R.id.TextInputEditText3);

        armar(Nombre.getText().toString(), Cantidad.getText().toString(), Precio.getText().toString());

        List productos = db.getAllProducts();
        int max = productos.size() - 1;
        int id = ((Inventario) productos.get(max)).getId();
        System.out.println(id);


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

        saveImageStorage(bitmap, x);


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

    private void armarID(){

    }

    private void armar(String Nombre, String Precio, String Cantidad){

        Inventario item = new Inventario(Nombre, Integer.parseInt(Cantidad), Integer.parseInt(Precio));

    }
}