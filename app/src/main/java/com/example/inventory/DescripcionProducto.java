package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import com.example.inventory.BO.BOInventario;
import android.os.Bundle;
import android.widget.TextView;

import com.example.inventory.BO.BOInventario;

public class DescripcionProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_producto);
        Bundle extras = getIntent().getExtras();
        TextView t1 = (TextView) findViewById(R.id.textView);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        TextView t3 = (TextView) findViewById(R.id.textView3);
        TextView t4 = (TextView) findViewById(R.id.textView4);
        TextView t5 = (TextView) findViewById(R.id.textView6);
        TextView t6 = (TextView) findViewById(R.id.textView7);
        TextView t7 = (TextView) findViewById(R.id.textView8);
        TextView t8 = (TextView) findViewById(R.id.textView9);
        t5.setText("Nombre:");
        t6.setText("Cantidad:");
        t7.setText("Precio Compra:");
        t8.setText("Precio Venta:");
        if (extras != null) {
            String value = extras.getString("name");
            String[] parts = value.split(" - ");
            t1.setText(parts[1]);
            t2.setText(parts[2]);
            t3.setText(parts[3]);
            BOInventario BOI = new BOInventario();
            t4.setText(Integer.toString(BOI.calcularPrecioVenta(Float.parseFloat(parts[3]))));
        }
    }
}