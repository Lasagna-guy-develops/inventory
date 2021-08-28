package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DaoInventarioImpl db = new DaoInventarioImpl(MainActivity.this);

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

        Dialog dialog = new Dialog(MainActivity.this);

        String msg = CtrlInventario.addInventario(Nombre.getText().toString(),
                Integer.parseInt(Cantidad.getText().toString()),
                Float.parseFloat(Precio.getText().toString()),
                new BOInventario(), db);

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        if(msg.compareTo("El objeto a ingresar ya existe en el inventario")!=0) {
            int id = CtrlInventario.retrieveInsertedId(Nombre.getText().toString(),
                    Integer.parseInt(Cantidad.getText().toString()),
                    Float.parseFloat(Precio.getText().toString()),
                    new BOInventario(), db);

//        Bitmap qr = crearQr(id);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        qr.compress(Bitmap.CompressFormat.PNG, 100, baos);
//         byte[] bArray = baos.toByteArray();
        }

         Cantidad.setText("");
         Precio.setText("");
         Nombre.setText("");

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