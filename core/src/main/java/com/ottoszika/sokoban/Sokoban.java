package com.ottoszika.sokoban;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sokoban extends Game {
	private SpriteBatch batch;


	@Override
	public void create() {
		batch = new SpriteBatch();
	}
}
