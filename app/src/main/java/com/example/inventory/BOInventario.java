package com.example.inventory;


public class BOInventario{


    public  BOInventario(){

    }

    public String addInventario(Inventario Inventory,DaoInventarioImpl dao) {
        Inventario q = dao.getInventario(Inventory.getName().toString());
        if (q != null) {
            return "El objeto a ingresar ya existe en el inventario";
        } else {
            dao.addInventario(Inventory);
            return "Ingreso Existoso";
        }
    }

    public int retrieveInsertedId(Inventario Inventory, DaoInventarioImpl dao) {
        return dao.getInventario(Inventory.getName()).getId();
    }
}
