package com.example.inventory;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import com.example.inventory.BO.BOInventario;
import com.example.inventory.Ctrl.CtrlInventario;
import com.example.inventory.Objects.Inventario;

import java.util.Arrays;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.inventory", appContext.getPackageName());
    }*/

    static BOInventario bo;
    static CtrlInventario c;
    Inventario inv;
    int cant;
    String Name;
    float price;
    static Context appContext;

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"Coca-Cola",10,10000},{"Pepsi",10,10000}  ,{124124,89,-10000},{"Premio",89,"ada"},{"Premio",89,-12323}
        });
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass()");
        c = new CtrlInventario();
        bo = new BOInventario();
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    public ExampleInstrumentedTest(String Name, int cant, float price){
        this.Name = Name;
        this.cant = cant;
        this.price = price;
        inv = new Inventario(Name,cant,price);
    }

    @Test
    public void Ctrl_addInventarioTest_NoExiste(){
        System.out.println("TestDaoAdd_YaExistente"+this.Name);
        String result = c.addInventario(this.Name,this.cant,this.price,appContext);
        assertEquals("Ingreso Existoso",result);
    }

    @Test
    public void Ctrl_addInventarioTest_YaExiste(){
        System.out.println("TestDaoAdd_NoExistente"+this.Name);
        String result = c.addInventario(this.Name,this.cant,this.price,appContext);
        assertEquals("El objeto a ingresar ya existe en el inventario",result);
    }

    @Test
    public void BOI_addInventarioTest_NoExiste(){
        System.out.println("TestDaoAdd_YaExistente"+this.Name);
        String result = bo.addInventario(this.inv,appContext);
        assertEquals("Ingreso Existoso",result);
    }

    @Test
    public void BOI_addInventarioTest_YaExiste(){
        System.out.println("TestDaoAdd_NoExistente"+this.Name);
        String result = bo.addInventario(this.inv,appContext);
        assertEquals("El objeto a ingresar ya existe en el inventario",result);
    }
}