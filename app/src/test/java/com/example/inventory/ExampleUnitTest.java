package com.example.inventory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.Objects.Inventario;

import java.util.Arrays;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(value = Parameterized.class)
public class ExampleUnitTest {

    DaoInventarioImpl db;
    Inventario inv;
    int id;

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"Coca-Cola",10,10000,0}   ,{124124,89,-10000,1293801}
        });
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass()");
        System.out.println("Main y Db Creado");
    }

    public ExampleUnitTest(String Name, int cant, float price,int id){
        inv = new Inventario(Name,cant,price);
        this.id = id;
    }

    @Test
    public void DAO_addInventarioTest(){
        System.out.println("TestDaoAdd");
        boolean result = db.addInventario(this.inv);
        assertTrue(result);
    }

    @Test
    public void DAO_getInventarioTest(){
        System.out.println("TestDaoGet");
        Inventario result = db.getInventario(this.id);
        assertNotNull(result);
    }
}