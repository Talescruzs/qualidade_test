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

public class EndScreen implements Screen{
    final Main game;
    Texture fundoImage;
    Personagem pv;
	GameData gameData;

    OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;

    public EndScreen(final Main passed_game, Personagem pv, boolean isP1){
        game = passed_game; 

        this.pv = pv;
        if(isP1) fundoImage = new Texture(Gdx.files.internal("vitoria.png"));

        else fundoImage = new Texture(Gdx.files.internal("vitoria2.png"));

        // Init the camera objects.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 837);
		touchPos = new Vector3();
		
		batch = new SpriteBatch();

		gameData = new GameData();
    }

    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

		game.batch.draw(fundoImage, 300, 0, 1000, 837);
		game.batch.draw(pv.getAvatar(), 675, 0, 300, 300);
		
		game.batch.end();

		// If player activates the game, dispose of this menu.
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game, gameData));
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
