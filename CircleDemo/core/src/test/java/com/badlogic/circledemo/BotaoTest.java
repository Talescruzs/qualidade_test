package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.Texture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BotaoTest {

    private Botao botao;
    private Texture mockTexture;

    @Before
    public void setUp() {
        // Mock para a textura
        mockTexture = mock(Texture.class);

        // Inicializa o botão com a textura mockada
        botao = new Botao(mockTexture, 100, 200, 500, 200);
    }

    @Test
    public void testConstructorWithPath() {
        // Verifica se os valores foram inicializados corretamente
        assertEquals(100, (int) botao.getPosx());
        assertEquals(200, (int) botao.getPosy());
        assertEquals(500, (int) botao.getTamx());
        assertEquals(200, (int) botao.getTamy());
        assertNotNull(botao.getImg());
    }

    @Test
    public void testConstructorWithOption() {
        // Mock para a textura
        Texture mockTextureJogar = mock(Texture.class);
        Texture mockTextureVoltar = mock(Texture.class);

        // Inicializa o botão com a opção 0 (btJogar)
        Botao botaoJogar = new Botao(mockTextureJogar, 150, 250, 500, 200);

        // Verifica se os valores foram inicializados corretamente
        assertEquals(150, (int) botaoJogar.getPosx());
        assertEquals(250, (int) botaoJogar.getPosy());
        assertEquals(500, (int) botaoJogar.getTamx());
        assertEquals(200, (int) botaoJogar.getTamy());
        assertNotNull(botaoJogar.getImg());

        // Inicializa o botão com a opção 1 (btVoltar)
        Botao botaoVoltar = new Botao(mockTextureVoltar, 300, 400, 500, 200);

        // Verifica se os valores foram inicializados corretamente
        assertEquals(300, (int) botaoVoltar.getPosx());
        assertEquals(400, (int) botaoVoltar.getPosy());
        assertEquals(500, (int) botaoVoltar.getTamx());
        assertEquals(200, (int) botaoVoltar.getTamy());
        assertNotNull(botaoVoltar.getImg());
    }

    @Test
    public void testClicouInsideBounds() {
        // Verifica se o clique dentro dos limites retorna true
        assertTrue(botao.clicou(150, 250));
        assertTrue(botao.clicou(200, 300));
    }

    @Test
    public void testClicouOutsideBounds() {
        // Verifica se o clique fora dos limites retorna false
        assertFalse(botao.clicou(50, 150));
        assertFalse(botao.clicou(700, 450));
        assertFalse(botao.clicou(150, 450));
        assertFalse(botao.clicou(700, 250));
    }

    @Test
    public void testDispose() {
        // Mock para a textura
        Texture mockTexture = mock(Texture.class);

        // Inicializa o botão com a textura mockada
        Botao botaoComMock = new Botao(mockTexture, 100, 200, 500, 200);
        botaoComMock.dispose();

        // Verifica se o método dispose foi chamado na textura
        verify(mockTexture, times(1)).dispose();
    }
}
