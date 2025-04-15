package com.badlogic.circledemo;

import com.badlogic.gdx.utils.Array;

public class Locais {
    private String nome = new String();
    private int id;
    private Array<String> imagens = new Array<String>();
    private int personagem;
    private Array<Integer> questoes = new Array<Integer>();

    // Construtor
    public Locais(int id) {
        String img = new String();
        int questao;
        this.id = id;
        switch (id) {
            case 1:
                this.nome = "Quarta Colônia";
                img = "QuartaColonia.jpg";
                this.imagens.add(img);
                this.personagem = 1;
                questao = 1;
                this.questoes.add(questao);
                questao = 2;
                this.questoes.add(questao);
                break;
            case 2:
                this.nome = "Missoes";
                img = "Missoes.jpg";
                this.imagens.add(img);
                this.personagem = 1;
                questao = 3;
                this.questoes.add(questao);
                questao = 4;
                this.questoes.add(questao);
                break;
            case 3:
                this.nome = "Torres";
                img = "Torres.jpg";
                this.imagens.add(img);
                this.personagem = 2;
                questao = 5;
                this.questoes.add(questao);
                questao = 6;
                this.questoes.add(questao);
                break;
            default:
                break;
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Array<String> getImagens() {
        return imagens;
    }

    public void setImagens(Array<String> imagens) {
        this.imagens = imagens;
    }

    public int getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(Integer personagem) {
        this.personagem = personagem;
    }

    public Array<Integer> getQuestoes() {
        return questoes;
    }

}
