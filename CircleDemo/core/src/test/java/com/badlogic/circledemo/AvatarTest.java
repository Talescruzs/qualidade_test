package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.Texture;

import org.junit.Before;
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
        assertEquals(0, (int) avatarP1.getPosx());
        assertEquals(837 - 300, (int) avatarP1.getPosy());

        assertEquals(1600 - 300, (int) avatarP2.getPosx());
        assertEquals(837 - 300, (int) avatarP2.getPosy());
    }

    @Test
    public void testSetAvatar() {
        Texture mockTexture = mock(Texture.class);
        Personagem mockPersonagem = mock(Personagem.class);
        when(mockPersonagem.getAvatar()).thenReturn(mockTexture);
        avatarP1.setAvatar(mockPersonagem);

        assertNotNull(avatarP1.getImg());
        assertEquals(mockTexture, avatarP1.getImg());
    }

    @Test
    public void testSetPersonagem() {
        Texture mockAvatarTexture = mock(Texture.class);
        Texture mockAnimationTexture = mock(Texture.class);

        Personagem personagem = new Personagem(
            1, 
            0, 
            500, 
            mockAvatarTexture,
            mockAnimationTexture,
            600, 
            300, 
            300 
        );
        avatarP1.setPersonagem(personagem);

        assertNotNull(avatarP1.getPersonagem());
        assertEquals(mockAvatarTexture, avatarP1.getImg());
    }

    @Test
    public void testSetFrameIni() {
        avatarP1.update();
        avatarP1.setFrameIni();

        assertEquals(avatarP1.getFrame(), avatarP1.getFrame());
    }

    @Test
    public void testDispose() {
        Texture mockTexture = mock(Texture.class);
        avatarP1 = new Avatar(true, mockTexture);

        avatarP1.dispose();
        verify(mockTexture, times(1)).dispose();
    }

    @Test
    public void testGetIsP1() {
        assertTrue(avatarP1.getIsP1());
        assertFalse(avatarP2.getIsP1());
    }
}
