package com.example.inventory;

import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private String Name = "";
    private int cant = 0;
    private float price = 0;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUserInputScenario() {
        //Entrar a Ingresar
        //Ingresar Nombre
        //Cerrar Keyboard
        //Ingresar Cantidad
        //Cerrar Keyboard
        //Ingresar Precio
        //Cerrar Keyboard
        //Undir el Boton
        //Check Text
        //Undir el Boton
    }

    @After
    public void tearDown() throws Exception {
    }
}