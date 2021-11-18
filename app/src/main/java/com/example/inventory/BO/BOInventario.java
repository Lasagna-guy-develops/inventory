package com.example.inventory.BO;


import android.content.Context;
import android.os.StrictMode;

import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.DAO.DaoWS;
import com.example.inventory.Objects.Inventario;

import java.io.IOException;
import java.util.List;

public class BOInventario{


    public  BOInventario(){

    }

    public String addInventario(Inventario Inventory) throws IOException {
        DaoWS ws = new DaoWS();
        if (ws.exists(Inventory.getName())) {
            return "El objeto a ingresar ya existe en el inventario";
        } else {
            System.out.println("cacorrein "+new DaoWS().post(Inventory));
            return "Ingreso Existoso";
        }
    }

    public int calcularPrecioVenta(float precioOriginal){
        //agregar el IVA
        int precioVenta = (int)(precioOriginal * 1.19);

        if (precioOriginal < 7500){
            precioVenta = (int)(1.05*precioVenta);
        }else if(precioVenta < 20000){
            precioVenta = (int)(1.1*precioVenta);
        }else{
            precioVenta = (int)(1.15*precioVenta);
        }
        return precioVenta;
    }

//    public boolean addQr(Inventario Inventory,){
//        return dao.updateInventory(Inventory);
//    }

//    public int retrieveInsertedId(Inventario Inventory) {
//        return dao.getInventario(Inventory.getName()).getId();
//    }

    public List<String> fetchAll() throws IOException {
        DaoWS ws = new DaoWS();
        return new DaoWS().getAll();
    }
}
