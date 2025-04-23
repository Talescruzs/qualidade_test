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
        // Mock para a textura
        mockTexture = mock(Texture.class);

        // Inicializa a bolinha com o mock da textura
        bolinha = new Bolinha(mockTexture, 100, 200, 1);
    }

    @Test
    public void testConstructorWithPath() {
        // Verifica se os valores foram inicializados corretamente
        assertEquals(100, (int) bolinha.getX());
        assertEquals(200, (int) bolinha.getY());
        assertEquals(1, (int) bolinha.getIdLocal());
        assertNotNull(bolinha.getImg());
    }

    @Test
    public void testConstructorWithoutPath() {
        // Inicializa a bolinha usando o construtor padrão
        Bolinha bolinhaSemPath = new Bolinha(150, 250, 2);

        // Verifica se os valores foram inicializados corretamente
        assertEquals(150, (int) bolinhaSemPath.getX());
        assertEquals(250, (int) bolinhaSemPath.getY());
        assertEquals(2, (int) bolinhaSemPath.getIdLocal());
        assertNotNull(bolinhaSemPath.getImg());
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testClicouInsideBounds() {
        // Verifica se o clique dentro dos limites retorna true
        assertTrue(bolinha.clicou(110, 210));
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testClicouOutsideBounds() {
        // Verifica se o clique fora dos limites retorna false
        assertFalse(bolinha.clicou(50, 50));
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testDispose() {
        // Mock para a textura
        Bolinha bolinhaComMock = new Bolinha("bolinha.png", 100, 200, 1) {
            @Override
            public Texture getImg() {
                return mockTexture;
            }
        };

        // Chama o método dispose
        bolinhaComMock.dispose();

        // Verifica se o método dispose foi chamado na textura
        verify(mockTexture, times(1)).dispose();
    }
}
