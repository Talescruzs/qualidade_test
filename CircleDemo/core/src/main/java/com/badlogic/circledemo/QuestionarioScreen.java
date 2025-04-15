package com.badlogic.circledemo;

import java.util.Iterator;

import com.badlogic.circledemo.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Align;

public class QuestionarioScreen implements Screen {
    final Main game;
	GameData gameData;

    Texture fundoImage;
	Texture pergunta;
	Array<Texture> respostas;
    Array<Bolinha> bolinhas;
	Avatar av1, av2;
	Quiz quiz;

	int vezP;

	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;

    public QuestionarioScreen(final Main passed_game, int idPergunta, GameData gameData){
        game = passed_game; 
		this.vezP = gameData.getVez();
		this.gameData = gameData;

        fundoImage = new Texture(Gdx.files.internal("fundoQuiz.jpg"));

        av1 = gameData.getAvatar(true);
		av2 = gameData.getAvatar(false);

		quiz = new Quiz(idPergunta);

		pergunta = new Texture(Gdx.files.internal(quiz.getPergunta()));
		respostas = new Array<Texture>();
		for(int i=0; i < 4; i++){
			respostas.add(new Texture(Gdx.files.internal(quiz.getRespostas().get(i))));
		}

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 837);
		touchPos = new Vector3();
		
		batch = new SpriteBatch();

        bolinhas = new Array<Bolinha>();
		createBolinhas();
    }

    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		if(this.vezP == 0){
			av2.setFrameIni();
			av1.update();
		}
		else if(this.vezP == 1){
			av1.setFrameIni();
			av2.update();
		}

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

		game.batch.draw(fundoImage, 300, 0, 1000, 837);
		
        for (Bolinha b : bolinhas) {
			game.batch.draw(b.getImg(), b.getX(), b.getY());
		}

		game.batch.draw(av1.getFrame(), av1.getPosx(), av1.getPosy());
		game.batch.draw(av2.getFrame(), av2.getPosx(), av2.getPosy());

		game.batch.draw(pergunta, 450, 480, 700, 200);

		for(int i=0; i < 4; i++){
			game.batch.draw(respostas.get(i), bolinhas.get(i).getX(), bolinhas.get(i).getY()-70, 700, 200);
		}

		game.batch.end();

		// // Process any user input
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			for (Bolinha b : bolinhas) {
				if(b.clicou(touchPos.x, touchPos.y)){
					if(quiz.isRespostaCorreta(b.getIdLocal())){
						if(this.vezP == 0){
							gameData.getAvatar(true).setPersonagem(gameData.getLocal().getPersonagem(), true);
							gameData.setPersonagem(av1, gameData.getLocal().getPersonagem());
						}
						else if(this.vezP == 1){
							gameData.getAvatar(false).setPersonagem(gameData.getLocal().getPersonagem(), true);
							gameData.setPersonagem(av2, gameData.getLocal().getPersonagem());
						}
						gameData.passaVez();
						game.setScreen(new GameScreen(game, gameData));
						dispose();
					}
					else{
						if(this.vezP == 0){
							gameData.getAvatar(true).setPersonagem(gameData.getLocal().getPersonagem(), false);
							gameData.setPersonagem(av1, gameData.getLocal().getPersonagem());
							gameData.getAvatar(true).getPersonagem().tomaDano(gameData.getAvatar(true).getPersonagem().getVidaT()/2);
						}
						else if(this.vezP == 1){
							gameData.getAvatar(false).setPersonagem(gameData.getLocal().getPersonagem(), false);
							gameData.setPersonagem(av2, gameData.getLocal().getPersonagem());
							gameData.getAvatar(false).getPersonagem().tomaDano(gameData.getAvatar(false).getPersonagem().getVidaT()/2);
						}
						gameData.passaVez();
						game.setScreen(new GameScreen(game, gameData));
						dispose();
					}
				}
			}
		}

        // pergunta.dispose();
		
	}
    private void createBolinhas(){
        Bolinha bolinha;
        bolinha = new Bolinha(500, 100+24, 0); 
        this.bolinhas.add(bolinha);
        bolinha = new Bolinha(500, 206+24, 1); 
        this.bolinhas.add(bolinha);
        bolinha = new Bolinha(500, 313+24, 2); 
        this.bolinhas.add(bolinha);
        bolinha = new Bolinha(500, 421+24, 3); 
        this.bolinhas.add(bolinha);
    }
	
	@Override
	public void dispose() {
		fundoImage.dispose();
        pergunta.dispose();
		for(int i=0; i < 4; i++){
			respostas.get(i).dispose();
		}
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// rainMusic.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
