package com.example.inventory.BO;


import android.content.Context;

import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.Objects.Inventario;

public class BOInventario{


    public  BOInventario(){

    }

    public String addInventario(Inventario Inventory, DaoInventarioImpl dao) {
        Inventario q = dao.getInventario(Inventory.getName().toString());
        if (q != null) {
            return "El objeto a ingresar ya existe en el inventario";
        } else {
            dao.addInventario(Inventory);
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

    public boolean addQr(Inventario Inventory,DaoInventarioImpl dao){
        return dao.updateInventory(Inventory);
    }

    public int retrieveInsertedId(Inventario Inventory, DaoInventarioImpl dao) {
        return dao.getInventario(Inventory.getName()).getId();
    }

    public String addInventario(Inventario Inventory) {
        DaoInventarioImpl dao = new DaoInventarioImpl(null);
        Inventario q = dao.getInventario2(Inventory);
        if (q != null) {
            return "El objeto a ingresar ya existe en el inventario";
        } else {
            //dao.addInventario(Inventory);
            return "Ingreso Existoso";
        }
    }
}
