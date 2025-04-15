package com.badlogic.circledemo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Box;

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


public class DetalhesScreen implements Screen {
	final Main game;
	GameData gameData;
	
	Texture fundoImage;
    Locais local;
	Botao btSelec, btVoltar;
	Avatar av1, av2;
	int vezP;

	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;
	
	public DetalhesScreen(final Main passed_game, GameData gameData) {
		game = passed_game; 
		this.vezP = gameData.getVez();
		this.gameData = gameData;

		local = this.gameData.getLocal();
		av1 = this.gameData.getAvatar(true);
		av2 = this.gameData.getAvatar(false);
		
		fundoImage = new Texture(Gdx.files.internal(local.getImagens().get(0)));

		btSelec = new Botao(0, 100, 200);
		btVoltar = new Botao(1, 800, 200);

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

		game.batch.draw(fundoImage, 0, 200, 1600, 637);
		game.batch.draw(btSelec.getImg(), btSelec.getPosx(), btSelec.getPosy());
		game.batch.draw(btVoltar.getImg(), btVoltar.getPosx(), btVoltar.getPosy());
		game.batch.draw(av1.getFrame(), av1.getPosx(), av1.getPosy());
		game.batch.draw(av2.getFrame(), av2.getPosx(), av2.getPosy());
		
		game.batch.end();

		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if(btSelec.clicou(touchPos.x, touchPos.y)){
				int random = new Random().nextInt(local.getQuestoes().size);

				if(this.vezP == 0){
					game.setScreen(new QuestionarioScreen(game, local.getQuestoes().get(random), gameData));
					dispose();
				}
				else if (this.vezP == 1){
					game.setScreen(new QuestionarioScreen(game, local.getQuestoes().get(random), gameData));
					dispose();
				}
				else{
					gameData.setLocal(local.getId());
					gameData.passaVez();
					game.setScreen(new PvpScreen(game, gameData));
					dispose();
				}
				
			}
			else if(btVoltar.clicou(touchPos.x, touchPos.y)){
				if(this.vezP == 0){
					gameData.recreateAvatar(av1);
				}
				else if(this.vezP == 1){
					gameData.recreateAvatar(av2);
				}
				gameData.setLocal(0);
				game.setScreen(new GameScreen(game, gameData));
				dispose();
			}
		}
		
	}

	
	@Override
	public void dispose() {
		fundoImage.dispose();
		btSelec.dispose();
		btVoltar.dispose();
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