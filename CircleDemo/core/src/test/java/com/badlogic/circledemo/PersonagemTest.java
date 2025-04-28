package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersonagemTest {

    private Personagem personagem;
    private Personagem outroPersonagem;
    private Texture mockAvatarTexture;
    private Texture mockAnimationTexture;

    @Before
    public void setUp() {
        // Mock para as texturas
        mockAvatarTexture = mock(Texture.class);
        mockAnimationTexture = mock(Texture.class);

        // Inicializa o personagem com texturas mockadas
        personagem = new Personagem(1, 100, 200, mockAvatarTexture, mockAnimationTexture, 600, 300, 300);
        outroPersonagem = new Personagem(2, 500, 200, mockAvatarTexture, mockAnimationTexture, 600, 300, 300);
    }

    @Test
    public void testConstructor() {
        // Verifica se os valores foram inicializados corretamente
        assertEquals(1, personagem.getId());
        assertEquals(100, personagem.getPosx(), 0.01);
        assertEquals(200, personagem.getPosy(), 0.01);
        assertEquals(600, personagem.getTamx());
        assertEquals(300, personagem.getTamy());
        assertEquals(300, personagem.getVida());
        assertEquals(300, personagem.getVidaT());
        assertNotNull(personagem.getAvatar());
        assertNotNull(personagem.getFrame());
    }

    @Test
    public void testSetMoves() {
        // Cria uma lista de movimentos simulada
        Array<Integer> moves = new Array<>();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        moves.add(4);

        // Configura os movimentos do personagem
        personagem.setMoves(moves);

        // Verifica se os movimentos foram configurados corretamente
        assertEquals(moves, personagem.getMoves());
    }

    @Test
    public void testMoveEsq() {
        // Move o personagem para a esquerda
        personagem.move_esq(10);
        assertEquals(90, personagem.getPosx(), 0.01);

        // Move o personagem para além do limite esquerdo
        personagem.move_esq(100);
        assertEquals(0, personagem.getPosx(), 0.01); // Verifica se `posx` foi redefinido para 0
    }

    @Test
    public void testMoveDir() {
        // Move o personagem para a direita
        personagem.move_dir(10);
        assertEquals(110, personagem.getPosx(), 0.01);

        // Move o personagem para além do limite direito
        personagem.move_dir(2000);
        assertEquals(1100, personagem.getPosx(), 0.01); // Verifica se `posx` foi redefinido para 1100
    }

    @Test
    public void testJump() {
        // Verifica o valor inicial de `dy`
        assertEquals(0, personagem.getDy(), 0.01);

        // Executa o salto
        personagem.jump();

        // Verifica se `dy` foi alterado para 22
        assertEquals(22, personagem.getDy(), 0.01);
    }

    @Test
    public void testGravity() {
        personagem.jump();
        personagem.gravity();
        assertTrue(personagem.getPosy() > 200); // Verifica se o personagem subiu
    }

    @Test
    public void testTomaDano() {
        personagem.tomaDano(50);
        assertEquals(250, personagem.getVida());
    }

    @Ignore("pula o teste por enquanto")
    @Test
    public void testAtack() {
        // Configura os movimentos para ataque
        personagem.setMoves(new Array<>(new Integer[]{1, 2, 3, 4}));
        outroPersonagem.setMoves(new Array<>(new Integer[]{1, 2, 3, 4}));

        // Posiciona os personagens para que estejam dentro do alcance do ataque
        outroPersonagem.move_esq(400); // Move o outro personagem para a esquerda

        // Configura a direção do ataque
        personagem.whereGo(); // Atualiza a direção do personagem

        // Realiza o ataque
        personagem.atack(outroPersonagem);

        // Verifica se o outro personagem tomou dano
        assertEquals(250, outroPersonagem.getVida());
    }

    @Test
    public void testWhereGo() {
        personagem.move_dir(10);
        assertEquals(1, personagem.whereGo()); // Direita

        personagem.move_esq(20);
        assertEquals(0, personagem.whereGo()); // Esquerda
    }
}
