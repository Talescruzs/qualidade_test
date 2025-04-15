package com.badlogic.circledemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private Main main;

    @Before
    public void setUp() {
        main = new Main();
    }

    @Test
    public void testGetNumber() {
        int result = main.getNumber();
        assertEquals(42, result); // Verifica se o número retornado é 42
    }
}