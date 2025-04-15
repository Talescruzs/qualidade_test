package com.badlogic.circledemo;

import com.badlogic.gdx.utils.Array;

public class Quiz {
    private int id;
    private String pergunta = new String();
    private Array<String> respostas = new Array<String>(); // Lista com todas as respostas (correta e erradas)
    private int respostaCorretaIndex; // Índice da resposta correta na lista

    public Quiz(Integer id) {
        String frase = new String();
        int questao;
        this.id = id;
        switch (id) {
            case 1:
                pergunta = "p1.png";
                frase = "q1.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                frase = "q3.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                respostaCorretaIndex = 0;
                break;
            case 2:
                pergunta = "p2.png";
                frase = "q3.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                frase = "q1.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                respostaCorretaIndex = 2;
                break;
            case 3:
                pergunta = "p3.png";
                frase = "q1.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                frase = "q3.png";
                respostas.add(frase);
                respostaCorretaIndex = 1;
                break;
            case 4:
                pergunta = "p4.png";
                frase = "q1.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                frase = "q3.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                respostaCorretaIndex = 3;
                break;
            case 5:
                pergunta = "p5.png";
                frase = "q1.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                frase = "q3.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                respostaCorretaIndex = 2;
                break;
            case 6:
                pergunta = "p6.png";
                frase = "q1.png";
                respostas.add(frase);
                frase = "q4.png";
                respostas.add(frase);
                frase = "q2.png";
                respostas.add(frase);
                frase = "q3.png";
                respostas.add(frase);
                respostaCorretaIndex = 3;
                break;
            default:
                break;
        }
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Array<String> getRespostas() {
        return respostas;
    }

    public Integer getRespostaCorretaIndex() {
        return respostaCorretaIndex;
    }

    // Método para verificar se uma resposta está correta
    public boolean isRespostaCorreta(int index) {
        return index == respostaCorretaIndex;
    }
}