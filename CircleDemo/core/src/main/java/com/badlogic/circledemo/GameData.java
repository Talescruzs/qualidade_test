package com.badlogic.circledemo;
import com.badlogic.gdx.graphics.Texture;

public class GameData {
    private Avatar av1, av2;
    private Locais local;
    private int vezP;

    // Construtor padrão
    public GameData() {
        this.vezP = 0;
        this.av1 = new Avatar(true);
        this.av2 = new Avatar(false);
        this.local = new Locais(0);
    }

    // Construtor para injeção de dependência
    public GameData(Avatar av1, Avatar av2) {
        this.vezP = 0;
        this.av1 = av1;
        this.av2 = av2;
        this.local = new Locais(0);
    }

    public void setAvatar(Avatar av, int id) {
        if (av.getIsP1()) {
            this.av1.setAvatar(id);
        } else {
            this.av2.setAvatar(id);
        }
    }

    public void setPersonagem(Avatar av, int idPersonagem) {
        if (av.getIsP1()) {
            this.av1.setPersonagem(idPersonagem, true);
        } else {
            this.av2.setPersonagem(idPersonagem, true);
        }
    }

    public void recreateAvatar(Avatar av) {
        if (av.getIsP1()) {
            this.av1 = new Avatar(true);
        } else {
            this.av2 = new Avatar(false);
        }
    }

    public void recreateAvatar(Avatar av, Texture mockTexture) {
        if (av.getIsP1()) {
            this.av1 = new Avatar(true, mockTexture);
        } else {
            this.av2 = new Avatar(false, mockTexture);
        }
    }

    public void setLocal(int idLocal) {
        this.local = new Locais(idLocal);
    }

    public Avatar getAvatar(boolean isp1) {
        if (isp1) return this.av1;
        return this.av2;
    }

    public int getVez() {
        return this.vezP;
    }

    public Locais getLocal() {
        return this.local;
    }

    public void passaVez() {
        if (this.vezP <= 2) this.vezP++;
        else this.vezP = 0;
    }
}
