package com.badlogic.circledemo;

import com.badlogic.gdx.utils.Array;
import com.badlogic.circledemo.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personagem {
    private int id;
    private int tamx, tamy;
    private int vida, vidaT;
    private int esq = 0, dir = 0, whereG = 0;
    private int delayAtack = 0, maxAtackDelay = 30;
    private float posx, posy, posyBase; 
    private double dy = 0.0;
    private String img1, imgAva;
    // private Map<String, Ataque> ataques;
    private Animation animation, aniAtack;
    private Texture avatar;
    private Array<Integer> listMoves = new Array<Integer>();

    // Construtor que inicializa o Personagem com base no ID
    public Personagem(int id, float posx, float posy) {
        this.posx = posx;
        this.posy = posy;
        this.posyBase = posy;
        this.delayAtack = maxAtackDelay;
        Texture t;
        switch (id) {
            case 1:
                this.vidaT = 300;
                this.vida = this.vidaT;
                this.tamx = 600;
                this.tamy = 300;
                this.img1 = new String("Bagualossauro2-g.png");
                this.imgAva = new String("P1Bag.png");

                t = new Texture(Gdx.files.internal(img1));

                this.avatar = new Texture(Gdx.files.internal(imgAva));
                this.animation = new Animation(new TextureRegion(t), 2, 10f);
                break;
            case 2:
                this.vidaT = 300;
                this.vida = this.vidaT;
                this.tamx = 600;
                this.tamy = 300;
                this.img1 = new String("Bagualossauro1-g.png");
                this.imgAva = new String("P1BagGaucho.png");

                t = new Texture(Gdx.files.internal(img1));

                this.avatar = new Texture(Gdx.files.internal(imgAva));
                this.animation = new Animation(new TextureRegion(t), 2, 10f);
                break;
            default:
                break;
        }
    }

    public Personagem(int id, float posx, float posy, Texture avatarTexture, Texture animationTexture, int tamx, int tamy, int vidaT) {
        this.id = id;
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
        this.vidaT = vidaT;
        this.vida = vidaT;
        this.avatar = avatarTexture;
        this.posyBase = posy;
        this.animation = new Animation(new TextureRegion(animationTexture), 2, 10f);
        this.delayAtack = maxAtackDelay; // Inicializa o delay do ataque
    }

    public void setMoves(Array<Integer> listMoves){
        this.listMoves = listMoves;
    }


    public void move(Personagem outro){
        int wg = this.whereG;
        if (Gdx.input.isKeyPressed(this.listMoves.get(0))){
            this.esq = 1;
            move_esq(5);
        }
        else{
            this.esq = 0;
        }

		if (Gdx.input.isKeyPressed(this.listMoves.get(1))){
            this.dir = 1;
            move_dir(5);
        }
        else{
            this.dir = 0;
        }
        if(this.dir == 1 || this.esq == 1){
            this.animation.update(1);
        }

		if (Gdx.input.isKeyPressed(this.listMoves.get(2))){
            jump();
        }
		if (Gdx.input.isKeyPressed(this.listMoves.get(3))){
            atack(outro);
        }
        if(wg != whereGo()){
            this.animation.flip();
        }
        gravity();
        
    }

    public void move_esq(int d) {
        this.posx -= d;
        this.esq = 1; // Atualiza a variável `esq`
        this.dir = 0; // Garante que `dir` seja 0
        if (this.posx < 0) {
            this.posx = 0; // Redefine para 0
        }
    }

    public void move_dir(int d) {
        this.posx += d;
        this.dir = 1; // Atualiza a variável `dir`
        this.esq = 0; // Garante que `esq` seja 0
        if (this.posx > 1100) {
            this.posx = 1100; // Redefine para 1100
        }
    }

    public void jump(){
        if(this.posy == this.posyBase){
            this.dy = 22;
        }
    }
    
    public void tomaDano(int dano){
        this.vida -= dano;
    }
    
    public void atack(Personagem outro) {
        int distanciaMin = this.tamx / 2;    
        int distanciaMax = this.tamx + 100; 
        System.out.println("DelayAtack: " + delayAtack);
        System.out.println("Posição do outro personagem: " + outro.getPosx());
        System.out.println("Posição do personagem: " + this.posx);
        System.out.println("Direção do ataque: " + this.whereG);

        if (delayAtack == maxAtackDelay) {
            if (this.whereG == 1) { // Ataque à esquerda
                System.out.println("Condição de ataque à esquerda:");
                System.out.println("this.posx: " + this.posx);
                System.out.println("outro.getPosx() - distanciaMax: " + (outro.getPosx() - distanciaMax));
                System.out.println("outro.getPosx() - distanciaMin: " + (outro.getPosx() - distanciaMin));
                if (this.posx >= outro.getPosx() - distanciaMax && this.posx <= outro.getPosx() - distanciaMin) {
                    outro.tomaDano(50);
                    System.out.println("Ataque à esquerda aplicado!");
                }
            } else if (this.whereG == 0) { // Ataque à direita
                System.out.println("Condição de ataque à direita:");
                System.out.println("this.posx: " + this.posx);
                System.out.println("outro.getPosx() + distanciaMin: " + (outro.getPosx() + distanciaMin));
                System.out.println("outro.getPosx() + distanciaMax: " + (outro.getPosx() + distanciaMax));
                if (this.posx <= outro.getPosx() + distanciaMax && this.posx >= outro.getPosx() + distanciaMin) {
                    outro.tomaDano(50);
                    System.out.println("Ataque à direita aplicado!");
                }
            }
            delayAtack = 0;
        } else {
            delayAtack++;
        }
    }

    public void gravity(){
        this.posy += this.dy;
        this.dy -= 0.5;
        if(this.posy < this.posyBase){
            this.posy = this.posyBase;
            this.dy = 0;
        }
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getTamx() { return tamx; }
    public int getTamy() { return tamy; }
    public int getVida() { return vida; }
    public int getVidaT() { return vidaT; }
    public Float getPosx() { return this.posx; }
    public Float getPosy() { return this.posy; }
    public double getDy() { return this.dy; }
    public TextureRegion getFrame(){return this.animation.getFrame();}
    public Texture getAvatar(){ return this.avatar; }
    public Array<Integer> getMoves(){ return this.listMoves; }
    
    public int whereGo(){
        if(this.dir == 1 && this.esq == 0){
            this.whereG = 1;
        }
        else if(this.esq == 1 && this.dir == 0){
            this.whereG = 0;
        }
        return this.whereG;
    }
}