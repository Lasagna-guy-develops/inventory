package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    public void ButtonOnClick(View v) throws WriterException {
        System.out.println();
        System.out.println(db.getAllProducts());
        System.out.println();

        Button button = (Button) v;
        TextInputEditText Cantidad = (TextInputEditText) findViewById(R.id.TextInputEditText);
        TextInputEditText Precio = (TextInputEditText) findViewById(R.id.TextInputEditText2);
        TextInputEditText Nombre = (TextInputEditText) findViewById(R.id.TextInputEditText3);

        String msg = CtrlInventario.addInventario(Nombre.getText().toString(),
                Integer.parseInt(Cantidad.getText().toString()),
                Float.parseFloat(Precio.getText().toString()),
                new BOInventario(), db);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if(msg.compareTo("El objeto a ingresar ya existe en el inventario")!=0) {

            int id = CtrlInventario.retrieveInsertedId(Nombre.getText().toString(),
                    Integer.parseInt(Cantidad.getText().toString()),
                    Float.parseFloat(Precio.getText().toString()),
                    new BOInventario(), db);

            Bitmap qr = crearQr(id);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            qr.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] arr = baos.toByteArray();

            boolean b = CtrlInventario.addInventarioQr(id, Nombre.getText().toString(),
                    Integer.parseInt(Cantidad.getText().toString()),
                    Float.parseFloat(Precio.getText().toString()),
                    arr, new BOInventario(), db);

        }

        Cantidad.setText("");
        Precio.setText("");
        Nombre.setText("");
        Intent i = new Intent(this, IngresoExitoso.class);
        i.putExtra("msg",msg);
        startActivity(i);

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

}