package com.badlogic.circledemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class QuizTest {

    private Quiz quiz;

    @Before
    public void setUp() {
        quiz = new Quiz(1); // Inicializa o Quiz com o ID 1
    }

    @Test
    public void testIsRespostaCorreta() {
        // Verifica se a resposta correta é identificada corretamente
        assertTrue(quiz.isRespostaCorreta(0)); // Índice correto para o ID 1
        assertFalse(quiz.isRespostaCorreta(1)); // Índice incorreto
        assertFalse(quiz.isRespostaCorreta(2)); // Índice incorreto
        assertFalse(quiz.isRespostaCorreta(3)); // Índice incorreto
    }
}
