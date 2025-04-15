package com.badlogic.circledemo;

import java.security.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;



public class PvpScreen implements Screen {
    final Main game;
	GameData gameData;

	Texture fundoImage, vida, vidaT;
	Avatar av1, av2;
	Personagem p1; 
	Personagem p2;

    OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;

    public PvpScreen(final Main passed_game, GameData gameData){
        game = passed_game; 
		this.gameData = gameData;

        av1 = this.gameData.getAvatar(true);
		av2 = this.gameData.getAvatar(false);

		p1 = av1.getPersonagem();
		p2 = av2.getPersonagem();

        fundoImage = new Texture(Gdx.files.internal(this.gameData.getLocal().getImagens().get(0)));

		vidaT = new Texture(Gdx.files.internal("Vermelho.png")); 
		vida = new Texture(Gdx.files.internal("Verde.png")); 

        // Init the camera objects.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 837);
		touchPos = new Vector3();
		
		batch = new SpriteBatch();
    }
    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

		game.batch.draw(fundoImage, 0, 200, 1600, 637);
		game.batch.draw(av1.getFrame(), av1.getPosx(), av1.getPosy());
		game.batch.draw(av2.getFrame(), av2.getPosx(), av2.getPosy());

		game.batch.draw(vidaT, 300, 600, p1.getVidaT(), 100);
		game.batch.draw(vida, 300, 600, p1.getVida(), 100);

		game.batch.draw(vidaT, 1000, 600, p2.getVidaT(), 100);
		game.batch.draw(vida, 1000, 600, p2.getVida(), 100);

		game.batch.draw(p1.getFrame(), p1.getPosx(), p1.getPosy(), p1.getTamx(), p1.getTamy());
		game.batch.draw(p2.getFrame(), p2.getPosx(), p2.getPosy(), p2.getTamx(), p2.getTamy());
		
		game.batch.end();


		p1.move(p2);
		p2.move(p1);

		if(p1.getVida() <= 0){
			game.setScreen(new EndScreen(game, p2, false));
			dispose();
		}
		else if(p2.getVida() <= 0){
			game.setScreen(new EndScreen(game, p1, true));
			dispose();
		}
	}

	
	@Override
	public void dispose() {
		fundoImage.dispose();
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
