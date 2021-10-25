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

    private int cant;
    private String Name;
    private float price;
    private static BOInventario bo;
    private int idPrecioEsperado;
    private int[] preciosEsperado;

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"Coca-Cola",10,5600,0},{"Pepsi",10,8000,1},{"Premio",89,33000,2},{"Premio",89,-10000,3},{12312,124,33000,2}
        });
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass()");
        bo = new BOInventario();
    }

    public ExampleUnitTest(String Name, int cant, float price,int idPrecioEsperado){
        this.Name = Name;
        this.cant = cant;
        this.price = price;
        this.idPrecioEsperado = idPrecioEsperado;
        this.preciosEsperado = new int[]{6997, 10472, 45160,0};
    }

    @Test
    public void InventarioObj_Test(){
        System.out.println("TextInvObj_Test"+this.Name);
        Inventario result = new Inventario(this.Name,this.cant,this.price);
        assertNotNull(result);
    }

    @Test
    public void PrecioVenta_Test(){
        System.out.println("PrecioVenta_Test"+this.Name);
        int result = bo.calcularPrecioVenta(this.price);
        assertEquals(this.preciosEsperado[this.idPrecioEsperado],result);
    }
//    @Test
//    public void Ctrl_addInventarioTest_NoExiste(){
//        System.out.println("TestDaoAdd_YaExistente"+this.Name);
//        String result = c.addInventario(this.Name,this.cant,this.price);
//        assertEquals("Ingreso Existoso",result);
//    }
//
//    @Test
//    public void Ctrl_addInventarioTest_YaExiste(){
//        System.out.println("TestDaoAdd_NoExistente"+this.Name);
//        String result = c.addInventario(this.Name,this.cant,this.price);
//        assertEquals("El objeto a ingresar ya existe en el inventario",result);
//    }
//
//    @Test
//    public void BOI_addInventarioTest_NoExiste(){
//        System.out.println("TestDaoAdd_YaExistente"+this.Name);
//        String result = bo.addInventario(this.inv);
//        assertEquals("Ingreso Existoso",result);
//    }
//
//    @Test
//    public void BOI_addInventarioTest_YaExiste(){
//        System.out.println("TestDaoAdd_NoExistente"+this.Name);
//        String result = bo.addInventario(this.inv);
//        assertEquals("El objeto a ingresar ya existe en el inventario",result);
//    }
}
