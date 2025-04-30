package com.badlogic.circledemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Botao {
    private Texture img;
    private Integer posx, posy;
    private Integer tamx, tamy;

    // Construtor existente
    public Botao(String path, Integer posx, Integer posy, Integer tamx, Integer tamy) {
        this.img = new Texture(Gdx.files.internal(path));
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
    }

    // Novo construtor para injeção de dependência
    public Botao(Texture img, Integer posx, Integer posy, Integer tamx, Integer tamy) {
        this.img = img;
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
    }

    public Botao(Integer option, Integer posx, Integer posy){
        if(option == 0){
            this.img = new Texture(Gdx.files.internal("btJogar.jpg"));
            this.posx = posx;
            this.posy = posy;
            this.tamx = 500;
            this.tamy = 200;
        }
        else{
            this.img = new Texture(Gdx.files.internal("btVoltar.jpg"));
            this.posx = posx;
            this.posy = posy;
            this.tamx = 500;
            this.tamy = 200;
        }
    }

    public boolean clicou(float x, float y) {
        return x >= posx && x <= posx + tamx && y >= posy && y <= posy + tamy;
    }

    public Texture getImg() { return this.img; }
    public Integer getPosx() { return this.posx; }
    public Integer getPosy() { return this.posy; }
    public Integer getTamx() { return this.tamx; }
    public Integer getTamy() { return this.tamy; }
    public void dispose() {
        img.dispose();
    }
}
