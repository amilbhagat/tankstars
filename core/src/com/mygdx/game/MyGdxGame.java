package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.screens.TitleScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame obj;
	private MyGdxGame () {
	}
	public static MyGdxGame getInstance()
	{
		if (obj==null)
			obj = new MyGdxGame();
		return obj;
	}
	@Override
	public void create() {
		setScreen(new TitleScreen(this));
	}
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}
