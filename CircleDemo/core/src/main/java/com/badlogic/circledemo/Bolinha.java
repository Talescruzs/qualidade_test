package com.badlogic.circledemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bolinha {
    private Texture img;
    private Integer posx, posy, idLocal;

    // Construtor com injeção de dependência para a textura
    public Bolinha(Texture img, Integer posx, Integer posy, Integer idLocal) {
        this.img = img;
        this.posx = posx;
        this.posy = posy;
        this.idLocal = idLocal;
    }

    // Construtor padrão que usa um caminho para carregar a textura
    public Bolinha(String path, Integer posx, Integer posy, Integer idLocal) {
        this(new Texture(Gdx.files.internal(path)), posx, posy, idLocal);
    }
    public Bolinha(Integer posx, Integer posy, Integer idLocal){
        this("bolinha.png", posx, posy, idLocal);
    }

    public boolean clicou(float x, float y) {
        return x >= posx && x <= posx + 50 && y >= posy && y <= posy + 50;
    }

    public Integer getX() {
        return this.posx;
    }

    public Integer getY() {
        return this.posy;
    }

    public Integer getIdLocal() {
        return this.idLocal;
    }

    public Texture getImg() {
        return this.img;
    }

    public void dispose() {
        img.dispose();
    }
}
