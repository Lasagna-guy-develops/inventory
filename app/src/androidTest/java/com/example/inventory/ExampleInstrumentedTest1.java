package com.example.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import org.mockito.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.inventory.BO.BOInventario;
import com.example.inventory.Ctrl.CtrlInventario;
import com.example.inventory.DAO.DaoInventarioImpl;
import com.example.inventory.Objects.Inventario;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest1 {

    static DaoInventarioImpl db;
    static BOInventario bo;
    static CtrlInventario c;
    Inventario inv;
    int id,cant;
    String Name;
    float price;
    static MainActivity MA;

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"Coca-Cola",10,10000,0}   ,{124124,89,-10000,1293801}
        });
    }

    @Mock
    Context appContext;

//    public ExampleInstrumentedTest1(String Name, int cant, float price, int id){
//        this.Name = Name;
//        this.cant = cant;
//        this.price = price;
//        inv = new Inventario(Name,cant,price);
//        this.id = id;
//    }

    @Test
    public void DAO_addInventarioTest(){
        System.out.println("BeforeClass()");
        MA = new MainActivity();
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new DaoInventarioImpl(appContext);
        bo = new BOInventario();
        c = new CtrlInventario();
        System.out.println("Main y Db Creado");
        System.out.println("TestDaoAdd");
        String result = c.addInventario(this.Name,this.cant,this.price,this.bo,this.db);
        assertEquals("El objeto a ingresar ya existe en el inventario",result);
    }

//    @Test
//    public void DAO_getInventarioTest(){
//        System.out.println("TestDaoGet");
//        Inventario result = db.getInventario(this.id);
//        assertNotNull(result);
//    }
}