package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.Texture;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BolinhaTest {

    private Bolinha bolinha;
    private Texture mockTexture;

    @Before
    public void setUp() {
        mockTexture = mock(Texture.class);
        bolinha = new Bolinha(mockTexture, 100, 200, 1);
    }

    @Test
    public void testConstructorWithPath() {
        assertEquals(100, (int) bolinha.getX());
        assertEquals(200, (int) bolinha.getY());
        assertEquals(1, (int) bolinha.getIdLocal());
        assertNotNull(bolinha.getImg());
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testConstructorWithoutPath() {
        Bolinha bolinhaSemPath = new Bolinha(150, 250, 2);

        assertEquals(150, (int) bolinhaSemPath.getX());
        assertEquals(250, (int) bolinhaSemPath.getY());
        assertEquals(2, (int) bolinhaSemPath.getIdLocal());
        assertNotNull(bolinhaSemPath.getImg());
    }

    @Test
    public void testClicouInsideBounds() {
        assertTrue(bolinha.clicou(110, 210));
        assertTrue(bolinha.clicou(140, 240));
    }

    @Test
    public void testClicouOutsideBounds() {
        assertFalse(bolinha.clicou(90, 190));
        assertFalse(bolinha.clicou(160, 260));
        assertFalse(bolinha.clicou(110, 260));
        assertFalse(bolinha.clicou(160, 210));
    }

    @Test
    public void testDispose() {
        Texture mockTexture = mock(Texture.class);
        Bolinha bolinhaComMock = new Bolinha(mockTexture, 100, 200, 1);
        bolinhaComMock.dispose();
        verify(mockTexture, times(1)).dispose();
    }
}
