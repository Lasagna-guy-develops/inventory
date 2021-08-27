package com.example.inventory;
import static android.R.attr.name;
public class Inventario {

    int id;
    String Name;
    int cant;
    float price;

    public Inventario(){

    }

    public Inventario(int id, String Name, int cant, float price){
        this.id = id;
        this.Name = Name;
        this.cant = cant;
        this.price = price;
    }

    public Inventario(String Name, int cant, float price){
        this.Name = Name;
        this.cant = cant;
        this.price = price;
    }

    //Constructors
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
