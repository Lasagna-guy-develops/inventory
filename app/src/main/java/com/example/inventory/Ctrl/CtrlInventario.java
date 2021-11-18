package com.example.inventory.Ctrl;

import android.content.Context;

import com.example.inventory.BO.BOInventario;
import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.Objects.Inventario;

import java.io.IOException;
import java.util.List;

public class CtrlInventario {

    public CtrlInventario(){
    }

    public static String addInventario(String Name, int cant, float price, String owner, BOInventario bo) throws IOException {
        Inventario aux = new Inventario(Name,cant,price,owner);
        return bo.addInventario(aux);
    }

//    public static boolean addInventarioQr(int id, String Name, int cant, float price, byte[] arr, BOInventario bo){
//        Inventario aux = new Inventario(id, Name,cant,price);
//        aux.setArr(arr);
//        return bo.addQr(aux);
//    }

//    public static int retrieveInsertedId(String Name, int cant, float price, BOInventario bo){
//        Inventario aux = new Inventario(Name,cant,price);
//        return bo.retrieveInsertedId(aux);
//    }

    public static List<String> fetchAll(BOInventario bo) throws IOException {
        return bo.fetchAll();
    }

}
