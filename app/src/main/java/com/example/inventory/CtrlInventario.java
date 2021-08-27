package com.example.inventory;

public class CtrlInventario {

    public CtrlInventario(){
    }

    public static String addInventario(String Name, int cant, float price,BOInventario bo,DaoInventarioImpl dao){
        Inventario aux = new Inventario(Name,cant,price);
        return bo.addInventario(aux,dao);
    }

    public static int retrieveInsertedId(String Name, int cant, float price,BOInventario bo,DaoInventarioImpl dao){
        Inventario aux = new Inventario(Name,cant,price);
        return bo.retrieveInsertedId(aux,dao);
    }
}
