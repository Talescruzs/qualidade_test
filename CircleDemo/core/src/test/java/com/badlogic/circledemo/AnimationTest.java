package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AnimationTest {

    private Animation animation;

    @Before
    public void setUp() {
        // Cria um array de TextureRegion mockado
        TextureRegion[] regions = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            regions[i] = mock(TextureRegion.class);
        }

        // Inicializa a animação com os quadros mockados
        animation = new Animation(regions, 1.0f); // 5 quadros, 1 segundo de ciclo
    }

    // @Test
    // public void testUpdateFrameChange() {
    //     animation.update(0.3f); // Atualiza com 0.3 segundos
    //     assertEquals(0, animation.getFrameIndex());

    //     animation.update(0.3f); // Atualiza com mais 0.3 segundos
    //     assertEquals(1, animation.getFrameIndex()); // O quadro deve mudar
    // }

    @Test
    public void testSetFrameIni() {
        animation.update(0.5f); // Atualiza para mudar o quadro
        animation.setFrameIni(); // Redefine para o quadro inicial
        assertEquals(0, animation.getFrameIndex());
    }

    @Test
    public void testFlipFrames() {
        animation.flip();

        // Verifica se o método foi executado sem lançar exceções
        for (int i = 0; i < animation.frameCount; i++) {
            TextureRegion region = animation.getFrame(i);
            assertNotNull(region); // Garante que o quadro não seja nulo
        }
    }

    @Test
    public void testGetFrame() {
        TextureRegion frame = animation.getFrame();
        assertNotNull(frame); // Garante que o quadro não seja nulo
    }
}
