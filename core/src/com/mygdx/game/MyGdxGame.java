package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	GameScreen gameScreen;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		this.gameScreen.create();
	}

	@Override
	public void render () {
		gameScreen.render();

	}

	public void update(float dt){

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
