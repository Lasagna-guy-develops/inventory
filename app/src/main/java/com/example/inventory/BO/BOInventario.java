package com.example.inventory.BO;


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
        }"El objeto a ingresar ya existe en el inventario"
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
