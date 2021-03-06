package com.example.inventory.DAO;

import com.example.inventory.Objects.Inventario;

import java.util.List;

public interface DAOInventario {

    boolean addInventario(Inventario Inventario);
    Inventario getInventario(int id);
    Inventario getInventario(String Name);
    List getAllProducts();
    boolean updateInventory(Inventario Inventario);
    boolean deleteInventory(Inventario Inventario);
    boolean deleteAllInventory();

}
