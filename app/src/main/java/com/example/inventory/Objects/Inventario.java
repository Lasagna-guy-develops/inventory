package com.example.inventory.Objects;
import static android.R.attr.name;
public class Inventario {

    int id;
    String Name;
    int cant;
    float price;
    byte[] arr;
    String Owner;

    public Inventario(){

    }

    public Inventario(int id, String Name, int cant, float price){
        this.id = id;
        this.Name = Name;
        this.cant = cant;
        this.price = price;
    }

    public Inventario(String Name, int cant, float price, String owner){
        this.Name = Name;
        this.cant = cant;
        this.price = price;
        this.Owner = owner;
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

    public byte[] getArr() {
        return arr;
    }

    public void setArr(byte[] arr) {
        this.arr = arr;
    }

    public String getOwner(){ return Owner; }

    public void setOwner(String owner){ this.Owner=owner; }
}
