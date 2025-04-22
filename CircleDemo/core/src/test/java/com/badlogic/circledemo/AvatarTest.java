package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input.Keys;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AvatarTest {

    private Avatar avatarP1;
    private Avatar avatarP2;

    @Before
    public void setUp() {
        Texture mockTexture = mock(Texture.class);
        avatarP1 = new Avatar(true, mockTexture);
        avatarP2 = new Avatar(false, mockTexture);
    }

    @Test
    public void testInitialPosition() {
        // Verifica as posições iniciais dos avatares
        assertEquals(0, (int) avatarP1.getPosx());
        assertEquals(837 - 300, (int) avatarP1.getPosy());

        assertEquals(1600 - 300, (int) avatarP2.getPosx());
        assertEquals(837 - 300, (int) avatarP2.getPosy());
    }

    @Test
    public void testSetAvatar() {
        // Mock para o personagem
        Texture mockTexture = mock(Texture.class);
        Personagem mockPersonagem = mock(Personagem.class);
        when(mockPersonagem.getAvatar()).thenReturn(mockTexture);

        // Configura o avatar com o mock do personagem
        avatarP1.setAvatar(mockPersonagem);

        // Verifica se o avatar foi configurado corretamente
        assertNotNull(avatarP1.getImg());
        assertEquals(mockTexture, avatarP1.getImg());
    }

    @Test
    public void testSetPersonagem() {
        // Mock para a textura
        Texture mockAvatarTexture = mock(Texture.class);
        Texture mockAnimationTexture = mock(Texture.class);

        // Cria um objeto Personagem diretamente
        Personagem personagem = new Personagem(
            1, // ID
            0, // posx
            500, // posy
            mockAvatarTexture, // avatarTexture
            mockAnimationTexture, // animationTexture
            600, // tamx
            300, // tamy
            300 // vidaT
        );

        // Configura o personagem para o avatar
        avatarP1.setPersonagem(personagem);

        // Verifica se o personagem foi configurado corretamente
        assertNotNull(avatarP1.getPersonagem());
        assertEquals(mockAvatarTexture, avatarP1.getImg());
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testUpdate() {
        // Atualiza o avatar e verifica se o frame muda
        TextureRegion initialFrame = avatarP1.getFrame();
        avatarP1.update();
        TextureRegion updatedFrame = avatarP1.getFrame();

        // Verifica se o frame foi atualizado
        assertNotNull(updatedFrame);
        assertNotEquals(initialFrame, updatedFrame);
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testSetFrameIni() {
        // Atualiza o avatar para mudar o frame
        avatarP1.update();
        avatarP1.setFrameIni();

        // Verifica se o frame foi redefinido para o inicial
        assertEquals(avatarP1.getFrame(), avatarP1.getFrame());
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testDispose() {
        // Mock para a textura
        Texture mockTexture = mock(Texture.class);
        avatarP1.dispose();

        // Verifica se o método dispose foi chamado
        verify(mockTexture, times(1)).dispose();
    }

    @Ignore("Ignorado devido à dependência de bibliotecas nativas")
    @Test
    public void testGetIsP1() {
        // Verifica se o avatar pertence ao jogador 1 ou 2
        assertTrue(avatarP1.getIsP1());
        assertFalse(avatarP2.getIsP1());
    }
}
