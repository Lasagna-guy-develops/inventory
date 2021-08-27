package com.example.inventory;


public class BOInventario{


    public  BOInventario(){

    }

    public String addInventario(Inventario Inventory,DaoInventarioImpl dao){
        if(Inventory.getName() == dao.getInventario(Inventory.getName()).getName()){
            return "El objeto a ingresar ya existe en el inventario";
        }else{
            return "Ingreso Existoso";
        }
    }

    public int retrieveInsertedId(Inventario Inventory, DaoInventarioImpl dao) {
        return dao.getInventario(Inventory.getName()).getId();
    }
}
