package com.badlogic.circledemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.Texture;


public class GameDataTest {

    private GameData gameData;
    private Avatar mockAvatarP1;
    private Avatar mockAvatarP2;

    @Before
    public void setUp() {
        // Mock para os Avatares
        mockAvatarP1 = mock(Avatar.class);
        mockAvatarP2 = mock(Avatar.class);

        // Configura os mocks
        when(mockAvatarP1.getIsP1()).thenReturn(true);
        when(mockAvatarP2.getIsP1()).thenReturn(false);

        // Inicializa o GameData com os mocks
        gameData = new GameData(mockAvatarP1, mockAvatarP2);
    }

    @Test
    public void testSetAvatar() {
        // Configura o avatar do jogador 1
        gameData.setAvatar(mockAvatarP1, 1);
        verify(mockAvatarP1, times(1)).setAvatar(1);

        // Configura o avatar do jogador 2
        gameData.setAvatar(mockAvatarP2, 2);
        verify(mockAvatarP2, times(1)).setAvatar(2);
    }

    @Test
    public void testSetPersonagem() {
        // Configura o personagem do jogador 1
        gameData.setPersonagem(mockAvatarP1, 1);
        verify(mockAvatarP1, times(1)).setPersonagem(1, true);

        // Configura o personagem do jogador 2
        gameData.setPersonagem(mockAvatarP2, 2);
        verify(mockAvatarP2, times(1)).setPersonagem(2, true);
    }

    @Test
    public void testRecreateAvatar() {
        // Mock para a textura
        Texture mockTexture = mock(Texture.class);

        // Recria o avatar do jogador 1
        gameData.recreateAvatar(mockAvatarP1, mockTexture);
        assertNotNull(gameData.getAvatar(true));

        // Recria o avatar do jogador 2
        gameData.recreateAvatar(mockAvatarP2, mockTexture);
        assertNotNull(gameData.getAvatar(false));
    }

    @Test
    public void testSetLocal() {
        // Configura o local
        gameData.setLocal(1);
        assertEquals(1, gameData.getLocal().getId());
    }

    @Test
    public void testGetAvatar() {
        // Verifica se os avatares são retornados corretamente
        assertNotNull(gameData.getAvatar(true));  // Avatar do jogador 1
        assertNotNull(gameData.getAvatar(false)); // Avatar do jogador 2
    }

    @Test
    public void testGetVez() {
        // Verifica o valor inicial de vezP
        assertEquals(0, gameData.getVez());
    }

    @Test
    public void testPassaVez() {
        // Passa a vez e verifica o valor de vezP
        gameData.passaVez();
        assertEquals(1, gameData.getVez());

        gameData.passaVez();
        assertEquals(2, gameData.getVez());

        gameData.passaVez();
        assertEquals(3, gameData.getVez());

        gameData.passaVez();
        assertEquals(0, gameData.getVez()); // Volta para 0 após 3
    }
}
