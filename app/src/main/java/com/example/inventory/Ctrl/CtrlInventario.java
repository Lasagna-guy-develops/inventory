package com.example.inventory.Ctrl;

import android.content.Context;

import com.example.inventory.BO.BOInventario;
import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.Objects.Inventario;

public class CtrlInventario {

    public CtrlInventario(){
    }

    public static String addInventario(String Name, int cant, float price, BOInventario bo, DaoInventarioImpl dao){
        Inventario aux = new Inventario(Name,cant,price);
        return bo.addInventario(aux,dao);
    }

    public static boolean addInventarioQr(int id, String Name, int cant, float price, byte[] arr, BOInventario bo,DaoInventarioImpl dao){
        Inventario aux = new Inventario(id, Name,cant,price);
        aux.setArr(arr);
        return bo.addQr(aux,dao);
    }

    public static int retrieveInsertedId(String Name, int cant, float price, BOInventario bo,DaoInventarioImpl dao){
        Inventario aux = new Inventario(Name,cant,price);
        return bo.retrieveInsertedId(aux,dao);
    }

    public static String addInventario(String Name, int cant, float price){
        Inventario aux = new Inventario(Name,cant,price);
        BOInventario bo = new BOInventario();
        return bo.addInventario(aux);
    }
}
