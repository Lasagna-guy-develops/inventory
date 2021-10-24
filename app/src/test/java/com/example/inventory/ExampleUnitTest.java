package com.example.inventory;
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
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(value = Parameterized.class)
public class ExampleUnitTest {

    static BOInventario bo;
    static CtrlInventario c;
    Inventario inv;
    int cant;
    String Name;
    float price;

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
    }

    public ExampleUnitTest(String Name, int cant, float price){
        this.Name = Name;
        this.cant = cant;
        this.price = price;
        inv = new Inventario(Name,cant,price);
    }

    @Test
    public void Ctrl_addInventarioTest_NoExiste(){
        System.out.println("TestDaoAdd_YaExistente"+this.Name);
        String result = c.addInventario(this.Name,this.cant,this.price);
        assertEquals("Ingreso Existoso",result);
    }

    @Test
    public void Ctrl_addInventarioTest_YaExiste(){
        System.out.println("TestDaoAdd_NoExistente"+this.Name);
        String result = c.addInventario(this.Name,this.cant,this.price);
        assertEquals("El objeto a ingresar ya existe en el inventario",result);
    }

    @Test
    public void BOI_addInventarioTest_NoExiste(){
        System.out.println("TestDaoAdd_YaExistente"+this.Name);
        String result = bo.addInventario(this.inv);
        assertEquals("Ingreso Existoso",result);
    }

    @Test
    public void BOI_addInventarioTest_YaExiste(){
        System.out.println("TestDaoAdd_NoExistente"+this.Name);
        String result = bo.addInventario(this.inv);
        assertEquals("El objeto a ingresar ya existe en el inventario",result);
    }
}
