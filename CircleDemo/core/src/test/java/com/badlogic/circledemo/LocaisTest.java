package com.badlogic.circledemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.utils.Array;

public class LocaisTest {

    @Test
    public void testConstructorCase1() {
        Locais local = new Locais(1);

        assertEquals("Quarta Colônia", local.getNome());
        assertEquals(1, local.getId());
        assertEquals(1, local.getPersonagem());

        Array<String> imagens = local.getImagens();
        assertEquals(1, imagens.size);
        assertEquals("QuartaColonia.jpg", imagens.get(0));

        Array<Integer> questoes = local.getQuestoes();
        assertEquals(2, questoes.size);
        assertTrue(questoes.contains(1, false));
        assertTrue(questoes.contains(2, false));
    }

    @Test
    public void testConstructorCase2() {
        Locais local = new Locais(2);

        assertEquals("Missoes", local.getNome());
        assertEquals(2, local.getId());
        assertEquals(1, local.getPersonagem());

        Array<String> imagens = local.getImagens();
        assertEquals(1, imagens.size);
        assertEquals("Missoes.jpg", imagens.get(0));

        Array<Integer> questoes = local.getQuestoes();
        assertEquals(2, questoes.size);
        assertTrue(questoes.contains(3, false));
        assertTrue(questoes.contains(4, false));
    }

    @Test
    public void testConstructorCase3() {
        Locais local = new Locais(3);

        assertEquals("Torres", local.getNome());
        assertEquals(3, local.getId());
        assertEquals(2, local.getPersonagem());

        Array<String> imagens = local.getImagens();
        assertEquals(1, imagens.size);
        assertEquals("Torres.jpg", imagens.get(0));

        Array<Integer> questoes = local.getQuestoes();
        assertEquals(2, questoes.size);
        assertTrue(questoes.contains(5, false));
        assertTrue(questoes.contains(6, false));
    }

    @Test
    public void testSettersAndGetters() {
        Locais local = new Locais(1);

        local.setNome("Novo Nome");
        assertEquals("Novo Nome", local.getNome());

        local.setId(10);
        assertEquals(10, local.getId());

        Array<String> novasImagens = new Array<>();
        novasImagens.add("NovaImagem.jpg");
        local.setImagens(novasImagens);
        assertEquals(1, local.getImagens().size);
        assertEquals("NovaImagem.jpg", local.getImagens().get(0));

        local.setPersonagem(3);
        assertEquals(3, local.getPersonagem());
    }
}